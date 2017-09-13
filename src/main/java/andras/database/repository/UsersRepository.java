package andras.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import andras.database.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{

}
