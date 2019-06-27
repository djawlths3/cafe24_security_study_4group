# HTTPS (HTTP+SSL/TLS)



## HTTPS VS HTTP

**HTTP는 Hypertext Transfer Protocol의 약자다**. 즉 Hypertext 인 HTML을 전송하기 위한 통신규약을 의미한다. HTTP**S**에서 **마지막의 S는 Over Secure Socket Layer의 약자로 Secure라는 말을 통해서 알 수 있듯이 보안이 강화된 HTTP**라는 것을 짐작할 수 있다. **HTTP는** 암호화되지 않은 방법으로 데이터를 전송하기 때문에 **서버와 클라이언트가 주고 받는 메시지를 감청하는 것이 매우 쉽다**. **예를들어 로그인을 위해서 서버로 비밀번호를 전송하거나, 또는 중요한 기밀 문서를 열람하는 과정에서 악의적인 감청이나 데이터의 변조등이 일어날 수 있다는 것이다.** 이를 보안한 것이 HTTPS다.

![http sslì ëí ì´ë¯¸ì§ ê²ìê²°ê³¼](https://pushalert.co/blog/wp-content/uploads/2017/11/ssl-adds-security-layer-to-http.jpg)

- #### HTTP

  - 인터넷 상에서 정보를 주고 받기위한 프로토콜(양식과 규칙의 체계)
  - 클라이언트와 서버 사이에 이루어지는 요청/응답 프로토콜
  - 암호화되지 않은 방법으로 데이터를 전송한다. (악의적인 감청, 데이터 변조의 가능성)

- #### HTTPS

  - 보안이 강화된 HTTP

  - Hypertext Transfer Protocol Over Secure Socket Layer의 약자

  - 모든 HTTP 요청과 응답 데이터는 네트워크로 보내지기 전에 암호화된다.

  - HTTPS는 HTTP의 하부에 SSL과 같은 보안계층을 제공함으로써 동작한다.

    

## HTTPS와 SSL

HTTPS와 SSL를 같은 의미로 이해하고 있는 경우가 많다. 이것은 맞기도 틀리기도 하다. 그것은 마치 인터넷과 웹을 같은 의미로 이해하는 것과 같다. 결론적으로 말하면 웹이 인터넷 위에서 돌아가는 서비스 중의 하나인 것처럼 **HTTPS도 SSL 프로토콜 위에서 돌아가는 프로토콜이다.**



![views](https://i.imgur.com/4GHgl0T.png)



### SSL 디지털 인증서

SSL 인증서는 클라이언트와 서버간의 통신을 제3자가 보증해주는 전자화된 문서다. 클라이언트가 서버에 접속한 직후에 서버는 클라이언트에게 이 인증서 정보를 전달한다. 클라이언트는 이 인증서 정보가 신뢰할 수 있는 것인지를 검증 한 후에 다음 절차를 수행하게 된다. SSL과 SSL 디지털 인증서를 이용했을 때의 이점은 아래와 같다.

- 통신 내용이 공격자에게 노출되는 것을 막을 수 있다. 
- 클라이언트가 접속하려는 서버가 신뢰 할 수 있는 서버인지를 판단할 수 있다.
- 통신 내용의 악의적인 변경을 방지할 수 있다. 



![img](https://t1.daumcdn.net/cfile/tistory/2171D643590C3F380B)

### 대칭키

암호를 만드는 행위인 **암호화**를 할 때 사용하는 일종의 비밀번호를 키(key)라고 한다. 이 키에 따라서 암호화된 결과가 달라지기 때문에 키를 모르면 암호를 푸는 행위인 **복호화**를 할 수 없다. 대칭키는 동일한 키로 암호화와 복호화를 같이 할 수 있는 방식의 암호화 기법을 의미한다. **즉 암호화를 할 때 1234라는 값을 사용했다면 복호화를 할 때 1234라는 값을 입력해야 한다는 것이다.** 이해를 돕기 위해서 openssl을 이용해서 대칭키 방식으로 암호화하는 방법을 살펴보자. 아래 명령을 실행하면 plaintext.txt 파일이 생성된다. 그리고 비밀번호를 요구 받을 것이다. 이 때 입력한 비밀번호가 대칭키가 되는 것이다. 



### 공개키

**대칭키 방식은 단점이 있다.** 암호를 주고 받는 사람들 사이에 대칭키를 전달하는 것이 어렵다는 점이다. **대칭키가 유출되면 키를 획득한 공격자는 암호의 내용을 복호화 할 수 있기 때문에 암호가 무용지물이 되기 때문이다.**  이런 배경에서 나온 암호화 방식이 공개키방식이다.

공개키 방식은 두개의 키를 갖게 되는데 A키로 암호화를 하면 B키로 복호화 할 수 있고, B키로 암호화하면 A키로 복호화 할 수 있는 방식이다. 이 방식에 착안해서 **두개의 키 중 하나를 비공개키(private key, 개인키, 비밀키라고도 부른다)로하고, 나머지를 공개키(public key)로 지정**한다. **비공개키는 자신만이 가지고 있고**, **공개키를 타인에게 제공**한다. 공개키를 제공 받은 타인은 **공개키를 이용해서 정보를 암호화**한다. **암호화한 정보를 비공개키를 가지고 있는 사람에게 전송**한다. **비공개키의 소유자는 이 키를 이용해서 암호화된 정보를 복호화** 한다. **이 과정에서 공개키가 유출된다고해도 비공개키를 모르면 정보를 복호화 할 수 없기 때문에 안전하다**. 공개키로는 암호화는 할 수 있지만 복호화는 할 수 없기 때문이다.



### CA

인증서의 역할은 클라이언트가 접속한 서버가 클라이언트가 의도한 서버가 맞는지를 보장하는 역할을 한다. 이 역할을 하는 민간기업들이 있는데 이런 기업들을 **CA(Certificate authority)** 혹은 Root Certificate 라고 부른다. **CA는 아무 기업이나 할 수 있는 것이 아니고 신뢰성이 엄격하게 공인된 기업들만이 참여할 수 있다.** 그 중에 대표적인 기업들은 아래와 같다. 수치는 현시점의 시장점유율이다. ([위키피디아 참조](https://en.wikipedia.org/wiki/Certificate_authority))

- Symantec (VeriSign, Thawte, Geotrust) with 42.9% market share
- Comodo with 26%
- GoDaddy with 14%
- GlobalSign with 7.7%

SSL을 통해서 암호화된 통신을 제공하려는 서비스는 CA를 통해서 인증서를 구입해야 한다. CA는 서비스의 신뢰성을 다양한 방법으로 평가하게 된다.



### 사설 인증기관

개발이나 사적인 목적을 위해서 SSL의 암호화 기능을 이용하려한다면 자신이 직접 CA의 역할을 할 수도 있다. 물론 이것은 공인된 인증서가 아니기 때문에 이러한 사설 CA의 인증서를 이용하는 경우 브라우저는 아래와 같은 경고를 출력한다.

![img](https://s3.ap-northeast-2.amazonaws.com/opentutorials-user-file/module/228/1536.gif)

공인된 CA가 제공하는 인증서를 사용한다면 브라우저의 주소창이 아래와 비슷한 모양을 보여줄 것이다.

![img](https://s3.ap-northeast-2.amazonaws.com/opentutorials-user-file/module/228/1537.gif)



### SSL 인증서의 내용

SSL 인증서에는 다음과 같은 정보가 포함되어 있다.

1. 서비스의 정보 (인증서를 발급한 CA, 서비스의 도메인 등등)
2. 서버 측 공개키 (공개키의 내용, 공개키의 암호화 방법)

인증서의 내용은 위와 같이 크게 2가지로 구분할 수 있다. 1번은 클라이언트가 접속한 서버가 클라이언트가 의도한 서버가 맞는지에 대한 내용을 담고 있고, 2번은 서버와 통신을 할 때 사용할 공개키와 그 공개키의 암호화 방법들의 정보를 담고 있다. 서비스의 도메인, 공개키와 같은 정보는 서비스가 CA로부터 인증서를 구입할 때 제출해야 한다.

위와 같은 내용은 CA에 의해서 암호화 된다. 이 때 사용하는 암호화 기법이 [공개키](https://opentutorials.org/course/228/4894#public) 방식이다. CA는 자신의 CA 비공개키를 이용해서 서버가 제출한 인증서를 암호화하는 것이다. CA의 비공개키는 절대로 유출되어서는 안된다. 이것이 노출되는 바람에 [디지노타](http://www.ted.com/talks/mikko_hypponen_three_types_of_online_attack.html)라는 회사는 파산된 사례도 있다.



### CA를 브라우저는 알고 있다

**인증서를 이해하는데 꼭 알고 있어야 하는 것이 CA의 리스트다.** **브라우저는 내부적으로 CA의 리스트를 미리 파악하고 있다.** 이 말은 브라우저의 소스코드 안에 CA의 리스트가 들어있다는 것이다. **브라우저가 미리 파악하고 있는 CA의 리스트에 포함되어야만 공인된 CA가 될 수 있는 것이다.** CA의 리스트와 함께 각 [CA의 공개키](https://opentutorials.org/course/228/4894#public)를 브라우저는 이미 알고 있다.



### SSL 통신과정

- 컴퓨터와 컴퓨터가 네트워크를 통해서 통신을 할때 `핸드쉐이크 -> 세션 -> 세션종료` 의 과정을 거친다.

- 암호화된 HTTP 메시지를 교환하기 전에 클라이언트와 서버는 `SSL 핸드쉐이크를` 진행한다.

- 핸드쉐이크의 목적은 아래와 같다.

  - 프로토콜 버전번호 교환
  - 양쪽이 알고 있는 pre master secret 키 생성 및 교환
  - 양쪽의 신원 인증
  - 채널을 암호화 하기 위한 임시 세션 키 생성

- SSL 통신과정을 간단하게 도식화 하면 아래와 같다.

  

  ![views](https://i.imgur.com/YIfy1wK.png)



---
---
---

## Tomcat 8.5 SSL 사용

### PEM (openssl) 인증서를 -> PKCS12 변환 -> JKS 변환해서 사용

​	*JKS : Java Standard Keystore

```c
/* 톰캣폴더/conf밑에 ssl폴더 생성 */
[root@localhost ~]# mkdir /usr/local/cafe24/tomcat-cafe24/conf/ssl
```

```c
/* 톰캣폴더/conf/ssl로 이동 */
[root@localhost ~]# cd /usr/local/cafe24/tomcat-cafe24/conf/ssl
```



#### OPENSSL 이용하여 KEY 파일 생성

```
[root@localhost ssl]# openssl genrsa -des3 -out 'key아이디' 2048
```



위처럼 입력하게 되면 아래와 같은 구문이 나타난다.



![1.png](https://github.com/djawlths3/cafe24_security_study_4group/blob/master/security/2.%EB%B0%9C%ED%91%9C%EC%9E%90%EB%A3%8C/chapter6/1.png?raw=true)



#### KEY 비밀번호입력 및 확인

```
Enter pass phrase for cafe24.com.key:'key비밀번호'
Verifying - Enter pass phrase for cafe24.com.key: 'key비밀번호'
```



***발급이 완료된 인증서는 재발급 또는 변경이 불가하므로 CSR 생성시 절대 주의 바랍니다.** 



  **CSR ( Certificate Signing Request ) 이란?**

```c
* SSL 서버를 운영하는 회사의 정보를 암호화하여 인증기관으로 보내 인증서를 발급받게 하는   일종의 신청서입니다. 
* CSR은 ASCII 텍스트 화일로 생성됩니다. 
* CSR을 생성할 때 서버의 식별명을 입력하게 됩니다. 식별명은 각 서버를 공유하게 나타내는   이름으로 다음과 같은 정보를 포함합니다.
```



> Country Name ( 국가코드) [] : KR
> State or Province Name ( 지역 ) [] : Seoul
> Locality Name ( 시/군/구 ) [] : Seocho
> Organization Name ( 회사명 ) [] : Hanbiro Inc
> Organizational Unit Name ( 부서명 ) [] : Linux Team
> Common Name ( 서비스도메인명 ) [] : 도메인
> Email Address [] : 이메일@naver.com



##### **CSR 항목에 대한 설명**

> Country Name : 이것은 두 자로 된 ISO 형식의 국가 코드입니다. 
> State or Province Name : 시 이름을 입력해야 하며 약어를 사용할 수 없습니다. 
> Locality Name : 이 필드는 대부분의 경우 생략이 가능하며 업체가 위치한 곳를 나타냅니다. 
> Organization : 사업자 등록증에 있는 회사명과 일치되는 영문회사명을 입력하시면 됩니다. 
> Organization Unit : "리눅스 관리팀", "윈도우 관리팀" 등과 같이 업체의 부서를 입력할 수 있습니다. 
> Common Name : 인증받을 도메인주소를 입력하시면 됩니다.

이 정보로 웹 사이트를 식별하므로 호스트 이름을 변경할 경우 다른 디지털 ID를 요청해야 합니다. 
호스트에 연결하는 클라이언트 브라우저가 디지털 ID의 이름과 URL이 일치하는지를 확인합니다.



 **CSR 항목 입력시 주의사항**

> Common Name 에는 인증서를 설치할 사이트의 도메인의 이름을 정확하게 입력하셔야 합니다.
>
> Common Name 에는 IP 주소, 포트번호, 경로명, http:// 나 https:// 등은 포함할 수 없습니다.
>
> CSR 항목에는 < > ~ ! @ # $ % ^ * / \ ( ) ? 등의 특수 68 문자를 넣을 수 없습니다.
>
> CSR 생성후 서버에 개인키 (Private Key) 가 생성됩니다. 개인키를 삭제하거나 분실할 경우 인증서를 발급받아도 설치가 불가합니다. 따라서 꼭 개인키를 백업받아 두셔야 합니다.
>
> 정보입력과정 마지막에 나오는 A challenge password 와 An optional company name 두 항목은 입력하지 마시고 Enter 만 누르고 넘어가야 합니다. 두 정보가 입력될 경우 잘못된 CSR 생성될 수 있습니다.
>
> ↘ 위 주의사항을 유의하여 아래와 같은 절차로 CSR 생성을 진행합니다.
> **(\* 윈도우+아파치의 경우 -config "openssl.cnf 절대 경로" 입력 하시기 바랍니다.)]**
>
> 

#### CSR 파일 생성

```c
[root@localhost ssl]# openssl req -new -key "cafe24.com.key" -out "cafe24.csr"
Enter pass phrase for cafe24.com.key:
You are about to be asked to enter information that will be incorporated
into your certificate request.
What you are about to enter is what is called a Distinguished Name or a DN.
There are quite a few fields but you can leave some blank
For some fields there will be a default value,
If you enter '.', the field will be left blank.
-----
Country Name (2 letter code) [XX]:"KR"
State or Province Name (full name) []:"Seoul-si"
Locality Name (eg, city) [Default City]:"gangnam-gu"
Organization Name (eg, company) [Default Company Ltd]:"cafe24.bit"
Organizational Unit Name (eg, section) []:"SE"
Common Name (eg, your name or your server's hostname) []:"cafe24"
Email Address []:"mynameisyjh@gmail.com"
Please enter the following 'extra' attributes
to be sent with your certificate request
A challenge password []:
An optional company name []:
```



```c
/*생성된 CSR 을 출력하면 아래와 같은 base64 형식의 문서를 볼 수 있습니다.*/
[root@localhost ssl]# cat cafe24.csr
```



![2.png](https://github.com/djawlths3/cafe24_security_study_4group/blob/master/security/2.%EB%B0%9C%ED%91%9C%EC%9E%90%EB%A3%8C/chapter6/2.png?raw=true)

> 이문서의 첫 줄 -----BEGIN … 부터 마지막 줄 -----END … 까지 복사하여 지정된 SSL 접수페이지에 복사하여 붙여 넣은 뒤 입력정보와 함께 전송하면 접수가 완료됩니다.
> 접수한 CSR 파일이 정상적으로 생성됐다면 별다른 문제없이 인증서를 발급 받을 수 있습니다.
> 인증서 파일은 신청시 기록한 Email 주소를 통해 인증서를 첨부파일로 수신하게 됩니다.



CSR 파일이 생성되었으면 인증서 업체에 CSR 정보를 보내어  인증서 파일을 받으면 된다. 보통 인증서, 체인인증서, ROOT인증서를 받는다

- 개인인증서 : cafe24.crt

- root 인증서 : DigiCertRoot2.crt.cer

- 체인 인증서 : EncryptionEverywhereDVCA.crt

  

해당 인증서의 파일이 3개가 존재한다면 key 파일과 인증서파일을 이용하여 통합 통합된  PEM 인증서를 PKCS12포맷으로 변환한다

**개인키 → 메인인증서 → 체인인증서 → 루트인증서**

```c
###PEM 통합
[root@localhost ssl]# cat cafe24.com.key cafe24.crt EncryptionEverywhereDVCA.crt DigiCertRoot2.crt.cer > cafe24.pem
 
### OEPNSSL 이용 변환 PEM -> PKCS12
[root@localhost ssl]# openssl pkcs12 -export -out cafe24.pkcs12 -in cafe24.pem 
Enter pass phrase for cafe24.pem:         ---> 처음 key 만들때 입력한 패스워드
Enter Export Password:                    ---> pkcs12 파일 패스워드 새로 입력
Verifying - Enter Export Password:
```



#### KEYTOOL를 이용하여 PKCS12 -> JKS 포맷으로 변환

```c
#keytool을 이용하여 PKCS12 -> JKS 포맷으로 변환
[root@localhost ssl]# keytool -importkeystore -srckeystore cafe24.pkcs12 -srcstoretype pkcs12 -destkeystore cafe24.jks -deststoretype jks
키 저장소 cafe24.pkcs12을(를) cafe24.jks(으)로 임포트하는 중...
대상 키 저장소 비밀번호 입력: 
새 비밀번호 다시 입력: 123456
소스 키 저장소 비밀번호 입력: 123456
1 별칭에 대한 항목이 성공적으로 임포트되었습니다.
임포트 명령 완료: 성공적으로 임포트된 항목은 1개, 실패하거나 취소된 항목은 0개입니다.
```



#### 변환된 JKS 파일 keytool 로 인증서 확인

```c
[root@localhost ssl]# keytool -list -keystore cafe24.jks 
키 저장소 비밀번호 입력: 123456
키 저장소 유형: jks
키 저장소 제공자: SUN
 
키 저장소에 1개의 항목이 포함되어 있습니다.
 
1, 2018. 11. 30, PrivateKeyEntry, 
인증서 지문(SHA1): C5:8C:FC:2B:28:B2:85:C6:0A:D6:3C:19:34:29:91:81:FE:0A:12:80
```



#### Server.xml 설정

tomcat/conf/server.xml 파일의 해당 Connector를 아래와 같이 주석을 해제한 후,

```c
<!-- JKS SSL Password True / HTTP/1.1 / https-openssl-nio protocol -->
<Connector port="443" protocol="org.apache.coyote.http11.Http11NioProtocol" 
 maxHttpHeaderSize="8192" maxThreads="150" 
 enableLookups="false" acceptCount="100" connectionTimeout="20000" 
 disableUploadTimeout="true"
 SSLEnabled="true" scheme="https" secure="true" clientAuth="false" sslProtocol="TLS" 
 keystoreFile="/usr/local/tomcat/conf/ssl/jsp4.xinet.kr.jks" 
 keystorePass="123456"
 />
```

Tomcat 서버를 재시작한다.



### 참조



<https://www.comodossl.co.kr/certificate/ssl-installation-guides/Apache-csr-crt.aspx>

<https://xinet.kr/?p=1674>

<https://opentutorials.org/course/228/4894>
