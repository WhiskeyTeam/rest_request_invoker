package com.whiskey.libs.rest.request.serobj;

import lombok.*;
import java.util.*;

@Getter
@Setter
public class ReceiptPayload {
    private String version;
    private String requestId;
    private long timestamp;
    // todo. private List<> images = new ArrayList<>();
    private List<ImageHeadForReceipt> images = new ArrayList<>();
}
