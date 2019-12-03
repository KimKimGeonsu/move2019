package movie2019.review.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import movie2019.login.action.ActionForward;
import movie2019.review.db.ReviewDAO;
import movie2019.review.db.ReviewVO;

public class ReviewModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");

		ActionForward forward = new ActionForward();

		ReviewDAO reviewdao = new ReviewDAO();
		ReviewVO reviewvo = new ReviewVO();

		String realFolder = "";

		// WebContent �Ʒ��� �� ������ �����ϼ���.
		String saveFolder = "reviewupload";

		int fileSize = 10 * 1024 * 1024; // ���ε��� ������ �ִ� ������ �Դϴ�. 10MB

		// ���� ���� ��θ� �����մϴ�.
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);

		System.out.println("realFolder = " + realFolder);
		boolean result = false;

		try {
			MultipartRequest multi = null;
			multi = new MultipartRequest(request, realFolder, fileSize, "utf-8", new DefaultFileRenamePolicy());
			// BoardBean ��ü�� �� ��� ������ �Է� ���� �������� �����մϴ�.
			int num = Integer.parseInt(multi.getParameter("BOARD_NUM"));
			String pass = multi.getParameter("BOARD_PASS");

			// �۾��̰� �´��� Ȯ���ϱ� ���� ����� ��� & �Է� ��� ��
			boolean usercheck = reviewdao.isReviewWriter(num, pass);

			// ��й�ȣ�� �ٸ� ���
			if (usercheck == false) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('��й�ȣ�� �ٸ��ϴ�.')");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				return null;
			}

			// ��й�ȣ�� ��ġ�ϴ� ��� ���� ���� �����Ѵ�.
			// BoardBean��ü�� �� ��� ������ �Է¹��� �������� ����.
			reviewdata.setBOARD_PASS(pass);
			reviewdata.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
			reviewdata.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));

			String check = multi.getParameter("check");
			System.out.println("check=" + check);

			if (check != null) { // ���� ���� �״�� ����ϴ� ���
				reviewdata.setBOARD_FILE(check);
			} else { // �ý��ۿ� ���ε�� ���� ���ϸ� ��������
				String filename = multi.getFilesystemName("BOARD_FILE");
				reviewdata.setBOARD_FILE(filename);
			}

			// DAO���� ���� �޼ҵ� ȣ���� �����ϱ�
			result = reviewdao.boardModify(reviewdata);

			// ���� ������ ���
			if (result == false) {
				System.out.println("�Խ��� ���� ����");
				forward.setRedirect(false);
				request.setAttribute("message", "�Խ��� ���� ����");
				forward.setPath("error/error.jsp");
				return forward;
			}
			// �Խñ� ������ ����� �� ���
			System.out.println("���� �Ϸ�!");

			forward.setRedirect(true);
			// ������ �� ���� Ȯ���� ���� �� ���뺸�� �������� ��η� ����
			forward.setPath("BoardDetailAction.bo?num=" + reviewdata.getBOARD_NUM());

			return forward;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
