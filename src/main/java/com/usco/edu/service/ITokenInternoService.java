package com.usco.edu.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.usco.edu.entities.Respuesta;
import com.usco.edu.entities.Token;

public interface ITokenInternoService {
	
	public List<Token> obtenerToken(String id);
	
	public int generar(Token token, HttpServletRequest request);
	
	int actualizar(Token token);
	
	Respuesta enviarToken(String token, String email, String nombre);

}
