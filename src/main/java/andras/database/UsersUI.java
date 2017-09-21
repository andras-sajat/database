package andras.database;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import andras.database.model.Users;

@SpringUI
@Theme("valo")
public class UsersUI extends UI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private VerticalLayout layout;
	
	@Autowired
	UserList userList;

	@Override
	protected void init(VaadinRequest request) {
		
		setLayout();
		addHeader();
		addActionButton();
		addForm();
		addUserList();
		
		
		
	}

	private void addForm() {
		HorizontalLayout formLayout = new HorizontalLayout();
		formLayout.setWidth("80%");
		
		TextField name = new TextField();
		name.focus();
		Button addButton = new Button("");
		
		formLayout.addComponentsAndExpand(name);
		formLayout.addComponent(addButton);
		layout.addComponent(formLayout);
		
		addButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
		addButton.setIcon(VaadinIcons.PLUS);
		
		addButton.addClickListener(click -> {
			userList.addUser(new Users(name.getValue()));
			name.setValue("");
			name.focus();
		});
		addButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		
		
	}

	private void addUserList() {
		layout.addComponent(userList);
	}

	private void addHeader() {
		Label label = new Label("Users");
		label.addStyleName(ValoTheme.LABEL_H1);
		
		layout.addComponent(label);
	}

	private void setLayout() {
		layout = new VerticalLayout();
		layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		setContent(layout);
	}
	
	private void addActionButton() {
		Button deleteButton  = new Button("Delete checked items");
		
		deleteButton.addClickListener(click->userList.deleteByChecked(true));
		
		layout.addComponent(deleteButton);
	}

}
