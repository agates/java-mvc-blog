package agates.blog;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class ViewContent
 */
@WebServlet("/ViewContent")
public class ViewContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewContent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/blog.jsp");
		
		try {
			String id = request.getParameter("id");
			if (id == null) {
				List<Content> content = DBUtil.getContentTitles();
				request.setAttribute("content", content);
				dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			}
			else {
				Content content = DBUtil.getContentById(id);
				request.setAttribute("content", new Gson().toJson(content));
				//response.setContentType("application/json");
			}
		} catch (CustomException e) {
			CustomExceptionSet errors = new CustomExceptionSet();
			errors.addError(e);
			dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
			request.setAttribute("error", errors);
		}
		
		Util.setRequestUser(request);

		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
