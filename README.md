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

## 도커

https://ktor.io/docs/docker.html

이거 따라하기

https://medium.com/swlh/how-to-deploy-a-ktor-server-using-docker-dd01f4883c30

## auto reload

```ps
./gradlew -t build -x test -i

./gradlew :run
```