package agates.blog;

public class CustomException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CustomErrorType type;

	public CustomException(String message, CustomErrorType type) {
		super(message);
		this.setType(type);
	}

	public CustomErrorType getType() {
		return type;
	}

	public void setType(CustomErrorType type) {
		this.type = type;
	}
}
