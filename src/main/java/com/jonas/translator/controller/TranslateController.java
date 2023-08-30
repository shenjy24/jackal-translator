package com.jonas.translator.controller;

import com.jonas.translator.component.Translator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenjy
 * @createTime 2023/8/30 10:11
 * @description 翻译控制器
 */
@RestController("/translate")
@RequiredArgsConstructor
public class TranslateController {

    private final Translator translator;

    /**
     * 翻译单个文本
     *
     * @param text 原文
     * @return 译文
     */
    @PostMapping("/text")
    public String translate(String text) {
        return translator.translate(text);
    }
}
