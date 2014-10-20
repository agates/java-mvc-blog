package agates.blog;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaveContact
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Login() {
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ViewContent");
		
		if (!Util.isLoggedIn(request))
			dispatcher = getServletContext().getRequestDispatcher("/login.jsp");

		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ViewContent");
		
		if (!Util.isLoggedIn(request)) {
			String username = "";
			String password = "";
			
			username = Util.validateString(request.getParameter("username"));
			password = Util.validateString(request.getParameter("password"));
		
			CustomExceptionSet errors = new CustomExceptionSet();
			
			
			if (!Util.isValid(username))
				errors.addError(new CustomException("Invalid input", CustomErrorType.invalidUsername));
			if (!Util.isValid(password))
				errors.addError(new CustomException("Invalid input", CustomErrorType.invalidPassword));
			
			try {
				if (!DBUtil.checkLogin(username, password))
					errors.addError(new CustomException("Invalid credentials", CustomErrorType.invalidCredentials));
				if (!errors.get().isEmpty()) {
					dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
					request.setAttribute("error", errors);
				} else {
					response.addCookie(new Cookie(Constants.USER_COOKIE, username));
					User user = new User(username);
					request.getSession().setAttribute(Constants.USER_SESSION, user);
					request.setAttribute(Constants.USER_SESSION, user);
				}
			} catch (CustomException e) {
				errors.addError(e);
				request.setAttribute("error", errors);
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
			}
		}

		dispatcher.forward(request, response);
	}
}
