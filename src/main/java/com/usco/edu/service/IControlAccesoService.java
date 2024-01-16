package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.ControlAcceso;

public interface IControlAccesoService {
	
	public List<ControlAcceso> obtenerAccesos();
	
	public int insertarAcceso(ControlAcceso acceso);

}
