package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IUaaDao;
import com.usco.edu.entities.Uaa;
import com.usco.edu.entities.UaaTipo;
import com.usco.edu.service.IUaaService;

@Service
public class UaaServiceImpl implements IUaaService {

	@Autowired
	private IUaaDao uaaDao;

	@Override
	public List<Uaa> allUaa(String userdb) {
		return uaaDao.allUaa(userdb);
	}

	@Override
	public List<UaaTipo> uaaTipos(String userdb) {
		return uaaDao.uaaTipos(userdb);
	}

	@Override
	public Uaa findById(String userdb, int UaaCodigo) {
		return uaaDao.findById(userdb, UaaCodigo);
	}

	@Override
	public int getTotalUaabyTipo(String userdb, int tipoUaa) {
		return uaaDao.getTotalUaabyTipo(userdb, tipoUaa);
	}

	@Override
	public int getTotalUaa(String userdb) {
		return uaaDao.getTotalUaa(userdb);
	}

	@Override
	public List<Uaa> findByName(String userdb, String name) {
		return uaaDao.findByName(userdb, name);
	}

	@Override
	public int getTotalUaaByName(String userdb, String name) {
		return uaaDao.getTotalUaaByName(userdb, name);
	}

	@Override
	public boolean validarIdDeLaUaa(String userdb, int UaaCodigo) {
		int valor = uaaDao.validarIdDeLaUaa(userdb, UaaCodigo);
		return valor > 0 ? true : false;
	}

	@Override
	public List<Uaa> findBySede(String userdb, int sedeCod) {
		return uaaDao.findBySede(userdb, sedeCod);
	}

	@Override
	public List<Uaa> listUaaUnificadas(String userdb) {
		return uaaDao.listUaaUnificadas(userdb);
	}

	@Override
	public boolean validarUaaDpto(Uaa uaa) {
		return uaaDao.validarUaaDpto(uaa);
	}

}
