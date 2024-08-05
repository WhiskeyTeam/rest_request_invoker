package com.whiskey.libs.rest.request.serobj;

import lombok.*;

@Getter
@Setter
public class ImageHead {
    private String format;
    private String url;
    private String data;
    private String name;
    private int[] templateIds;
}
