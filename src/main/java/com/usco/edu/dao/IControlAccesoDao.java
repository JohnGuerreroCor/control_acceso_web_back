package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.ControlAcceso;

public interface IControlAccesoDao {
	
	public List<ControlAcceso> obtenerAccesos();
	
	public int insertarAcceso(ControlAcceso acceso);

}
