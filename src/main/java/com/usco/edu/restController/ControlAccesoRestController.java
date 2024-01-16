package com.usco.edu.restController;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.ControlAcceso;
import com.usco.edu.service.IControlAccesoService;

@RestController
@RequestMapping(path = "acceso")
public class ControlAccesoRestController {
	
	@Autowired
	IControlAccesoService accesoService;
	
	@GetMapping(path = "obtener-accesos")
	public List<ControlAcceso> obtenerAccesos() {
		return accesoService.obtenerAccesos();
	}
	
	@PostMapping(path = "insertar-acceso")
	public int insertarAcceso(@RequestBody ControlAcceso acceso) {

		return accesoService.insertarAcceso(acceso);
		
	}

}
