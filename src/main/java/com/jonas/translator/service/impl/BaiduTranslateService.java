package com.jonas.translator.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jonas.translator.common.LanguageType;
import com.jonas.translator.common.TranslatorType;
import com.jonas.translator.service.TranslateService;
import com.jonas.translator.util.GsonUtil;
import com.jonas.translator.util.HttpGet;
import com.jonas.translator.util.MD5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shenjy
 * @createTime 2023/8/30 9:53
 * @description <a href="http://api.fanyi.baidu.com/product/113">百度翻译</a>
 */
@Slf4j
@Service(TranslatorType.BAIDU)
public class BaiduTranslateService implements TranslateService {

    @Value("${translate.baidu.appId}")
    private String appId;
    @Value("${translate.baidu.key}")
    private String key;
    @Value("${translate.baidu.api}")
    private String api;

    private static final String TRANS_RESULT = "trans_result";
    private static final String TRANS_DST = "dst";

    @Override
    public String translate(String text) {
        String result = "";
        try {
            Map<String, String> params = buildParams(text, LanguageType.AUTO, LanguageType.EN);
            result = HttpGet.get(api, params);
            JsonObject jsonObject = GsonUtil.toJsonObject(result);
            JsonArray transResult = jsonObject.get(TRANS_RESULT).getAsJsonArray();
            return transResult.get(0).getAsJsonObject().get(TRANS_DST).getAsString();
        } catch (Exception e) {
            log.error(String.format("baidu translate error, text = %s, result = %s", text, result), e);
        }
        return "";
    }

    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);
        params.put("appid", appId);
        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);
        // 签名
        String src = appId + query + salt + key; // 加密前的原文
        params.put("sign", MD5.md5(src));

        return params;
    }
}
