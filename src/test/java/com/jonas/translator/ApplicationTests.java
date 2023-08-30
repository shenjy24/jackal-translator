package com.jonas.translator;

import com.jonas.translator.component.Translator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class ApplicationTests {

    @Autowired
    private Translator translator;

    @Test
    void testTranslate() {
        String text = "一位专业的女性律师，采用超写实风格，直视镜头，逼真的绘画风格，完美动人的面容！4K分辨率，数字绘画，杰作，ArtStation上的热门作品，精美的光线处理，诡异的，David Cronenberg、WLOP和Peter Mohrbacher的风格，面部完美对称，不祥的，邪恶的，科幻风格，DAZ，逼真绘画，精细的，华丽的，ArtStation上的热门作品。";
        String translation = translator.translate(text);
        System.out.println(translation);
    }
}
