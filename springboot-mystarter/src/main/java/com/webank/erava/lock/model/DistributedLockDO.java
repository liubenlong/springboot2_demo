package com.webank.erava.lock.model;

import java.util.Date;

public class DistributedLockDO  {
    private Long id;

    private String lockKey;

    private Date createTime;

    private Integer expireTime;

    private Date expireAtTime;

    private String requestFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLockKey() {
        return lockKey;
    }

    public void setLockKey(String lockKey) {
        this.lockKey = lockKey == null ? null : lockKey.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Integer expireTime) {
        this.expireTime = expireTime;
    }

    public Date getExpireAtTime() {
        return expireAtTime;
    }

    public void setExpireAtTime(Date expireAtTime) {
        this.expireAtTime = expireAtTime;
    }

    public String getRequestFlag() {
        return requestFlag;
    }

    public void setRequestFlag(String requestFlag) {
        this.requestFlag = requestFlag == null ? null : requestFlag.trim();
    }
}