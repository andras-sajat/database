package andras.database;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;

import andras.database.model.Users;
import andras.database.repository.UsersRepository;

@UIScope
@SpringComponent
public class UserList extends VerticalLayout implements UserChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8538292030924099509L;
	@Autowired
	UsersRepository repo;
	
	@PostConstruct
	void init() {
		setWidth("80%");
		update();
	}
	
	private void update() {
		setUsers(repo.findAll());
		
	}

	private void setUsers(List<Users> users) {
		removeAllComponents();
		users.forEach(user -> addComponent(new UserLayout(user, this)));
		
	}
	
	void addUser(Users user) {
		repo.save(user);
		update();
	}

	@Override
	public void userChanged(Users user) {
		addUser(user);		
	}

	public void deleteByChecked(boolean checked) {
		repo.deleteByChecked(true);
		update();
	}



}
