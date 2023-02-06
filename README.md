# ktor Template

[start.ktor.io](https://start.ktor.io/)

spring initilizer 이랑 똑같은 툴임


## SSL sample key gen

```ps
keytool -keystore keystore.jks -alias sampleAlias -genkeypair -keyalg RSA -keysize 4096 -validity 3 -dname 'CN=localhost, OU=ktor, O=ktor, L=Unspecified, ST=Unspecified, C=US'
```

하고 사용할 비밀번호 치기

그리고 ssl 인증서 파일 `keystore.jks` build 폴더로 이동 ㄱㄱ

기본적인건 완료!

## Docker...

https://ktor.io/docs/docker.html

to save image

mount folder to `/uploads`

example : `/app/uploads:/uploads`

## auto reload

```ps
./gradlew -t build -x test -i

./gradlew :run
```

