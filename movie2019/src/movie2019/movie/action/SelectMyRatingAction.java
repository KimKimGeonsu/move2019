package movie2019.movie.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie2019.review.db.ReviewVO;

public class SelectMyRatingAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		MovieDAO movieDAO = new MovieDAO();

		int movieId = Integer.parseInt(request.getParameter("movieId"));
		String movieTitle = (String) request.getParameter("title");
		String userId = "duswl13"; //�� �� session
		
		
		System.out.println("movieTitle : "+ movieTitle);
		// movieID �ִ��� Ȯ��, ������ �߰�����
		boolean isMovieId = movieDAO.isMovieId(movieId, movieTitle);

		if (isMovieId) {
			
			// ǥ������ ��������
			int face = movieDAO.SelectFaceRating(movieId,userId);
			
			// �������� ��������
			int star = movieDAO.SelectStarRating(movieId,userId);
			
			// �� ���� ��������
			ReviewVO review =  movieDAO.MyReviewRating(movieId,userId);
			
			// ����Ʈ ���� 3�� ��������
			ReviewVO[] bestreview =  movieDAO.BestReviewRating(movieId);
						
			
			ActionForward forward = new ActionForward();
			
			request.setAttribute("face",face);
			request.setAttribute("star",star);
			request.setAttribute("review",review);
			request.setAttribute("bestreview",bestreview);
			
			forward.setRedirect(false);
			forward.setPath("Page/MovieDetail/MovieDetail.jsp");
			
		
		} 
		
		
		return null;
	
		
	}

}
