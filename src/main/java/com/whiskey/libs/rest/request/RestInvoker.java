package com.whiskey.libs.rest.request;

import com.google.gson.Gson;
import lombok.*;

import java.io.*;
import java.net.*;
import java.util.Map;

public class RestInvoker<T> {
    @Getter
    private final String requestUrl;

    @Getter
    private final Class<T> responseType;

    private Gson gson = new Gson();

    public RestInvoker(String requestUrl, Class<T> responseType) {
        this.requestUrl = requestUrl;
        this.responseType = responseType;
    }

    public <P> T request(Object requestPayload, Class<P> payloadType, Map<String, String> headers, RequestMethod method) throws Exception {
        URL url = new URL(requestUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method.toString());
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Set headers
        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                connection.setRequestProperty(header.getKey(), header.getValue());
            }
        }

        // Send request payload if not null
        if (requestPayload != null && payloadType != null) {
            String jsonPayload = gson.toJson(requestPayload, payloadType); // Serialize request payload
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonPayload.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
        }

        // Get response
        int code = connection.getResponseCode();    // Get response code(like 200, 404, etc.)

        if (code == 200) {  // if the request is successful
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) response.append(responseLine.trim());

                return gson.fromJson(response.toString(), responseType);    // Deserialize response to Object of type T
            }
        } else {
            throw new RuntimeException("Failed : HTTP error code : " + code);
        }
    }

    // Overloaded method without headers
    public <P> T request(Object requestPayload, Class<P> payloadType, RequestMethod method) throws Exception {
        return request(requestPayload, payloadType, null, method);
    }

    // Overloaded method without body
    public <P> T request(Map<String, String> headers, RequestMethod method) throws Exception {
        return request(null, null, headers, method);
    }
}
