package com.whiskey.libs.rest.request;

import org.junit.jupiter.api.Test;

public class ocrtest {
    @Test
    public void nonHeadTest() throws Exception {
        String url = "http://localhost:8080/ocr/receipt";
        var invoker = new RestInvoker<>(url, FilteredReceipt.class);

        // 사전에 반드시 파일은 업로드 되어 있어야 함!!!
        // 업로드된 파일명을 반환하여 하기 fileName에 입력해야 합니다.

        var fileName = new RequestBody();
        fileName.fileName = "Snipaste_2024-08-01_17-15-32.png";

        FilteredReceipt request = invoker.request(fileName, RequestBody.class, RequestMethod.POST);
        System.out.println(request);
    }
}

class RequestBody {
    public String fileName;
}