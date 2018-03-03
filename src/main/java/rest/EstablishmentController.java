package rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Establishment;
import service.EstablishmentService;

@RestController
public class EstablishmentController {
	
	@Autowired
	private EstablishmentService establishmentService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cinemas")
	public List<Establishment> getAllCinemas() {
		return establishmentService.getAllCinemas();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/theatres")
	public List<Establishment> getAllTheatres() {
		return establishmentService.getAllTheatres();
	}
}
