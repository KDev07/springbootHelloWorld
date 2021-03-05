package com.springboot.helloworldexample.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.helloworldexample.model.Users;

@Repository
public interface UserRepository extends CrudRepository <Users, Long>{

	Optional<Users> findByUsername(String username);
}
