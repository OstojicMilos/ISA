package service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import dto.ReservationViewDto;
import enums.Role;
import model.DiscountTicket;
import model.Reservation;
import model.User;
import repository.EventRepository;
import repository.UserRepository;

@Service
public class UserService {

		@Autowired
		private UserRepository userRepository;
		
		@Autowired
		private EventRepository eventRepository;
		
		public User processRegistrationForm(User user, HttpServletRequest request) {
			
			if(userRepository.findByEmail(user.getEmail()) == null) {
				
				user.setActivated(false);
				user.setRole(Role.DEFAULT);
				user.setConfirmationToken(UUID.randomUUID().toString());
				userRepository.save(user);
				
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
			
			User u = userRepository.findByConfirmationToken(token);
			if(u != null) {
				u.setActivated(true);
				userRepository.save(u);
				return u;
				
			}
			return null;
		}
		
		public void saveUser(User user) {
			userRepository.save(user);
		}
		
		public User authenticateUser(String email, String password) {
			User user = userRepository.findByEmail(email);
			
			if(user != null) {
				if(user.getPassword().equals(password) && (user.getActivated()))
					return user;
			}
			return null;
		}
		
		public List<User> searchForUser(String[] criteria) {
			
			List<User> result = new ArrayList<>();
			if(criteria.length > 0) {
				for(int i = 0; i < criteria.length; i++) {
					if(criteria[i].length() > 1) {
						result.addAll(userRepository.findByNameLike("%"+criteria[i]+"%"));
						result.addAll(userRepository.findBySurnameLike("%"+criteria[i]+"%"));
					}
				}
			}
			
			HashSet uniqueSearchResult = new HashSet(result);
			result.clear();
			result.addAll(uniqueSearchResult);
			return result;
		}

		public void updateUserdata(User user) {
			
			User u = userRepository.findByEmail(user.getEmail());
			if(u != null) {
				u.setCity(user.getCity());
				u.setName(user.getName());
				u.setSurname(user.getSurname());
				u.setPhoneNumber(user.getPhoneNumber());
				userRepository.save(u);
			}
			
		}

		public List<ReservationViewDto> getReservationsAsOwner(int userId) {
			User user = userRepository.findOne(userId);
			List<ReservationViewDto> result = new ArrayList<>();
			
			if(user != null) {
				List<Reservation> reservations =  user.getReservations();
				for(Reservation r : reservations) {
					ReservationViewDto dto = new ReservationViewDto();
					dto.setEvent(r.getProjection().getEvent());
					dto.setReservation(r);
					result.add(dto);
				}
			}
			return result;
		}
		
		public List<ReservationViewDto> getReservationsAsGuest(int userId) {
			User user = userRepository.findOne(userId);
			List<ReservationViewDto> result = new ArrayList<>();
			
			if(user != null) {
				List<Reservation> reservations =  user.getReservationsAsGuest();
				for(Reservation r : reservations) {
					ReservationViewDto dto = new ReservationViewDto();
					dto.setEvent(r.getProjection().getEvent());
					dto.setReservation(r);
					result.add(dto);
				}
			}
			return result;
		}

		public List<DiscountTicket> getFastTickets(Integer userId) {
			User user = userRepository.findOne(userId);
			return user.getDiscountedTickets();
		}
				
}
