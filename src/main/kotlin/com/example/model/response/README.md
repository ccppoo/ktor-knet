# response


## HttpResonse.kt

### HttpResponse(class)

Http로 보낼 수 있는 response 모델

### generateHttpResponse(fun)

**HttpResponse**는 `sealed class`라서 `static method`처럼 생성자를 만들어서

상태에 따라서 HTTP에 적합한 response 모델을 반환함

Kotlin 코드 -> Http response 모델로 만들어주는 역할이라고 보면 됨