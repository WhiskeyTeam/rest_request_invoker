package com.whiskey.libs.rest.request;

import com.whiskey.libs.rest.request.base64.ImageStream;
import com.whiskey.libs.rest.request.serobj.*;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class ApiRequestTest {
    @Test
    public void test1() throws Exception {
        String url = "https://mf00jmkwbp.apigw.ntruss.com/custom/v1/32965/ae43fa0909815a39a619576aff03b94bee51207695cf6066e9bf13e609f28081/infer";
        var invoker = new RestInvoker<ResponseHead>(url, ResponseHead.class);

        Map<String, String> head = Map.of(
                "Content-Type", "application/json",
                "X-OCR-SECRET", "cVhReUVIT0l1aWx4cEdmdnB1ZXZGVnFYaW5laVVNek0="
        );
        var image = new ImageHead();
        image.setFormat("jpg");
        image.setUrl("https://kr.object.ncloudstorage.com/whiskey-file/testSample7.jpg");
        image.setData(null);
        image.setName("testSample7.jpg");
        image.setTemplateIds(new int[]{ 31316, 31315, 31313, 31304 });

        var body = new Payload();
        body.setVersion("V2");
        body.setRequestId("string");
        body.setTimestamp(1722477710525L);
        body.setLang("ko");
        body.setEnableTableDetection(true);
        body.getImages().add(image);


        ResponseHead request = invoker.request(body, Payload.class, head, RequestMethod.POST);

        System.out.println(request);
    }

    @Test
    public void test2() throws Exception {
        // 31360
        // https://mf00jmkwbp.apigw.ntruss.com/custom/v1/32963/3bf678201ff62e291fb5267cb9a6bb12dce9f30926dcdc65177280aca81531da/infer
        String url = "https://y9pm2s135x.apigw.ntruss.com/custom/v1/33193/3bf678201ff62e291fb5267cb9a6bb12dce9f30926dcdc65177280aca81531da/document/receipt";
        // T2l4aU90dUVzUVJ3aGVodVZqVWNwUWNsU2VSV2ZoUnE=

        var invoker = new RestInvoker<ResponseHead>(url, ResponseHead.class);

        Map<String, String> head = Map.of(
                "Content-Type", "application/json",
                "X-OCR-SECRET", "Tk5SZE9iaGVFdWZOV2hhb1NkZGlEZGdRU3V0WldUSG8="
        );

//        var image = new ImageHead();
//        image.setFormat("png");
//        image.setUrl("https://kr.object.ncloudstorage.com/whiskey-file/Snipaste_2024-08-01_17-15-32.png");
//        image.setData(null);
//        image.setName("testSample7.jpg");
//        image.setTemplateIds(new int[]{ 31360 });

        ImageStream imageStream = new ImageStream();
        byte[] imageAsByteArray = imageStream.downloadImageAsByteArray("https://kr.object.ncloudstorage.com/whiskey-file/Snipaste_2024-08-01_17-15-32.png");
        String base64Image = java.util.Base64.getEncoder().encodeToString(imageAsByteArray);

        var image = new ImageHeadForReceipt();

        image.setFormat("png");
        image.setData(base64Image);
        image.setName("testSample7.jpg");

//        var body = new Payload();
//        body.setVersion("V2");
//        body.setRequestId("string");
//        body.setTimestamp(1722477710525L);
//        body.setLang("ko");
//        body.setEnableTableDetection(true);
//        body.getImages().add(image);
        var body = new ReceiptPayload();
        body.setVersion("V2");
        body.setRequestId("string");
        body.setTimestamp(1722477710525L);
        body.getImages().add(image);

        ResponseHead request = invoker.request(body, ReceiptPayload.class, head, RequestMethod.POST);

        System.out.println(request);
    }
}
