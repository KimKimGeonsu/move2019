package movie2019.admin.notice;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoticeDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		boolean result=false;
		
		int num=Integer.parseInt(request.getParameter("num"));
		
		NoticeDAO boarddao=new NoticeDAO();
		
		result=boarddao.noticeDelete(num);
		
		ActionForward forward=new ActionForward();
		//���� ó�� ������ ���
		if(result==false) {
			System.out.println("�Խ��� ���� ����");
			forward.setRedirect(false);
			request.setAttribute("message", "�Խ��� ���� �����Դϴ�.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		//���� ó�� ������ ��� - �� ��� ���� ��û�� �����ϴ� �κ��Դϴ�.
		System.out.println("�Խ��� ���� ����");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("alert('���� �Ǿ����ϴ�..');");
		out.println("location.href='NoticeList.bo';");
		out.println("</script>");		
		out.close();
		return null;
	}

}
