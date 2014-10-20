package agates.blog;

import java.util.HashSet;
import java.util.Set;

public class CustomExceptionSet {
	private Set<CustomException> errorSet = new HashSet<CustomException>();
	public CustomExceptionSet() {
		return;
	}
	public Set<CustomException> get() {
		return errorSet;
	}
	public void addError(CustomException error) {
		this.errorSet.add(error);
	}

}
