package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Vigilante;

public interface IVigilanteDao {
	
	public List<Vigilante> obtenerVigilantes();
	
	public List<Vigilante> obtenerVigilantesActivos();
	
	public List<Vigilante> obtenerVigilantesSinAsignacion();
	
	public List<Vigilante> obtenerVigilanteIdentificacion(String id);
	
	public List<Vigilante> obtenerVigilanteCodigo(int codigo);

}
