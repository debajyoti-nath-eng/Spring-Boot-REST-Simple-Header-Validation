# Spring Boot REST Simple Header Validation

A minimal Spring Boot application demonstrating required request headers and validation with a controller and a Spring MVC interceptor.

## Run

```sh
mvn spring-boot:run
```

## Endpoint

`GET /api/headers` requires both `X-Client-Id` and `X-Request-Id`. Missing or blank headers receive `400 Bad Request`.

`GET /api/intercepted/headers` validates the same headers with a Spring MVC `HandlerInterceptor`. It reports all missing or blank headers together in a `400 Bad Request` JSON response.

Example:

```sh
curl -i http://localhost:8080/api/headers \
  -H "X-Client-Id: client-123" \
  -H "X-Request-Id: request-456"
```

Successful response:

```json
{
  "message": "Headers accepted",
  "clientId": "client-123",
  "requestId": "request-456"
}
```
