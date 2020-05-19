package com.webank.erava.lock.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;


@Component
@Slf4j
public class DistributedLockService {

    @Autowired
    @Qualifier("DistributedLockDataSource")
    private DruidDataSource dataSource; // 声明Druid连接池的对象

    public boolean tryLock(String key, int expire, String flag) {
        if (expire == 0) expire = 1000 * 60;//默认1分钟
        String insertSql = "insert into fdm_ic_distributed_lock(lock_key, create_time, expire_time, expire_at_time, request_flag) values(?,?,?,?,?)";
        GregorianCalendar calendar = new GregorianCalendar();

        // 从连接池中获取连接、创建连接的报告、命令报告执行指定的SQL语句
        try (DruidPooledConnection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertSql)) {
            stmt.setString(1, key);
            stmt.setTimestamp(2, new Timestamp(calendar.getTime().getTime()));
            stmt.setInt(3, expire);
            calendar.add(Calendar.MILLISECOND, expire);
            stmt.setTimestamp(4, new Timestamp(calendar.getTime().getTime()));
            stmt.setString(5, flag);
            int update = stmt.executeUpdate();
            return update == 1;
        } catch (Exception e) {
            log.warn("{} 获取锁失败. requestFlag={}。errorMsg:{}", e.getMessage(), key, flag, e);
            return false;
        }
    }

    public boolean releaseLock(String key, String flag) {
        String deleteSql = "delete from fdm_ic_distributed_lock where lock_key=? and request_flag=?";

        // 从连接池中获取连接、创建连接的报告、命令报告执行指定的SQL语句
        try (DruidPooledConnection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(deleteSql)) {
            stmt.setString(1, key);
            stmt.setString(2, flag);
            int update = stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            log.warn("{} 获取锁失败. requestFlag={}。errorMsg:{}", e.getMessage(), key, flag, e);
            return false;
        }
    }

    @Scheduled(initialDelay = 1000, fixedDelay = 5000)
    @Async
    public void releaseInvalidLock() {
        String deleteSql = "delete from fdm_ic_distributed_lock where expire_at_time < now()";

        // 从连接池中获取连接、创建连接的报告、命令报告执行指定的SQL语句
        try (DruidPooledConnection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            boolean execute = stmt.execute(String.format(deleteSql));
            log.info("定期清理过期分布式锁");
        } catch (Exception e) {
            log.error("清理过期key失败！");
        }
    }
}
