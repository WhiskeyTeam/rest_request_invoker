package com.whiskey.libs.rest.request;

import lombok.*;

@Getter
@Setter
public class ErrorMessage {
    // {
    //    "code": "0011",
    //    "message": "Request invalid: {images[0].url=must match \"^((ht|f)tps?):\/\/[\\w\\-]+(\\.[\\w\\-]+)+([\\w\\-.,@?^=%&:\/~+#]*[\\w\\-@?^=%&\/~+#])?$\"}",
    //    "path": "/external/v1/32965/ae43fa0909815a39a619576aff03b94bee51207695cf6066e9bf13e609f28081/infer",
    //    "traceId": "e8eba950421c4fa4a39f09f5db67a24e",
    //    "timestamp": 1722496635856
    //}
    private String code;
    private String message;
    private String path;
    private String traceId;
    private long timestamp;
}
