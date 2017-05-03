package gamepackage;

class User {

	String username, password;
	int score;

	User(String username, String password) {

		this.username = username;
		this.password = password;
		this.score = 0;
		
	}
	
	User(String username, String password, int score) {

		this.username = username;
		this.password = password;
		this.score = score;
		
	}

	@Override
	public boolean equals(Object user) {

		if(user instanceof User){
			if (((User) user).username.compareTo(this.username) == 0 && ((User) user).password.compareTo(this.password) == 0){
				return true;
			}
		}
		return false;
		
	}

}
