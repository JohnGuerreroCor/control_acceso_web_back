package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Uaa;
import com.usco.edu.entities.UaaTipo;

public interface IUaaDao {

	public List<Uaa> allUaa(String userdb);

	public List<UaaTipo> uaaTipos(String userdb);

	public List<Uaa> findByName(String userdb, String name);

	public List<Uaa> findBySede(String userdb, int sedeCod);

	public List<Uaa> listUaaUnificadas(String userdb);

	public Uaa findById(String userdb, int UaaCodigo);

	public int getTotalUaabyTipo(String userdb, int tipoUaa);

	public int getTotalUaa(String userdb);

	public int getTotalUaaByName(String userdb, String name);

	public int validarIdDeLaUaa(String userdb, int UaaCodigo);

	public boolean validarUaaDpto(Uaa uaa);

}
