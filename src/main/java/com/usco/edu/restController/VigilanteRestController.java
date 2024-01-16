package com.usco.edu.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.Vigilante;
import com.usco.edu.service.IVigilanteService;

@RestController
@RequestMapping(path = "vigilante")
public class VigilanteRestController {
	
	@Autowired
	IVigilanteService vigilanteService;
	
	@GetMapping(path = "obtener-vigilantes")
	public List<Vigilante> obtenerVigilantes() {
		
		return vigilanteService.obtenerVigilantes();
		
	}
	
	@GetMapping(path = "obtener-vigilantes-activos")
	public List<Vigilante> obtenerVigilantesActivos() {
		
		return vigilanteService.obtenerVigilantesActivos();
		
	}
	
	@GetMapping(path = "obtener-vigilantes-sin-asignacion")
	public List<Vigilante> obtenerVigilantesSinAsignacion() {
		
		return vigilanteService.obtenerVigilantesSinAsignacion();
		
	}
	
	@GetMapping(path = "obtener-vigilantes-identificacion/{id}")
	public List<Vigilante> obtenerVigilanteIdentificacion(@PathVariable String id) {
		
		return vigilanteService.obtenerVigilanteIdentificacion(id);
		
	}
	
	@GetMapping(path = "obtener-vigilante-codigo/{codigo}/{username}")
	public List<Vigilante> obtenerVigilanteCodigo(@PathVariable int codigo) {
		
		return vigilanteService.obtenerVigilanteCodigo(codigo);
		
	}

}
