package com.jonas.translator.service.impl;

import com.jonas.translator.common.LanguageType;
import com.jonas.translator.common.TranslatorType;
import com.jonas.translator.service.TranslateService;
import com.jonas.translator.util.GsonUtil;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.tmt.v20180321.TmtClient;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateRequest;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateResponse;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author shenjy
 * @createTime 2023/8/30 19:56
 * @description <a href="https://cloud.tencent.com/document/api/551/15619">腾讯翻译</a>
 */
@Slf4j
@Service(TranslatorType.TENCENT)
public class TencentTranslateService implements TranslateService {

    @Value("${translate.tencent.keyId}")
    private String keyId;
    @Value("${translate.tencent.keySecret}")
    private String keySecret;
    @Value("${translate.tencent.region}")
    private String region;
    @Value("${translate.tencent.projectId}")
    private Long projectId;

    private TmtClient client;

    @PostConstruct
    public void init() {
        Credential cred = new Credential(keyId, keySecret);
        client = new TmtClient(cred, region);
    }

    @Override
    public String translate(String text) {
        TextTranslateResponse response = null;
        try {
            TextTranslateRequest req = new TextTranslateRequest();
            req.setSource(LanguageType.AUTO);
            req.setTarget(LanguageType.EN);
            req.setSourceText(text);
            req.setProjectId(projectId);

            response = client.TextTranslate(req);
            return response.getTargetText();
        } catch (Exception e) {
            log.error("腾讯翻译, text={}, response={}", text, GsonUtil.toJson(response), e);
        }
        return "";
    }
}
