package andras.database.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import andras.database.model.Users;
import andras.database.repository.UsersRepository;

@RestController
@RequestMapping(value=UsersResource.URI)
public class UsersResource {

	final static String URI = "/rest/users";
	
	@Autowired
	UsersRepository usersRepository;
	
	@GetMapping(value="/all")
	public List<Users> getAll() {
		return usersRepository.findAll();
	}
	
	@PostMapping(value="/load")
	public List<Users> persist(@RequestBody final Users users){
		usersRepository.save(users);
		return usersRepository.findAll();
	}
	
}
