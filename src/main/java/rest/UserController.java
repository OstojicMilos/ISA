package rest;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import enums.Role;
import model.User;
import service.UserService;

@RestController
public class UserController {
	
	@Autowired	
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<User> processRegistrationForm(@Valid @RequestBody User user, HttpServletRequest request) {
		
		if(userService.findByEmail(user.getEmail()) == null) {
			
			user.setActivated(false);
			user.setRole(Role.DEFAULT);
			user.setConfirmationToken(UUID.randomUUID().toString());
			userService.saveUser(user);
			
			String appUrl = request.getScheme()+"://"+request.getServerName()+":8080";
			SimpleMailMessage registrationEmail = new SimpleMailMessage();
			registrationEmail.setTo(user.getEmail());
			registrationEmail.setSubject("Potvrda registracije");
			registrationEmail.setText("Da biste aktivirali svoj nalog posetite link: "+appUrl+"#!/aktivacija/"+user.getConfirmationToken());
			registrationEmail.setFrom("noreply@isaproject.com");
			JavaMailSender sender = new JavaMailSenderImpl();
			sender.send(registrationEmail);
			return ResponseEntity.ok(user);
		}
		
		return null;
	}
	
	@PostMapping("/activate/{token}")
	public boolean activateNewUser(@PathVariable("token") String token) {
		System.out.println("aktivacija");
		System.out.println(token);
		User u = userService.findByConfirmationToken(token);
		if(u != null) {
			System.out.println("uspesna");
			u.setActivated(true);
			userService.saveUser(u);
			return true;
			
		}
		
		return false;
	}

}
