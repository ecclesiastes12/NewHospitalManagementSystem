package com.hms.modules;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long>{

	/*NB :email refers to the parameter value in @Param("email") */
	//repository method to get user email
	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User findByEmail(@Param("email") String email);
	
	//public Optional<User> findByUserName(String username);
	Optional <User> findByUserName(String username);
}
