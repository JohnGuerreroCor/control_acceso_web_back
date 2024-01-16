package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IControlAccesoDao;
import com.usco.edu.entities.ControlAcceso;
import com.usco.edu.service.IControlAccesoService;

@Service
public class ControlAccesoServiceImpl implements IControlAccesoService {
	
	@Autowired
	IControlAccesoDao accesoDao;

	@Override
	public List<ControlAcceso> obtenerAccesos() {
		
		return accesoDao.obtenerAccesos();
		
	}

	@Override
	public int insertarAcceso(ControlAcceso acceso) {
		
		return accesoDao.insertarAcceso(acceso);
		
	}

}
