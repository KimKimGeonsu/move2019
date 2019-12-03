package movie2019.mypage.genres;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie2019.mypage.user.Genres;
import movie2019.mypage.user.GenresDAO;

public class USERGenresAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("�׽�����������");

		GenresDAO gdao = new GenresDAO();
		List<Genres> genres=new ArrayList<Genres>();
		System.out.println("�׽�����������");
		String id=request.getParameter("user_id");
		genres=gdao.getGenres(id);
		ActionForward forward = new ActionForward();
		
		if(genres==null) {
			System.out.println("������������ ����");
			forward.setRedirect(false);
			request.setAttribute("message", "������������ �����Դϴ�.");
			forward.setPath("error/error.jsp");
			return null;
		}
		System.out.println("�帣���� �������� ����");
		
		request.setAttribute("genresinfo",genres);
		forward = new ActionForward();
		forward.setRedirect(false);
		
		//�� ���� ���� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
		forward.setPath("Page/MyPage/MyInfo/MyInfo.jsp");
		return forward;
	}


}
