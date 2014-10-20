package agates.blog;

public final class Queries {
	public static final String GET_CHILDREN = String.format(
			"SELECT c1.%s, COALESCE(c1.%s, SUBSTRING(c1.%s, 1, 25)) as %s, c1.%s, c1.%s, c1.%s, COUNT(c2.%s) as num_children "
			+ "FROM CONTENT c1 LEFT JOIN CONTENT c2 ON c1.%s=c2.%s WHERE c1.%s=? "
			+ "GROUP BY c1.%s, c1.%s, c1.%s, c1.%s, c1.%s "
			+ "ORDER BY c1.%s ASC;",
			SQLFields.CONTENT_ID, SQLFields.TITLE, SQLFields.CONTENT, SQLFields.TITLE, SQLFields.USER_ID, SQLFields.TIME_CREATED, SQLFields.CONTENT, SQLFields.CONTENT_ID,
			SQLFields.CONTENT_ID, SQLFields.PARENT_ID, SQLFields.PARENT_ID,
			SQLFields.CONTENT_ID, SQLFields.TITLE, SQLFields.CONTENT, SQLFields.USER_ID, SQLFields.TIME_CREATED,
			SQLFields.TIME_CREATED);
	
	public static final String GET_CONTENT = String.format(
			"SELECT COALESCE(%s, SUBSTRING(%s, 1, 25)) as %s, %s, %s, %s FROM CONTENT WHERE CONTENT.%s=?;",
			SQLFields.TITLE, SQLFields.CONTENT, SQLFields.TITLE, SQLFields.USER_ID, SQLFields.TIME_CREATED, SQLFields.CONTENT, SQLFields.CONTENT_ID);
	
	public static final String GET_TITLES = String.format(
			"SELECT TOP 10 %s, %s, SUBSTRING(%s, 1, 50) AS %s FROM CONTENT WHERE %s IS NULL ORDER BY %s DESC;",
			SQLFields.CONTENT_ID, SQLFields.TITLE, SQLFields.CONTENT, SQLFields.CONTENT, SQLFields.PARENT_ID, SQLFields.TIME_CREATED);

	public static final String GET_LOGIN = String.format(
			"SELECT COUNT(%s) as num FROM LOGIN WHERE %s=? AND %s=?;", SQLFields.USER_ID, SQLFields.USER_ID, SQLFields.USER_PASS);
}
