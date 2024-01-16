package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.Vigilante;

public interface IVigilanteService {
	
	public List<Vigilante> obtenerVigilantes();
	
	public List<Vigilante> obtenerVigilantesActivos();
	
	public List<Vigilante> obtenerVigilantesSinAsignacion();
	
	public List<Vigilante> obtenerVigilanteIdentificacion(String id);
	
	public List<Vigilante> obtenerVigilanteCodigo(int codigo);

}
