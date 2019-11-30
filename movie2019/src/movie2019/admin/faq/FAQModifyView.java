package movie2019.admin.faq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class FAQModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		FAQDAO faqdao=new FAQDAO();
		FAQVO faq = new FAQVO();

		//�Ķ���ͷ� ���޹��� �亯�� �� ��ȣ�� num ������ �����մϴ�.
		int num=Integer.parseInt(request.getParameter("num"));
		
		//�� ��ȣ num�� �ش��ϴ� ������ �����ͼ� boarddata ��ü�� �����մϴ�.
		faq=faqdao.getDetail(num);
		
		//�� ������ ���� ���
		if(faq==null) {
			System.out.println("���� ������ �̵� ����");
			forward.setRedirect(false);
			request.setAttribute("message", "���� ������ �����Դϴ�.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("���� ������ �̵� �Ϸ�");
		//���� �������� �̵��� �� ���� �� ������ �����ֱ� ����
		//boarddata��ü�� Request ��ü�� �����մϴ�.
		request.setAttribute("faq", faq);
		forward.setRedirect(false);
		//���� ������ ��� �����մϴ�.
		forward.setPath("Page/AdminPage/FAQ/FAQModify.jsp");
		
		return forward;
	}

}
