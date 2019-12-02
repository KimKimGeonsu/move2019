package movie2019.admin.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserDAO mdao=new UserDAO();
		request.setCharacterEncoding("euc-kr");
		String id=request.getParameter("user_id");
		response.setContentType("text/html;charset=euc-kr");
		PrintWriter out=response.getWriter();
		int result=mdao.delete(id);
		String message = id+"�� ȸ�� ������ ���� �Ǿ����ϴ�.";
		if(result!=1) {
			message="������ ���� �ʾҽ��ϴ�.";
		}
		out.println("<script>");
		out.println("alert('"+message+"');");
		out.println("location.href='user_list.ul'");
		out.println("</script>");

		return null;
	}

}
