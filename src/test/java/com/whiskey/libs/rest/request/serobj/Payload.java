package com.whiskey.libs.rest.request.serobj;

import lombok.*;

import java.util.*;

@Getter
@Setter
public class Payload {
    private String version;
    private String requestId;
    private long timestamp;
    private String lang;

    private List<ImageHead> images = new ArrayList<>();
    private boolean enableTableDetection;
}

