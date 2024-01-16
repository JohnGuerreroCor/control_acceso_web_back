package com.usco.edu.restController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.Respuesta;
import com.usco.edu.entities.Token;
import com.usco.edu.service.ITokenInternoService;

@RestController
@RequestMapping(path = "token")
public class TokenInternoRestController {
	
	@Autowired
	private ITokenInternoService tokenService;
	
	@GetMapping(path = "obtener-token/{id}")
	public List<Token> obtenerToken(@PathVariable String id) {
		
		return tokenService.obtenerToken(id);
		
	}
	
	@PostMapping(path = "generar-token")
	public int generar(@RequestBody Token token, HttpServletRequest request) {

		return tokenService.generar(token, request);
		
	}
	
	@PutMapping(path = "actualizar-estado-token")
	public int actualizar(@RequestBody Token token) {
		
		return tokenService.actualizar(token);
		
	}
	
	@GetMapping("enviar-token-email/{token}/{email}/{nombre}")
	public Respuesta enviarToken(@PathVariable String token, @PathVariable String email, @PathVariable String nombre) {
		return tokenService.enviarToken(token, email, nombre);
	}

}
