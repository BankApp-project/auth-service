# auth-service

This service is build with one milion daily users in mind.
Also it's build to be highly maintainable.

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

## UX

### UX Problems

If user tries to verify his email few times without OTP confirmation,
and then he will try to confirm his email with OTP from not-latest email, he won't be able to.
This problem won't be solved.

## PROBLEMS

### USER ID

Initial approach was to use id from `EmailVerificationRequestEvent` to create new User.
Problem is - where to store this id? We don't want to create new users until they confirm theirs email address,
one approach is to store it just as key-value in redis with key as `userId:<email>` and value of event UUID.
