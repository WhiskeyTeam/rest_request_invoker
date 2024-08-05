package com.whiskey.libs.rest.request.base64;

import java.util.*;

public class Base64Encoder {
    public String encode(String str) {
        byte[] ba = new ImageStream().downloadImageAsByteArray(str);
        return Base64.getEncoder().encodeToString(ba);  // byte array -> base64 string
    }
}
