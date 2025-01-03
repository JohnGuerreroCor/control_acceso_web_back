package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.PoliticaEstamento;

public interface IPoliticaService {

	public List<PoliticaEstamento> obtenerPoliticaEstamento(String userdb);

	public List<PoliticaEstamento> obtenerPoliticaPorCodigoEstamento(int codigo, String userdb);

}
