package com.whiskey.libs.rest.request.base64;

import lombok.*;

import java.io.*;
import java.net.URL;

@Getter
@Setter
public class ImageStream {
    public byte[] downloadImageAsByteArray(String imageUrl) {
        byte[] imageAsByteArray = null;

        try {
            URL url = new URL(imageUrl);

            // open stream
            InputStream is = url.openStream();

            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];

            int nBytes;
            while((nBytes = is.read(buffer)) != -1) {
                bs.write(buffer, 0, nBytes);
            }

            is.close();
            bs.close();

//            byte[] imageBytes = bs.toByteArray();
            imageAsByteArray = bs.toByteArray();

            // convert byte array to base64 string
//            imageAsByteArray = java.util.Base64.getEncoder().encodeToString(imageBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imageAsByteArray;
    }
}
