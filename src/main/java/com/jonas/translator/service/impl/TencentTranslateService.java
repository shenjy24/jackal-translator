package com.jonas.translator.service.impl;

import com.jonas.translator.common.TranslatorType;
import com.jonas.translator.service.TranslateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author shenjy
 * @createTime 2023/8/30 19:56
 * @description 腾讯翻译
 */
@Slf4j
@Service(TranslatorType.TENCENT)
public class TencentTranslateService implements TranslateService {
    @Override
    public String translate(String text) {
        return null;
    }
}
