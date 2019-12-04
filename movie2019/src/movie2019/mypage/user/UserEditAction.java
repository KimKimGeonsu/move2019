package movie2019.mypage.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie2019.admin.user.UserDAO;
import movie2019.admin.user.Users;
import movie2019.mypage.genres.Genres;
import movie2019.mypage.genres.GenresDAO;

public class UserEditAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		response.setContentType("text/html;charset=euc-kr");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("INFO_ID");
		String pass=request.getParameter("INFO_PASS");
		String email=request.getParameter("INFO_EMAIL");
		String phone=request.getParameter("INFO_PHONE");
		System.out.println("test>>>"+email);
		String[] genres=request.getParameterValues("INFO_GENRES");

		try {
		System.out.println("test>>>"+genres.length);
		if (genres.length < 3||genres.length==0||genres==null) {
			out.println("alert('�帣�� 3�� �̻� �����ϼ���');");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}
		}catch(Exception e) {
			e.printStackTrace();
			out.println("alert('�帣�� 3�� �̻� �����ϼ���');");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}
		Users user=new Users();
		user.setUSER_ID(id);
		user.setUSER_PASS(pass);
		user.setUSER_EMAIL(email);
		user.setUSER_PHONE(phone);
		GenresDAO gdao = new GenresDAO();
		gdao.update_genres(genres, id);

		
		UserDAO udao= new UserDAO();
		
		int result =udao.user_update(user);
		
		//������ �� ��� 
		if(result==1) {
			out.println("alert('ȸ�������� �����Ǿ����ϴ�.');");
			out.println("location.href='user_info.mu';");
		}else{
			out.println("alert('ȸ�� ���� ������ �����߽��ϴ�.');");
			out.println("history.back()");//��й�ȣ�� ������ �ٸ� �����ʹ� �����Ǿ� �ֽ��ϴ�.	
		}
		out.println("</script>");
		out.close();
		return null;
	}

}
