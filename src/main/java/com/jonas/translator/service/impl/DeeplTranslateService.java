package com.jonas.translator.service.impl;

import com.jonas.translator.common.TranslatorType;
import com.jonas.translator.service.TranslateService;
import org.springframework.stereotype.Service;

/**
 * @author shenjy
 * @createTime 2023/8/30 9:21
 * @description <a href="https://www.deepl.com/pro-api?cta=header-pro-api">deepl api</a>
 */
@Service(TranslatorType.DEEPL)
public class DeeplTranslateService implements TranslateService {

    @Override
    public String translate(String text) {
        return text;
    }
}
