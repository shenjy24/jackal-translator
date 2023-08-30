package com.jonas.translator.service;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shenjy
 * @createTime 2023/8/30 9:23
 * @description
 */
@Component
public class TranslateServiceFactory {

    @Value("${translate.service}")
    private String translateService;

    @Resource
    private final Map<String, TranslateService> map = new ConcurrentHashMap<>();

    public TranslateService getService() {
        if (!map.containsKey(translateService)) {
            throw new RuntimeException("找不到对应的服务：" + translateService);
        }
        return map.get(translateService);
    }

    public TranslateService getService(String service) {
        if (!map.containsKey(service)) {
            throw new RuntimeException("找不到对应的服务：" + service);
        }
        return map.get(service);
    }
}
