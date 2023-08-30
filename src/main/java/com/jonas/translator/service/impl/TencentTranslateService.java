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

    @Value("${translate.tencent.keyId}")
    private String keyId;
    @Value("${translate.tencent.keySecret}")
    private String keySecret;

    private CvmClient client;

    @PostConstruct
    public void init() throws Exception {
        Credential cred = new Credential(keyId, keySecret);
        client = new CvmClient(cred, "ap-shanghai");
    }

    @Override
    public String translate(String text) {
        try {


            DescribeInstancesRequest req = new DescribeInstancesRequest();
            DescribeInstancesResponse resp = client.DescribeInstances(req);

            System.out.println(DescribeInstancesResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

        return null;
    }
}
