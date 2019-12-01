package movie2019.admin.faq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FAQDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FAQDAO faqdao = new FAQDAO();
		FAQVO faqvo = new FAQVO();

		//�۹�ȣ �Ķ���� ���� num������ �����մϴ�.
		int num=Integer.parseInt(request.getParameter("num"));
		
		//���� ������ DAO���� ���� �� ���� ����� boarddata ��ü�� �����մϴ�.
		faqvo=faqdao.getDetail(num);
		
		ActionForward forward = new ActionForward();
				
		//DAO���� ���� ������ ���� ������ ��� null�� ��ȯ�մϴ�.
		if(faqvo==null) {
			System.out.println("�󼼺��� ����");
			forward.setRedirect(false);
			request.setAttribute("message", "�Խ��� �󼼺��� �����Դϴ�.");
			forward.setPath("error/error.jsp");
			return null;
		}
		System.out.println("�󼼺��� ����");
		
		//boarddata ��ü�� Request ��ü�� �����մϴ�.
		request.setAttribute("faqdata",faqvo);
		forward = new ActionForward();
		forward.setRedirect(false);
		
		//�� ���� ���� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
		forward.setPath("Page/AdminPage/FAQ/FAQView.jsp");
		return forward;
	}

}
