# 중요 정보 노출
## 암호화 정책과 안전한 DB 데이터 관리
### 암호화 정책이란?
다음과 같은 내용을 총체적으로 이르는 표현이다.

- 암호화 대상 시스템 정의
- 암호화 대상 정보 정의
- 암호화 정책(부분암호화 적용 여부, 암호 알고리즘 등) 정의
- 암호화 방식(API, Plug-In(UDF Only 포함), Hybrid 등) 정의
- 접근제어, 감사로그 적용 대상 정의

주로 프로그램 개발 역할군 중에서는 프로젝트 책임자(PM)가, 프로그램 개발 단계 중 '분석/설계 단계'에서 정한다.

### 암호화 방식
- 대칭키 암호화
   - 전송하고자 하는 평문을 암호화하고 복호화하는 데 동일한 키를 사용하는 방식
   - 😄 공개 키 암호화 방식에 비해 처리 속도가 빠르고, 암호화의 길이가 공개 키 암호화 방식보다 상대적으로 짧아서 일반적인 정보의 기밀성을 보장하기 위한 용도로 사용된다.
   - 😭 정보 교환 당사자 간에 동일한 키를 공유해야 하므로, 여러 사람과 정보 교환을 해야 하는 경우 많은 키를 유지 관리해야 한다.
   - SEED, ARIA, AES
- 비대칭키 암호화
   - 공개 키 암호화 방식이라고도 하며, 공개 키와 개인 키의 키 쌍이 존재해 평문을 암/복호화하는 데 서로 다른 키를 사용하는 방식이다.
   - 대칭키 암호화 방식의 키 분배나 전자서명 또는 카드번호와 같은 작은 크기의 데이터를 암호화할 때 많이 사용된다.
   - RSA, DSA, KCDSA, ECC, ECKCDSA
- 일방향 암호화
   - 해시함수를 이용해 암호화된 값을 생성
   - 복호화가 불가능함
   - 해시함수는 임의의 길이를 갖는 메시지를 입력해서 고정된 길이의 해시 코드를 생성하며, 동일한 입력 메시지에 대해 항상 동일한 해시값을 생성한다. 해시값만으로는 입력값을 유추하는 데 어려움이 있어 전자서명 체계와 함께 데이터의 무결성을 검증하기 위해 사용된다.
   - 해시함수를 사용하더라도 솔트(salt: 해시함수로 도출된 데이터에 특정한 값을 추가하여 보안성을 강화하는 기법)값을 추가하지 않으면 노출 가능성이 증가한다.
   - SHA

### 암호화 알고리즘 종류
- SHA-224/256/384/512
   - 해시함수 이용
- AES-127/196/256
   - DES의 뒤를 잇는 암호 기술 표준
   - 대칭키 암호화 알고리즘
- RSA
   - MIT에서 개발한 공개키 암호화 알고리즘
   - 소인수분해의 어려움에 안전성의 기반을 둠
- ARIA-127/192/256
   - 대칭키 방식의 국가 암호화 알고리즘
- SEED
   - 순수 국내기술로 개발된 대칭키 암호 알고리즘

### DB 암호화시 안전한 키 관리대책 4가지
1. 암호화 키는 절대로 키 관리 서버를 떠나서는 안 된다.
   - 데이터 암호화에 사용되는 키와 키 자체를 암호화하는 마스터 키 모두 DB 서버의 파일 시스템은 물론 메모리에도 존재하지 않아야 한다. DB서버가 장악됐을 때 암호화 키도 유출될 수 있기 때문이다.
2. 암호화 키는 외부 공격으로부터 키를 안전하게 보호할 수 있는 곳에 보관되어야 한다.
   - 해커가 DB서버를 경우해서 키 관리 서버 자체를 공격할 가능성도 있으므로 키를 안전하게 보호하기 위해 설계된 전용 하드웨어 어플라이언스에 키를 보관하는 것이 안전하다.
3. DB 관리자와 데이터 보안 관리자(암호화키 관리자) 계정은 반드시 분리해서 관리해야 한다.
   - 해커가 DB 관리자 계정을 탈취하면 암호화 키에 대한 접근 권한까지 같이 획득하게 될 가능성을 사전에 방지한다. 두 계정을 별도의 계정으로 관리하여, DB 관리자라 하더라도 암호화된 데이터의 원래 내용을 볼 수 없도록 권한을 제한해야 한다.
