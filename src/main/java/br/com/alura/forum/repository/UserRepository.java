package br.com.alura.forum.repository;

import org.springframework.data.repository.Repository;

import br.com.alura.forum.model.User;

public interface UserRepository extends Repository<User, Long> {
	User findByEmail(String email);
}
