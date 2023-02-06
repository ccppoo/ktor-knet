# ktor Template

[start.ktor.io](https://start.ktor.io/)

spring initilizer 이랑 똑같은 툴임


## SSL sample key gen (optional)

이거는 `application.conf`에서 `sslPort` 설정하면 필요

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

```bash
docker build -t knet-ktor:latest .

# 컨맨드 실행한 디렉토리 내부의 uploads 폴더에 저장됨
docker run --rm -p 8010:8010 -v path/to/save/image:/app/uploads knet-ktor

# Windows 의 경우 예시
docker run --rm -p 8010:8010 -v C:\Users\<USER_NAME>\Documents\test:/app/uploads knet-ktor
```

## auto reload

`intellj`로 실행 (`Shift+F10`) 누르고 `Terminal`에서 실행

```ps
./gradlew -t build -x test -i
```

