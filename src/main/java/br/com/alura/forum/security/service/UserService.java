package br.com.alura.forum.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alura.forum.model.User;
import br.com.alura.forum.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> possibleUser = userRepository.findByEmail(username);
		 
		 return possibleUser.orElseThrow(() -> new UsernameNotFoundException(
				 "Não foi possível encontrar o usuário com email: " + username));

	}

	public UserDetails loadUserById(Long id) {
		 Optional<User> possibleUser = userRepository.findById(id);
		 
		 return possibleUser.orElseThrow(() -> new UsernameNotFoundException(
				 "Não foi possível encontrar o usuário com id: " + id));

	}

}
