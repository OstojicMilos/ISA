package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.User;
import repository.UserRepository;

@Service
public class UserService {

		@Autowired
		private UserRepository userRepo;
		
		public User findByEmail(String email) {
			return userRepo.findByEmail(email);
		}
		
		public User findByConfirmationToken(String token) {
			return userRepo.findByConfirmationToken(token);
		}
		
		public void saveUser(User user) {
			userRepo.save(user);
		}
				
}
