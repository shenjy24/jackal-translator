package com.jonas.translator.component;

import com.jonas.translator.service.TranslateService;
import com.jonas.translator.service.TranslateServiceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author shenjy
 * @createTime 2023/8/30 9:31
 * @description
 */
@Component
@RequiredArgsConstructor
public class Translator {

    private final TranslateServiceFactory factory;

    public String translate(String text) {
        TranslateService translateService = factory.getService();
        return translateService.translate(text);
    }
}
