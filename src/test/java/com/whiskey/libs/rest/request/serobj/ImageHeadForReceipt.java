package com.whiskey.libs.rest.request.serobj;

import lombok.*;

@Getter
@Setter
public class ImageHeadForReceipt {
    private String format;
    private String data;        // desc. base64 encoded image data
    private String name;
}
