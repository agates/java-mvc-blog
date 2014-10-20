package agates.blog;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
	public static Content getContentById(String id) throws CustomException {
		
		PreparedStatement stmt = null;
		
		Content content = null;
		User user = null;
		
		try {
			stmt = DB.getConn().prepareStatement(Queries.GET_CONTENT);
		} catch (SQLException e2) {
			throw new CustomException("Unable to create get content statement.", CustomErrorType.sqlError);
		}
		
		try {
			stmt.setString(1, id);
		} catch (SQLException e2) {
			throw new CustomException("Unable to set get content query parameter.", CustomErrorType.sqlError);
		}
		
		ResultSet results;
		try {
			results = stmt.executeQuery();
		} catch (SQLException e2) {
			throw new CustomException("Unable to execute get content query.", CustomErrorType.sqlError);
		}
		
		try {
			while (results.next()) {
				content = new Content();
				user = new User(results.getString(SQLFields.USER_ID));
				content.setId(id);
				content.setUser(user);
				content.setTitle(results.getString(SQLFields.TITLE));
				content.setContent(results.getString(SQLFields.CONTENT));
				content.setDate(results.getDate(SQLFields.TIME_CREATED));
				// TODO get children in separate servlet
				content.setChildren(DBUtil.getContentChildren(id, 5, 0));
			}
		} catch (SQLException e) {
			throw new CustomException("Unable to retrieve get content results.", CustomErrorType.sqlError);
		} finally {
			try {
				results.close();
			} catch (SQLException e) {
				throw new CustomException("Unable to close get content results.", CustomErrorType.sqlError);
			}
		}
		
		return content;
	}
	
public static Boolean checkLogin(String username, String password) throws CustomException {
		PreparedStatement stmt;
		
		try {
			stmt = DB.getConn().prepareStatement(Queries.GET_LOGIN);
		} catch (SQLException e2) {
			throw new CustomException("Unable to create check login statement.", CustomErrorType.sqlError);
		}
		
		try {
			stmt.setString(1, username);
			stmt.setString(2, password);
		} catch (SQLException e2) {
			throw new CustomException("Unable to set check login query parameter.", CustomErrorType.sqlError);
		}
		
		ResultSet results;
		try {
			results = stmt.executeQuery();
		} catch (SQLException e2) {
			throw new CustomException("Unable to execute check login query.", CustomErrorType.sqlError);
		}
		
		try {
			results.next();
			int count = results.getInt(SQLFields.NUM);
			if (count > 0)
				return true;
			return false;
		} catch (SQLException e) {
			throw new CustomException("Unable to retrieve check login results.", CustomErrorType.sqlError);
		} finally {
			try {
				results.close();
			} catch (SQLException e) {
				throw new CustomException("Unable to close check login results.", CustomErrorType.sqlError);
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new CustomException("Unable to close check login statement.", CustomErrorType.sqlError);
			}
		}
	}
	
	public static List<Content> getContentChildren(String id, int depth, int level) throws CustomException {
		PreparedStatement stmt = null;
		try {
			stmt = DB.getConn().prepareStatement(Queries.GET_CHILDREN);
		} catch (SQLException e) {
			throw new CustomException("Unable to create content children statement.", CustomErrorType.sqlError);
		}
		try {
			stmt.setString(1, id);
		} catch (SQLException e) {
			throw new CustomException("Unable to set content children query parameter.", CustomErrorType.sqlError);
		}
		
		List<Content> contents = new ArrayList<Content>();
		Content content = null;
		User user = null;
		
		ResultSet results;
		try {
			results = stmt.executeQuery();
		} catch (SQLException e) {
			throw new CustomException("Unable to execute content children query.", CustomErrorType.sqlError);
		}
		try {
			if (!results.isClosed()) {
				try {
					while (results.next()) {
						if (level < depth) {
							List<Content> children = new ArrayList<Content>();
							content = new Content();
							user = new User(results.getString(SQLFields.USER_ID));
							String childId = results.getString(SQLFields.CONTENT_ID);
							content.setId(childId);
							content.setUser(user);
							content.setTitle(results.getString(SQLFields.TITLE));
							content.setContent(results.getString(SQLFields.CONTENT));
							content.setDate(results.getDate(SQLFields.TIME_CREATED));
							int numChildren = results.getInt("num_children");
							if (numChildren > 0)
								children = DBUtil.getContentChildren(childId, depth, level+1);
							
							content.setChildren(children);
							
							contents.add(content);
						}
					}
				} catch (SQLException e1) {
					// Empty result set
					return contents;
				}
			}
		} catch (SQLException e) {
			throw new CustomException("Unable to check content children results.", CustomErrorType.sqlError);
		} finally {
			try {
				results.close();
			} catch (SQLException e) {
				throw new CustomException("Unable to close content children results.", CustomErrorType.sqlError);
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new CustomException("Unable to close content children statement.", CustomErrorType.sqlError);
			}
		}
		return contents;
	}
	
	public static List<Content> getContentTitles() throws CustomException {
		
		List<Content> contents = new ArrayList<Content>();
		Content content = null;
		
		Statement stmt;
		try {
			stmt = DB.getConn().createStatement();
		} catch (SQLException e2) {
			throw new CustomException("Unable to create content list statement.", CustomErrorType.sqlError);
		}
		
		try {
			ResultSet results = stmt.executeQuery(Queries.GET_TITLES);
			try {
				while (results.next()) {
					content = new Content();
					content.setId(results.getString(SQLFields.CONTENT_ID));
					content.setTitle(results.getString(SQLFields.TITLE));
					content.setContent(results.getString(SQLFields.CONTENT));
					
					contents.add(content);
				}
			} catch (SQLException e) {
				throw new CustomException("Unable to retrieve content list results.", CustomErrorType.sqlError);
			} finally {
				try {
					results.close();
				} catch (SQLException e) {
					throw new CustomException("Unable to close content list results.", CustomErrorType.sqlError);
				}
			}
		} catch (SQLException e1) {
			throw new CustomException("Unable to execute content list query.", CustomErrorType.sqlError);
		}
		
		return contents;
	}
}
