package com.whiskey.libs.rest.request;

import com.google.gson.Gson;
import lombok.Getter;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class RestInvoker<T> {
    @Getter
    private final String requestUrl;

    @Getter
    private final Class<T> responseType;

    private static final Gson gson = new Gson(); // reusable Gson instance

    // private constructor to enforce the use of the factory method
    private RestInvoker(String requestUrl, Class<T> responseType) {
        this.requestUrl = requestUrl;
        this.responseType = responseType;
    }

    // Factory method to create an instance of RestInvoker
    public static <T> RestInvoker<T> create(String requestUrl, Class<T> responseType) {
        return new RestInvoker<>(requestUrl, responseType);
    }

    /**
     * REST 서버에 주어진 페이로드와 헤더를 사용하여 요청을 보내고 응답을 받는다.
     * @param requestPayload 요청 페이로드
     * @param payloadType 요청 페이로드의 타입
     * @param headers 요청 헤더
     * @param method 요청 메소드
     * @return 응답 페이로드
     * @param <P> 요청 페이로드의 타입
     * @throws Exception 요청이 실패한 경우
     */
    public <P> T request(P requestPayload, Class<P> payloadType, Map<String, String> headers, RequestMethod method) throws Exception {
        URL url = new URL(requestUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            connection.setRequestMethod(method.toString());
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Set headers
            if (headers != null) {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    connection.setRequestProperty(header.getKey(), header.getValue());
                }
            }

            // Write payload
            if (requestPayload != null)  {
                String jsonPayload = gson.toJson(requestPayload, payloadType); // Serialize request payload
                writePayload(connection, jsonPayload);
            }

            // Get response
            int code = connection.getResponseCode();
            if (code == 200) {
                String response = readResponse(connection);

                if (headers == null) return null;
                return gson.fromJson(response, responseType);
            } else {
                String errorResponse = readResponse(connection.getErrorStream());
                throw new RuntimeException("Failed : HTTP error code : " + code + ", Response: " + errorResponse + ", ERROR MESSAGE: " + connection.getErrorStream());
            }
        } finally {
            connection.disconnect(); // 연결 해제
        }
    }

    // Overloaded method without headers

    /**
     * REST 서버에 주어진 페이로드를 사용하여 요청을 보내고 응답을 받는다. <br>
     * 오버로드된 메소드로 헤더를 사용하지 않는다.
     * @param requestPayload 요청 페이로드
     * @param payloadType 요청 페이로드의 타입
     * @param method 요청 메소드
     * @return 응답 페이로드
     * @param <P> 요청 페이로드의 타입
     * @throws Exception 요청이 실패한 경우
     */
    public <P> T request(P requestPayload, Class<P> payloadType, RequestMethod method) throws Exception {
        return request(requestPayload, payloadType, null, method);
    }

    /**
     * REST 서버에 주어진 헤더를 사용하여 요청을 보내고 응답을 받는다. <br>
     * 오버로드된 메소드로 페이로드를 사용하지 않는다.
     * @param headers 요청 헤더
     * @param method 요청 메소드
     * @return 응답 페이로드
     * @throws Exception 요청이 실패한 경우
     */
    public T request(Map<String, String> headers, RequestMethod method) throws Exception {
        return request(null, null, headers, method);
    }

    /**
     * REST 서버에 요청을 보내고 응답을 받는다. <br>
     * 오버로드된 메소드로 페이로드와 헤더를 사용하지 않는다.
     * @param method 요청 메소드
     * @return 응답 페이로드
     * @throws Exception 요청이 실패한 경우
     */
    public T request(RequestMethod method) throws Exception {
        return request(null, null, null, method);
    }

    private void writePayload(HttpURLConnection connection, String payload) throws IOException {
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = payload.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
    }

    private String readResponse(HttpURLConnection connection) throws IOException {
        return readResponse(connection.getInputStream());
    }

    private String readResponse(InputStream stream) throws IOException {
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }
        return response.toString();
    }
}
