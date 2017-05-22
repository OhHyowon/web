package hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * Servlet 클래스 구현
 *  1. public class로 선언
 *  2. javax.servlet.http.HttpServlet를 상속(extends) -> 코어API 아님  => Java EE
 *  3. 매개변수 없는 생성자 필요(no-arg) -> 생성자 없으면 default 생성자
 *  4. doGet() || doPost() 오버라이딩 => 서블릿이 서비스할 내용을 구현한다.
 *  	-요청방식:GET	-요청방식:POST
 *  5. 구현한 Servlet 클래스를 콤캣에 등록
 *  	-> (1) web.xml
 *  	-> (2) Annotation 이용 (Servlet 3.0부터 - Tomcat7)
 */
public class HelloServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
											throws IOException, ServletException {
		Date date = new Date();	// 실행시점 일시
		String current = String.format("%tY-%<tm-%<td %<tH:%<tM:%<tS", date);
		
		/*
		 * SimpleDateFormat :	java.util.Date --> String(format()) 
								String--> Date(parse())
		 *  - yyyy:연도(4자리), MM:월(2자리), dd:일(2자리), HH: 시간(24시간-2자), mm:분(2), ss:초(2)
		 *  
		 */
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current2 = sdf.format(date);
//		Date dd = sdf.parse("2011-03-13 22:30:05");
		
		response.setContentType("text/htmp;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head><title>현재시간</title></head>");
		out.println("<body>");
		out.printf("현재시간1 : %s<br> ", current);
		out.printf("현재시간2 : %s<br> ", current2);
		out.println("</body></html>");
	}
}
