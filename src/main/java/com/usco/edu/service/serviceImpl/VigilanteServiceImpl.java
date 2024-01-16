package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IVigilanteDao;
import com.usco.edu.entities.Vigilante;
import com.usco.edu.service.IVigilanteService;

@Service
public class VigilanteServiceImpl implements IVigilanteService {
	
	@Autowired
	private IVigilanteDao vigilanteDao;

	@Override
	public List<Vigilante> obtenerVigilantes() {
		
		return vigilanteDao.obtenerVigilantes();
		
	}

	@Override
	public List<Vigilante> obtenerVigilantesActivos() {
		
		return vigilanteDao.obtenerVigilantesActivos();
		
	}
	
	@Override
	public List<Vigilante> obtenerVigilantesSinAsignacion() {
		
		return vigilanteDao.obtenerVigilantesSinAsignacion();
		
	}

	@Override
	public List<Vigilante> obtenerVigilanteIdentificacion(String id) {
		
		return vigilanteDao.obtenerVigilanteIdentificacion(id);
		
	}

	@Override
	public List<Vigilante> obtenerVigilanteCodigo(int codigo) {
		
		return vigilanteDao.obtenerVigilanteCodigo(codigo);
		
	}
	
}
