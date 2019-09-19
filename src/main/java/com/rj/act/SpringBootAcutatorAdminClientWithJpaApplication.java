package com.rj.act;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@SpringBootApplication
public class SpringBootAcutatorAdminClientWithJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAcutatorAdminClientWithJpaApplication.class, args);
	}

}
@Data
@Entity
@Table(name="user111")
class User{
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String password;
}

interface UserRepo extends JpaRepository<User, Integer>{
	
}
@RestController
class UserRestController{
	@Autowired
	private UserRepo repo;
	
	@GetMapping("/user")
	public String getUser(@PathVariable int id) {
		Optional<User> getUser = repo.findById(id);
		User user=getUser.get();
		return user.toString();
	}
	@PostMapping("/save")
	public String saveUser(@RequestBody User user) {
		User user2=repo.save(user);
		
		return "User Addded with Id::"+user2.getId();
	}
}
