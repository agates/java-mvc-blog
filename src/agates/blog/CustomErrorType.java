package agates.blog;

public enum CustomErrorType {
	invalidCredentials {
		@Override
		public String toString() {
			return "Invalid credentials.";
		}
	},
	invalidUsername {
		@Override
		public String toString() {
			return "Invalid username.";
		}
	},
	invalidPassword {
		@Override
		public String toString() {
			return "Invalid password.";
		}
	},
	invalidInput {
		@Override
		public String toString() {
			return "Invalid input.";
		}
	},
	invalidRequest {
		@Override
		public String toString() {
			return "Invalid request.";
		}
	},
	sqlError {
		@Override
		public String toString() {
			return "An error has occured within the database.";
		}
	}
}
