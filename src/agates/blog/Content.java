package agates.blog;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Content {
	private String id;
	private String title = null;
	private String content;
	private User user;
	private Date date;
	private List<Content> children = new ArrayList<Content>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public List<Content> getChildren() {
		return this.children;
	}
	
	public void setChildren(List<Content> children) {
		this.children = children;
	}
	
	public void addChild(Content child) {
		this.children.add(child);
	}
}
