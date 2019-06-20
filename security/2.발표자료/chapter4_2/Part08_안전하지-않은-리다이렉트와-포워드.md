# 안전하지 않은 리다이렉트와 포워드
## 요약
![리다이렉트와 포워드의 특징](https://i2sec.github.io/images/2017-03-30/2.png)

### 검증되지 않은 리다이렉트
`response.sendRedirect("http://www.mysite.com");`

`response.sendRedirect(request.getParameter("url"));`

순서대로 안전한 URL 리다이렉트 예시 코드와, 취약한 URL 리다이렉트 예시 코드이다.

사용자가 넘겨준 매개변수로 각 언어별로 리다이렉트 처리 시 취약점이 발생하여 악의적 URL로 렌더링 될 수 있다는 취약점이다.

### 검증되지 않은 포워드
```java
public class ForwardServlet extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String query = request.getQueryString();
    if(query.contains("fwd")) {
      String fwd = request.getParameter("fwd");
      try {
        request.getRequestDispatcher(fwd).forward(request, response);
      } catch(ServletException e) {
        e.printStackTrace();
      }
    }
  }
}
```

## 참고
- KISA - 홈페이지 취약점 진단·제거 가이드.pdf
- <https://skynarciss.tistory.com/59>
- <https://intadd.tistory.com/100>
