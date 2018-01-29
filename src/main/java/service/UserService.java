package service;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import enums.Role;
import model.User;
import repository.UserRepository;

@Service
public class UserService {

		@Autowired
		private UserRepository userRepo;
		
		public User processRegistrationForm(User user, HttpServletRequest request) {
			
			if(userRepo.findByEmail(user.getEmail()) == null) {
				
				user.setActivated(false);
				user.setRole(Role.DEFAULT);
				user.setConfirmationToken(UUID.randomUUID().toString());
				userRepo.save(user);
				
				String appUrl = request.getScheme()+"://"+request.getServerName()+":8080";
				SimpleMailMessage registrationEmail = new SimpleMailMessage();
				registrationEmail.setTo(user.getEmail());
				registrationEmail.setSubject("Potvrda registracije");
				registrationEmail.setText("Da biste aktivirali svoj nalog posetite link: "+appUrl+"#!/aktivacija/"+user.getConfirmationToken());
				registrationEmail.setFrom("noreply@isaproject.com");
				JavaMailSender sender = new JavaMailSenderImpl();
				sender.send(registrationEmail);
				return user;
			}
			
			return null;
			
		}
		
		public User activateNewUser(String token) {
			
			User u = userRepo.findByConfirmationToken(token);
			if(u != null) {
				u.setActivated(true);
				userRepo.save(u);
				return u;
				
			}
			return null;
		}
		
		public void saveUser(User user) {
			userRepo.save(user);
		}
		
		public User authenticateUser(String email, String password) {
			User user = userRepo.findByEmail(email);
			
			if(user != null) {
				if(user.getPassword().equals(password) && (user.getActivated()))
					return user;
			}
			return null;
		}
				
}
