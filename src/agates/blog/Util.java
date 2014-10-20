package agates.blog;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Util {
	public static String getUserCookie(HttpServletRequest request) {
		String id = "";
		
		for (Cookie c : request.getCookies()) {
			if (c.getName().compareTo(Constants.USER_COOKIE) == 0) {
				id = c.getValue();
			}
		}
		
		return id;
	}
	
	public static Boolean isLoggedIn(HttpServletRequest request) {
		String id = Util.getUserCookie(request);
		
		if (!id.isEmpty()) {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute(Constants.USER_SESSION);
			if (user != null) {
				if (user.getUsername().compareTo(id) == 0) {
					return true;
				}
				else {
					session.invalidate();
				}
			}
		}
		
		return false;
	}
	
	public static Boolean isValid(String parameter) {
		return !parameter.isEmpty() && !parameter.equals(null);
	}
	
	public static void setRequestUser(HttpServletRequest request) {
		if (Util.isLoggedIn(request)) {
			request.setAttribute(Constants.USER_SESSION, request.getSession().getAttribute(Constants.USER_SESSION));
		}
	}
	
	public static String validateString(String parameter) {
		return parameter.trim();
	}
}
