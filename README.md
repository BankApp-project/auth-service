# auth-service
Authentication Service

## Swagger / OpenAPI Informations

Visible at `/swagger-ui/index.html`

## Logging

- Every `warn-type` exception should extend `AuthServiceWarnException`
- Every `error-type` exception should extend `AuthServiceErrorException`
- Constructors:
    - `super(String msg, Throwable e)`
    - `super(String msg)`