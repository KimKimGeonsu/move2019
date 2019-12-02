package movie2019.movie.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertStarRatingAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		MovieDAO movieDAO = new MovieDAO();

		int movieId = Integer.parseInt(request.getParameter("movieId"));
		int movieFace = Integer.parseInt(request.getParameter("movieStar"));
		String userId = "duswl13"; // ���� session ����

		// movieID �ִ��� Ȯ��, ������ �߰�����

		// �������� ���
		int result = movieDAO.InsertStarRating(movieId, movieFace, userId);
		response.getWriter().append(Integer.toString(result));

		return null;
	}

}
