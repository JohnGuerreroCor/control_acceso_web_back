package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Token;

public interface ITokenInternoDao {
	
	public List<Token> obtenerToken(String id);
	
	public int generar(Token token);
	
	int actualizar(Token token);

}
