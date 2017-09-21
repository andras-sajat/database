package andras.database;

import andras.database.model.Users;

public interface UserChangeListener {
	void userChanged(Users user);
}