4. DB 암호화 적용 시에는 반드시 얼마나 안전한가를 고려해야 한다.
   - 안전성의 기준은 암호화 키를 데이터와 분리하여 관리한다는 것이다.
   - 암호화키 관리의 가장 중요한 원칙은 암호화키와 암호화 데이터를 분리해서 보관해야 하는 것이다.
   - 별도 키 관리 시스템을 구축하고 해당 시스템의 접근통제를 강화할 필요가 있다.

### 데이터베이스 암복호화 방식
- 애플리케이션 자체 암호화
- DB 서버 암호화
- DBMS 자체 암호화
- DBMS 암호화 기능 호출
- 운영체제 암호화

### 자바 프로그램에서 암호화 구현
JCA(Java Cryptography Architecture), JCE(Java Cryptography Extention)

JCA는 `java.security` 패키지에 선언되어 있으며 암호화 처리를 위한 구조를 제시한다. SPI(Service Provider Interface) 등을 통해 사용자가 사용할 암호화 방식을 선택할 수 있는 틀을 제공한다.

JCE는 JCA의 확장으로서 `java.crypto` 패키지에 선언되어 있으며 Cipher, MAC, KeyGenerator 등으로 구성되어 있다. 즉 실제적인 암호화 처리를 제공하기 위한 라이브러리이다.

<br>

## 민감한 데이터 노출
로그인 또는 실명인증 시 민감한 데이터가 평문으로 송수신될 경우, 공격자가 감청(sniffing)을 통해 다른 사용자의 민감한 데이터를 획득할 수 있는 취약점이다. 일반적으로 공격자는 암호화 알고리즘 자체를 공격하지 않고, 키 유출, 중간자 공격, 복호화된 정보, 전송된 정보 가로채기 등을 통한 공격을 수행한다.

보통 다음과 같은 프로세스로 공격이 진행된다.

1. 클라이언트에서 서버에게 민감한 데이터를 요청한다.
2. 서버에서 요청에 대한 응답을 보낸다.
3. 공격자가 서버에서 보낸 응답을 감청한다.
4. 클라이언트가 서버의 응답을 받는다.

암호화 단계의 부재나 취약점이 있는 암호화 방식을 적용했을 경우에 발생하는 문제점으로, 대응 방법은 다음과 같다.

1. 민감한 데이터 전송 시 안전하게 암호화하여 전송한다.
   - 안전한 통신 채널 사용
   - '암호연산' 요구 항목을 충족시키는 암호화 알고리즘이나 암호키 사용
2. 쿠키에 포함되는 중요 정보는 암호화하여 전송한다.
   - 쿠키에는 중요정보가 포함되지 않도록 설계해야 하나, 부득이하게 쿠키에 중요정보가 포함되어야 하는 경우, 반드시 세션 쿠키로 설정되어야 하고, 전달되는 중요 정보는 반드시 암호화하여 전송해야 한다.
3. 보안 서버를 적용한다.
   - 서버와 클라이언트 통신 시 중요정보가 사용되는 구간에 SSL 등 암호화 통신을 적용한다.
   - **SSL(Secure Sockets Layer)** 란 웹 서버 인증, 서버 인증이라 불리는 프로토콜을 말하며, 클라이언트와 서버 간 통신에서 정보를 암호화하기 때문에 감청당하더라도 정보의 내용을 보호할 수 있는 보안 솔루션을 말한다.

비밀번호 정보를 전송해야 하는 경우, 다음과 같이 시큐어 코딩을 적용할 수 있다.

```java
char[] passwordArr = password.toCharArray();
String encrypted = "";

try {
  // 암호화 알고리즘 선택
  MessageDigest md = MessageDigest.getInstance("SHA-512");
  md.reset();
  // UTF-8 인코딩
  md.update(new String(passwordArr).getBytes("UTF-8"));
  // 선택한 알고리즘으로 암호화하고, 암호화된 비밀번호를 인코딩
  encrypted = String.format("%0128x", new BigInteger(1, md.digest()));
} catch(Exception e) {
  ...
}
```

암호화를 사용한다고 해도 반드시 고려해야 할 점이 있다. 솔트 없이 암호문을 생성할 경우, 무작위 공격이나 레인보우 테이블로 원문을 유추해낼 수 있다.

또한, 대칭키 알고리즘에 사용되는 암호키의 길이가 안전한지 체크하고, 대칭키 알고리즘에 사용되는 암호키는 112비트 이상의 길이를 사용하는 것이 안전하다.

비대칭키 알고리즘 역시 마찬가지다. 비대칭키 알고리즘에 사용하는 암호키는 2048비트 이상의 길이를 사용하는 것이 안전하다.

