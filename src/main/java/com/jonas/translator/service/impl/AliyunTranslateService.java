package com.jonas.translator.service.impl;

import com.aliyun.alimt20181012.Client;
import com.aliyun.alimt20181012.models.TranslateRequest;
import com.aliyun.alimt20181012.models.TranslateResponse;
import com.aliyun.teaopenapi.models.Config;
import com.jonas.translator.common.LanguageType;
import com.jonas.translator.common.TranslatorType;
import com.jonas.translator.service.TranslateService;
import com.jonas.translator.util.GsonUtil;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author shenjy
 * @createTime 2023/8/30 18:40
 * @description 阿里机器翻译
 */
@Slf4j
@Service(TranslatorType.ALIYUN)
public class AliyunTranslateService implements TranslateService {

    @Value("${translate.aliyun.keyId}")
    private String keyId;
    @Value("${translate.aliyun.keySecret}")
    private String keySecret;
    @Value("${translate.aliyun.endpoint}")
    private String endpoint;

    private Client client;

    private static final String FORMAT_TYPE = "text";
    private static final String SCENE = "general";

    @PostConstruct
    public void init() throws Exception {
        Config config = new Config().setAccessKeyId(keyId).setAccessKeySecret(keySecret).setEndpoint(endpoint);
        client = new Client(config);
    }

    @Override
    public String translate(String text) {
        TranslateRequest request = new TranslateRequest()
                .setSourceLanguage(LanguageType.ZH)
                .setTargetLanguage(LanguageType.EN)
                .setFormatType(FORMAT_TYPE)
                .setScene(SCENE)
                .setSourceText(text);
        TranslateResponse response = null;
        try {
            response = client.translate(request);
            log.info("阿里云机器翻译，text={}, response={}", text, GsonUtil.toJsonObject(response));
            return response.body.data.translated;
        } catch (Exception e) {
            log.error(String.format("阿里云机器翻译异常，text=%s, response=%s", text, GsonUtil.toJson(response)), e);
        }
        return "";
    }
}
