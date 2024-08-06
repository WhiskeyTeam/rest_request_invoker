package com.whiskey.libs.rest.request;

import com.whiskey.libs.rest.request.base64.ImageStream;
import com.whiskey.libs.rest.request.serobj.*;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class ApiRequestTest {
    @Test
    public void test1() throws Exception {
//        String url = "Invoke URL";
//        var invoker = new RestInvoker<>(url, ResponseHead.class);
//
//        Map<String, String> head = Map.of(
//                "Content-Type", "application/json",
//                "X-OCR-SECRET", "API_SECRET"
//        );
//        var image = new ImageHead();
//        image.setFormat("jpg");
//        image.setUrl(url);
//        image.setData(null);
//        image.setName("testSample7.jpg");
//        image.setTemplateIds(new int[]{ 31316, 31315, 31313, 31304 });
//
//        var body = new Payload();
//        body.setVersion("V2");
//        body.setRequestId("string");
//        body.setTimestamp(1722477710525L);
//        body.setLang("ko");
//        body.setEnableTableDetection(true);
//        body.getImages().add(image);
//
//        ResponseHead request = invoker.request(body, Payload.class, head, RequestMethod.POST);
//
//        System.out.println(request);
    }

    @Test
    public void test2() throws Exception {
//        String url = "Invoke URL";
//        var invoker = new RestInvoker<>(url, ResponseHead.class);
//
//        Map<String, String> head = Map.of(
//                "Content-Type", "application/json",
//                "X-OCR-SECRET", "API_SECRET"
//        );
//
//        ImageStream imageStream = new ImageStream();
//        byte[] imageAsByteArray = imageStream.downloadImageAsByteArray("Image URL");
//        String base64Image = java.util.Base64.getEncoder().encodeToString(imageAsByteArray);
//
//        var image = new ImageHeadForReceipt();
//
//        image.setFormat("png");
//        image.setData(base64Image);
//        image.setName("name");
//
//        var body = new ReceiptPayload();
//        body.setVersion("V2");
//        body.setRequestId("string");
//        body.setTimestamp(1722477710525L);
//        body.getImages().add(image);
//
//        ResponseHead request = invoker.request(body, ReceiptPayload.class, head, RequestMethod.POST);
//
//        System.out.println(request);
    }
}
