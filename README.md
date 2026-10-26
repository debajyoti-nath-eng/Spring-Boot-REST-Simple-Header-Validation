# Spring Boot REST Simple Header Validation

A minimal Spring Boot application with one REST endpoint that demonstrates required request headers and basic validation.

## Run

```sh
mvn spring-boot:run
```

## Endpoint

`GET /api/headers` requires both `X-Client-Id` and `X-Request-Id`. Missing or blank headers receive `400 Bad Request`.

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
