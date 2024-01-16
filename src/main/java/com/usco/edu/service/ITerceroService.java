package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.Tercero;


public interface ITerceroService {
	
	public List<Tercero> obtenerTerceroId(String id);
	
	int registrar(Tercero tercero);
	
	int actualizar(Tercero tercero);

}
