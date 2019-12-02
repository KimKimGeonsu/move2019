package movie2019.review.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import movie2019.login.action.ActionForward;
import movie2019.review.db.ReviewDAO;
import movie2019.review.db.ReviewVO;



public class ReviewAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ReviewDAO reviewdao = new ReviewDAO();
		ReviewVO reviewvo = new ReviewVO();
		ActionForward forward = new ActionForward();

		String realFolder = "";

		String saveFolder = "reviewupload"; // WebContent�Ʒ� ���� ����.

	
		// ���� ���� ��� ����.		
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);

		System.out.println("realFolder=" + realFolder);
		boolean result = false;

		try {
			MultipartRequest multi = null;
			multi = new MultipartRequest(request, realFolder,  
						"UTF-8", new DefaultFileRenamePolicy());

			// �� ��� ������ �Է¹��� �������� BoardBean��ü�� ����.
			reviewdata.setBOARD_NAME(multi.getParameter("BOARD_NAME"));
			reviewdata.setBOARD_PASS(multi.getParameter("BOARD_PASS"));
			reviewdata.setBOARD_SUBJECT(replaceParameter(multi.getParameter("BOARD_SUBJECT")));
			reviewdata.setBOARD_CONTENT(replaceParameter(multi.getParameter("BOARD_CONTENT")));


			// �� ��� ó���� ���� DAO�� boardInsert()�޼ҵ带 ȣ���Ѵ�.
			// �� ��� ������ �Է��� ������ ����� �ִ� reviewdata��ü�� ����.
			result = ReviewDAO.reviewInsert(reviewdata);

			// �� ��Ͽ� ������ ��� null�� ��ȯ.
			if (result == false) {
				System.out.println("�Խ��� ��� ����");
				forward.setRedirect(false);
				request.setAttribute("message", "�Խ��� ��� ���д�.");
				forward.setPath("error/error.jsp");
				return forward;
			}
			System.out.println("�Խ��� ��� �Ϸ�.");
			// �� ��� �Ϸ�Ǹ� �� ����� �����ֱ� ���� Redirect���θ� true�� ����
			forward.setRedirect(true);
			forward.setPath("BoardList.bo");
			return forward;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}
	private String replaceParameter(String param) {
		String result = param;
		if (param != null) {
			result = result.replaceAll("<", "&lt;");
			result = result.replaceAll(">", "&gt;");
			result = result.replaceAll("\"", "&quot;");
		}
		return result;
	}
}
