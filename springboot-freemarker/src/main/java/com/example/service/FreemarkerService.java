package com.example.service;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FreemarkerService {
    private Logger logger = LoggerFactory.getLogger(FreemarkerService.class);
    private Map<String, Template> templateMap = new ConcurrentHashMap<>();
    private freemarker.template.Configuration cfg;

    @PostConstruct
    public void init() throws IOException {
        cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_0);
        cfg.setDirectoryForTemplateLoading(new File(FreemarkerService.class.getResource("/templates/ftls").getPath()));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateUpdateDelayMilliseconds(0);//设置在检查是否存在比缓存模板更新版本的模板“文件”之前必须经过的时间（以毫秒为单位）。 默认为5000毫秒。
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
//        cfg.setLogTemplateExceptions(false);
    }

    public String process(String tempName, Map conditions) {
        try {
            if (!templateMap.containsKey(tempName)) {
                templateMap.put(tempName, cfg.getTemplate(tempName));
            }

            StringWriter out = new StringWriter();
            templateMap.get(tempName).process(conditions, out);
            String rs = out.getBuffer().toString();
            out.close();
            return rs;
        } catch (TemplateException | IOException e) {
            logger.error("生成文本失败:", e);
        }
        return null;
    }

    public String process(String tempName, String key, Object value) {
        Map conditions = new HashMap();
        conditions.put(key, value);
        return process(tempName, conditions);
    }

    public void clearAll() {
        templateMap.clear();
    }

}
