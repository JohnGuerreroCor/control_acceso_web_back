package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Tercero;

public interface ITerceroDao {
	
	public List<Tercero> obtenerTerceroId(String id);
	
	public int registrar(Tercero tercero);
	
	int actualizar(Tercero tercero);

}
