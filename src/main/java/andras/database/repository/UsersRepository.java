package andras.database.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;


import andras.database.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{

	@Transactional
	void deleteByChecked(boolean checked);

}
