package movie2019.mypage.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie2019.admin.user.UserDAO;
import movie2019.admin.user.Users;
import movie2019.mypage.genres.Genres;

public class UserEditAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("INFO_ID");
		String pass=request.getParameter("INFO_PASS");
		String email=request.getParameter("INFO_EMAIL");
		String phone=request.getParameter("INFO_PHONE");
		String genres=request.getParameter("INFO_GENRES");
		System.out.println("�����׽�Ʈ>>>"+genres);
		Users user=new Users();
		user.setUSER_ID(id);
		user.setUSER_PASS(pass);
		user.setUSER_EMAIL(email);
		user.setUSER_PHONE(phone);
		Genres genre = new Genres();
		genre.setGENRES_ID(genres);
		
		response.setContentType("text/html;charset=euc-kr");
		PrintWriter out=response.getWriter();
		
		UserDAO udao= new UserDAO();
		
		int result =udao.user_update(user);
		out.println("<script>");
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
