# tsl
```
the simple life
```

## tsl-common
```
base util,domain,service etc.
```

## tsl-admin
```
front app
user,group,role,permission,menu
```

## tsl-cms
```
front app
folder,article,attachment,feedback
```

## shell
```
1. echo -n "password" | openssl dgst -sha512 -hmac "username"
2. uuidgen | sed s/-//g
```
## exception
```
@ControllerAdvice   @ExceptionHandler
Exception	HTTP Status Code
TypeMismatchException	400 (Bad Request)
BindException	400 (Bad Request)
MethodArgumentNotValidException	400 (Bad Request)
HttpMessageNotReadableException	400 (Bad Request)
MissingServletRequestParameterException	400 (Bad Request)
MissingServletRequestPartException	400 (Bad Request)
NoSuchRequestHandlingMethodException	404 (Not Found)
HttpRequestMethodNotSupportedException	405 (Method Not Allowed)
HttpMediaTypeNotAcceptableException	406 (Not Acceptable)
HttpMediaTypeNotSupportedException	415 (Unsupported Media Type)

ConversionNotSupportedException	500 (Internal Server Error)
HttpMessageNotWritableException	500 (Internal Server Error)

DataAccessException,Exception
```