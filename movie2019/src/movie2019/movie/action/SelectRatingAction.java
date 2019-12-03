package movie2019.movie.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie2019.review.db.AllReviewVO;
import movie2019.review.db.ReviewVO;

public class SelectRatingAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		MovieDAO movieDAO = new MovieDAO();

		int movieId = Integer.parseInt(request.getParameter("id"));
		String movieTitle = (String) request.getParameter("title");
		String userId = "duswl13"; // �� �� session

		System.out.println("movieTitle : " + movieTitle);
		// movieID �ִ��� Ȯ��, ������ �߰�����
		boolean isMovieId = movieDAO.isMovieId(movieId, movieTitle);

		if (isMovieId) {

			// ǥ������ ��������
			int face = movieDAO.SelectFaceRating(movieId, userId);
			System.out.println("�� ǥ�� ���� : " + face);
			// �������� ��������
			int star = movieDAO.SelectStarRating(movieId, userId);
			System.out.println("�� ���� ���� : " + star);
			
			//�ش� ���� ����
			int[] movieStar = movieDAO.MovieAllStar(movieId);
			//�ش� ���ƿ� ����
			int[] movieFace = movieDAO.MovieAllLike(movieId);
			
			
			
			// �� ���� ��������
			ReviewVO review = movieDAO.MyReviewRating(movieId, userId);
			if (review != null) {
				System.out.println("�� ���� : " + review.getREVIEW_TITLE());
				System.out.println("�� ���� : " + review.getREVIEW_CONTENT());
			}
			// ����Ʈ ���� 3�� ��������
			ArrayList<AllReviewVO> bestreview = movieDAO.BestReviewRating(movieId);

			ActionForward forward = new ActionForward();

			request.setAttribute("face", face);
			request.setAttribute("star", star);
			request.setAttribute("allstar", movieStar);
			request.setAttribute("allface", movieFace);
			request.setAttribute("review", review);
			request.setAttribute("bestreview", bestreview);

			forward.setRedirect(false);
			forward.setPath("Page/MovieDetail/MovieDetail.jsp");
			return forward;

		}

		return null;

	}

}
