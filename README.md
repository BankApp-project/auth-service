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

## OTP

### Description

OTP stands for One Time Password.
This class can store anything alphanumeric that is shorter than `OTP_LENGTH` (env variable)

### Generation

For OTP generation use `OTPService`. As for now, there is implementation for number base `OTPGenerator`.
If in the future there will be need for alphabethic base OTPs - just make your implementation of `OTPGenerator` and use
it accordingly.