안전하지 않은 알고리즘으로 암호화하였을 때에도 문제가 발생한다. DES 알고리즘의 경우 취약한 알고리즘이며 크랙프로그램을 사용하여 암호화된 데이터를 크랙할 수 있다. 따라서 DES 알고리즘으로 중요한 데이터를 암호화하는 것은 적절하지 않다. AES, SEED, ARIA와 같은 안전한 알고리즘을 사용하자. 암호화시킬 때 암호화 모드도 선택해야 하는데, 암호화 모드에 따라 안전성이나 성능이 달라질 수 있기 때문에 충분한 비교가 필요하다. 현재 가장 안전하게 사용할 수 있는 암호화 모드 방식으로는 CBC모드나 CTR 모드가 있다.

<br>

## 실습
### 시큐어 코딩 페이지
`<input>`태그의 `type` 속성을 `password`로 지정하더라도, 여기에 정보를 적자마자 바로 암호화되는 것이 아니다. 잘 사용하는 방법은 아니지만 서버로부터 정보를 불러와서 `type=password`의 `input`태그 안에 value로 지정해두었을 때, 간단하게 `소스보기`만으로도 내용을 확인할 수 있다.

![value가 지정된 password input form](assets/6/1.PNG)

![소스보기로 정보 확인](assets/6/2.PNG)

### 강의 실습 페이지
프록시 툴을 사용하여 POST 방식으로 보내는 정보도 확인할 수 있다.

이렇게 중요한 정보가 평문으로 서버에 요청되는 것을 방지하기 위한 대책이 HTTPS이다.

![POST 요청에서 패스워드 전달 확인](assets/6/3.PNG)

### 해시 함수 사용
중요한 정보는 서버로 도달할 때에도 안전하게 전송되어야 하지만, 서버 단에 저장될 때에도 원문 그대로 저장되는 건 좋지 않다. 이를 위한 방법 중 하나가 해시 함수를 사용하는 것인데, 솔트 없이 해시 암호문을 생성하면 보안에 취약하다. 이를 보여주기 위한 간단한 실습이다.

```java
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public class HashFunctionSalt {
	public static void main(String[] args) {
		String password = "5048";
		byte[] salt = "salt".getBytes();

		System.out.println("< 솔트 없이 암호화 >");
		bruteForce(getEncryptString(getHashWithoutSalt(password)));

		System.out.println("< 솔트 넣어서 암호화 >");
		bruteForce(getEncryptString(getHashWithSalt(password, salt)));

	}

	// 해시 암호문 문자열로 뽑아내기
	public static String getEncryptString(byte[] encrypt) {
		StringBuilder sb = new StringBuilder("");
		for(int i = 0; i < encrypt.length; i++) {
			String hexString = Integer.toHexString(encrypt[i] & 0xff);
			while(hexString.length() < 2) {
				hexString = "0" + hexString;
			}
			sb.append(hexString);
		}
		return sb.toString();
	}

	// 솔트 없이 암호화
	public static byte[] getHashWithoutSalt(String password) {
		MessageDigest digest;
		byte[] encrypt = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			encrypt = digest.digest(password.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encrypt;
	}

	// 솔트 넣어서 암호화
	public static byte[] getHashWithSalt(String password, byte[] salt) {
		MessageDigest digest;
		byte[] encrypt = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(salt);
			encrypt = digest.digest(password.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encrypt;
	}

	// 무작위 공격
	public static void bruteForce(String encryptPassword) {
		System.out.println("=== 무작위 공격 ===");
		int i = 0;
		for(i = 0; i < 10000; i++) {
			String encryptElement = getEncryptString(getHashWithoutSalt(String.valueOf(i)));
			if(encryptPassword.equals(encryptElement)) {
				System.out.printf("비밀번호 찾음: %d\n해시값: %s\n", i, encryptElement);
				break;
			}
		}
		if(i == 10000) {
			System.out.println("비밀번호 찾지 못함");
		}
		System.out.println("===================\n");
	}

}
```

동작 결과는 다음과 같다.

![해시함수](assets/6/4.PNG)

<br>

## 참고
- KISA - 홈페이지 취약점 진단·제거 가이드.pdf
- 한국인터넷진흥원 - 개인정보의 암호화 조치 안내서.pdf
- <https://www.boannews.com/media/view.asp?idx=45396>
- <https://cocomo.tistory.com/301?category=681263>
- <http://gsinfo.kr/xe/SecureCoding_Board/1639>
- <http://oliviertech.com/ko/java/generate-SHA256--SHA512-hash-from-a-String/>
- <https://okky.kr/article/496801>
- 실무에 바로 적용하는 해킹방어를 위한 JAVA 시큐어코딩
