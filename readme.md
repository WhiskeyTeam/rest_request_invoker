# RestRequestInvoker

REST-API와 같은 서비스에 HTTP요청을 보내고, 편리하게 역직렬화된 객체를 응답받을 수 있는 유틸리티입니다.

## RestInvoker Class

```java
public class RestInvoker<T> { ... }
```



### 생성자

```java
private RestInvoker(String requestUrl, Class<T> responseType) {}
```

생성자의 접근권한이 `private`이므로, 생성자를 통한 객체 생성이 불가합니다.



### 사용 예시

#### 1. RestInvoker 인스턴스 생성

이 모듈은 `Factory Method` 패턴으로 작성되어 있으므로, 다음과 같이 인스턴스를 반환받을 수 있습니다.

```java
// 요청 API에서 응답받을 JSON 모델로 구성되어 있는 클래스(필요에 따라 직접 구성)
ResponseType responseType = new ResponseType();

// create()메소드 호출하여 인스턴스 생성(반환타입 없을 경우 null처리)
RestInvoker<ResponseType> invoker = RestInvoker.create("INVOKE_URL", ResponseType.class);
```



#### 2. HEADER 정의

헤더는 `Map<String, String>` 타입으로 정의됩니다. API에서 요구하는 경우에만 생성하면 됩니다.

```java
Map<String, String> headers = new HashMap<>;
headers.put("key1", "value1");
headers.put("key2", "value2");
//...
```



#### 3. Body Payload 정의

`POST` 메소드의 경우 JSON 모델로 구성된 요청 Body를 API에 전달해야 할 수 있습니다. Body의 경우는 요청 Body에 대한 Payload를 직접 구성해야 합니다.

```java
RequestPayload requestBody = new RequestBody();
requestBody.setUserName = "HongGilDong";
requestbody.setAge = 30;
// ...
```

\* Lombok 사용으로 인해 필드를 모두 `private`로 선언할 경우, 반드시 `@Getter` / `@Setter` 어노테이션이 적용되어 있어야 합니다. 다중 계층 객체의 경우 서브타입 클래스 또한 동일하게 적용되어야 하는 점 참고바랍니다.



##### 4. 요청 전달 및 객체 반환

요청에 필요한 준비 객체를 사용하여 요청을 전달합니다. 전달 파라미터 구성에 따라 서로 다른 구성으로 이루어진 요청 메소드가 오버로딩되어있습니다.

1. 요청 바디, 헤더 모두 전달하는 경우

   ```java
   public <P> T request(P requestPayload, Class<P> payloadType, Map<String, String> headers, RequestMethod method);
   ```

   

2. 헤더를 제외하고 요청 바디만 전달하는 경우

   ```java
   public <P> T request(P requestPayload, Class<P> payloadType, RequestMethod method);
   ```



3. 요청 바디 페이로드를 제외하고 헤더만 전달하는 경우

   ```java
   public T request(Map<String, String> headers, RequestMethod method);
   ```

   

4. 요청 바디와 헤더를 모두 전달하지 않는 경우

   ```java
   public T request(RequestMethod method);
   ```



## 사용 예시

**예시 시나리오:** `test.mysite.site/redis/save`를 통해 Redis에 Key-Value 페어를 저장하려고 하는 경우

요청 Header는 불필요하며, Body만 아래 JSON 모델의 구조를 사용하여 POST로 전달해야 한다. 별도의 응답은 제공되지 않는다고 가정.

```json
{
    "key": "string",
    "value": "string"
}
```

요청 코드는 다음과 같다.

```java
public class Application {
    public static void main(String[] args) {
        String url = "https://test.mysite.site/redis/save";
        
        // 반환타입 없으므로 null
        RequestInvoker<UserDefinedPayload> invoker = RequestInvoker.create(url, null);
        
        // 요청 바디 생성
        UserDefinedPayload requestBody = new UserDefinedPayload();
        requestBody.setKey("TestKey1");
        requestBody.setValue("TestValue1");
        
        // 요청 전달(헤더 없이 바디만 전달하므로 적절한 오버로드된 메소드 선택하여 할당)
        try {
            invoker.request(requestBody, UserDefinedPayload.class, RequestMethod.POST);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

@Getter
@Setter
class UserDefinedPayload {
    private String key;
    private String value;
}
```

