package movie2019.admin.faq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FAQModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//파라미터로 가져올  한글이 팁痴 않도록 하기 위한 문장입니다.
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		Boolean result = false;
System.out.println("TTTTT");
		//전달받은 파라미터 num 변수에 저장합니다.
		int num=Integer.parseInt(request.getParameter("FAQ_NUMBER"));
		
		FAQDAO faqdao=new FAQDAO();
		FAQVO faq = new FAQVO();

		//비밀번호가 일치하는 경우 수정 내용을 설정합니다.
		faq.setFAQ_NUMBER(num);
		faq.setFAQ_SUBJECT(request.getParameter("FAQ_SUBJECT"));
		faq.setFAQ_CONTENT(request.getParameter("FAQ_CONTENT"));
		
		//DAO에서 수정 메서드 호출하여 수정합니다.
		result = faqdao.FAQModify(faq);
		//수정에 실패한 경우
		if(result==false) {
			System.out.println("게시판 수정 실패");
			forward.setRedirect(false);
			request.setAttribute("message", "게시판 수정 작성 실패입니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		//수정 성공의 경우
		System.out.println("게시판 수정 완료");
		
		forward.setRedirect(true);
		//수정한 글 내용을 확인하기 위해 글 내용 보기 페이지를 경로로 설정합니다.
		forward.setPath("FAQDetailAction.fa?num="+faq.getFAQ_NUMBER());
		
		return forward;
	}
}
