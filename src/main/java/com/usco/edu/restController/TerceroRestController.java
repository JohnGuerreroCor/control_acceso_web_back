package com.usco.edu.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.Tercero;
import com.usco.edu.service.ITerceroService;

@RestController
@RequestMapping(path = "tercero")
public class TerceroRestController {
	
	@Autowired
	private ITerceroService terceroService;
	
	@GetMapping(path = "obtener-tercero/{id}")
	public List<Tercero> obtenerTerceroId(@PathVariable String id) {
		
		return terceroService.obtenerTerceroId(id);
		
	}
	
	@PostMapping(path = "registrar-tercero")
	public int registrar(@RequestBody Tercero tercero) {

		return terceroService.registrar(tercero);
		
	}
	
	@PutMapping(path = "actualizar-email-tercero")
	public int actualizar(@RequestBody Tercero tercero) {
		
		return terceroService.actualizar(tercero);
		
	}

}
