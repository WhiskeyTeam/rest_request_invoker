package com.whiskey.libs.rest.request;

import lombok.*;
import org.junit.jupiter.api.*;

public class OcrTest {
    @Test
    @DisplayName("REDIS 서버에 키-값 페어 저장 테스트")
    public void test1() {
//        String url = "REQUEST_URL_HERE";
//
//        var invoker = RestInvoker.create(url, null);
//
//        var payload = new RequestPayload();
//        payload.setKey("MyKey");
//        payload.setValue("Alice22");
//
//        try {
//            invoker.request(payload, RequestPayload.class, RequestMethod.POST);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}

@Setter
@Getter
class RequestPayload {
    private String key;
    private String value;
}

class ResponsePayload {

}
