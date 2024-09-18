package com.usco.edu.dao.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IPoliticaDao;
import com.usco.edu.entities.PoliticaEstamento;
import com.usco.edu.resultSetExtractor.PoliticaEstamentoSetExtractor;

@Repository
public class PoliticaDaoImpl implements IPoliticaDao {

	@Autowired
	@Qualifier("JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<PoliticaEstamento> obtenerPoliticaEstamento(String userdb) {
		String sql = "select * from carnetizacion.politica_estamento pe "
				+ "inner join dbo.usuario_tipo u on pe.tus_codigo = u.tus_codigo";
		return jdbcTemplate.query(sql, new PoliticaEstamentoSetExtractor());
	}

	@Override
	public List<PoliticaEstamento> obtenerPoliticaPorCodigoEstamento(int codigo, String userdb) {
		String sql = "select * from carnetizacion.politica_estamento pe "
				+ "inner join dbo.usuario_tipo u on pe.tus_codigo = u.tus_codigo where pe.tus_codigo = " + codigo;
		return jdbcTemplate.query(sql, new PoliticaEstamentoSetExtractor());
	}

}
