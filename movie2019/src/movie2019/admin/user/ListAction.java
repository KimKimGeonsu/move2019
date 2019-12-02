package movie2019.admin.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		UserDAO userdao = new UserDAO();
		List<Users> list = new ArrayList<>();

		int page = 1;
		int limit = 5;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("�Ѿ�� ������ = " + page);

		// �� ����Ʈ ���� �޾ƿɴϴ�.
		int listcount = userdao.getListCount();
		System.out.println("����Ʈ ��" + listcount);

		// ����Ʈ�� �޾ƿɴϴ�.
		list = userdao.getList(page, limit);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);

		int maxpage = (listcount+limit-1)/limit;
		System.out.println("�� ��������="+maxpage);
		
		int startpage=((page-1)/10)*10+1;
		System.out.println("���� �������� ������ ���� ������ �� ="+startpage);
		
		//endpage:���� ������ �׷쿡�� ������ ������ ������ ��([10],[20],[30] ��..)
		int endpage=startpage+10-1;
		System.out.println("���� �������� ������ ������ ������ ��"+endpage);
		
		if(endpage>maxpage) endpage=maxpage;
		request.setAttribute("page", page);//���� ������ ��
		request.setAttribute("maxpage", maxpage);//�ִ� ������ ��
		
		//���� �������� ǥ���� ù ������ ��
		request.setAttribute("startpage", startpage);

		//���� �������� ǥ���� �� ������ ��
		request.setAttribute("endpage", endpage);
		request.setAttribute("listcount", listcount);
	
		//�ش� �������� �� ����� ���� �ִ� ����Ʈ
	//	request.setAttribute("memberlist", list);
		
		
		// �� ��� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
		forward.setPath("Page/AdminPage/UserList/UserList.jsp");
		request.setAttribute("totallist", list);
		return forward; // BoardFrontController.java�� ���ϵ˴ϴ�.
	}

}