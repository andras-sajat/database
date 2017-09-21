package andras.database;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import andras.database.model.Users;

public class UserLayout extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final TextField id;
	private final TextField name;
	private final CheckBox checked;

	public UserLayout(Users user, UserChangeListener cl) {
		setWidth("100%");
		setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
		
		id = new TextField();
		name= new TextField();
		checked = new CheckBox();
		
		Binder<Users> binder = new Binder<>(Users.class);
		/*binder.bindInstanceFields(this);
		*/
		
		// Start by defining the Field instance to use
		binder.forField(id)
			.withConverter(
			    new StringToIntegerConverter("Must enter a number"))
			.bind(Users::getId,Users::setId);
		
		/*binder.forField(checked)
		.withConverter(
		    Boolean::valueOf,
		    Boolean::valueOf,
		    "Must enter a boolean")
		.bind(Users::isChecked,Users::setChecked);*/
		
		binder.bind(checked, Users::isChecked, Users::setChecked);

		binder.bind(name, Users::getName, Users::setName);
		
		binder.setBean(user);
		
		checked.setValue(user.isChecked());
		checked.addValueChangeListener(event-> {
					user.setChecked(checked.getValue());
		});
		
		addComponent(checked);
		addComponent(id);
		addComponentsAndExpand(name);
		
		binder.addValueChangeListener(event->cl.userChanged(user));
		
		
		
	}
}
