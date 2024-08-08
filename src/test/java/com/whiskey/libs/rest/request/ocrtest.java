package com.whiskey.libs.rest.request;

import lombok.*;
import org.junit.jupiter.api.*;

public class OcrTest {
    @Test
    public void test1() throws Exception {
        String url = "YOUR REQUEST URL";
        var invoker = RestInvoker.create(url, ResponseHead.class);

        RequestHead body = new RequestHead();
        body.fileName = "dd9a6650-ea45-4021-9d0e-afa31729c26d.jpeg";

        ResponseHead request = invoker.request(body, RequestHead.class, RequestMethod.POST);
        System.out.println(request);
    }
}

@Getter
@Setter
class RequestHead {
    public String fileName;
}
