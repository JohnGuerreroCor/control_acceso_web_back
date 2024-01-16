package com.usco.edu.dao.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IVigilanteDao;
import com.usco.edu.entities.Vigilante;
import com.usco.edu.resultSetExtractor.VigilanteSetExtractor;

@Repository
public class VigilanteDaoImpl implements IVigilanteDao {
	
	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<Vigilante> obtenerVigilantes() {
		
		String sql = "select * from carnetizacion.vigilante v "
				+ "inner join tipo_id ti on v.tii_codigo = ti.tii_codigo ";
		return jdbcTemplate.query(sql, new VigilanteSetExtractor());
		
	}
	
	@Override
	public List<Vigilante> obtenerVigilantesActivos() {
		
		String sql = "select * from carnetizacion.vigilante v "
				+ "inner join tipo_id ti on v.tii_codigo = ti.tii_codigo "
				+ "where v.vig_estado = 1 ";
		return jdbcTemplate.query(sql, new VigilanteSetExtractor());
		
	}
	
	@Override
	public List<Vigilante> obtenerVigilantesSinAsignacion() {
		
		String sql = "SELECT * FROM carnetizacion.vigilante v "
				+ "inner join tipo_id ti on v.tii_codigo = ti.tii_codigo "
				+ "WHERE NOT EXISTS ( SELECT * FROM carnetizacion.asignacion_puesto ap WHERE v.vig_codigo = ap.vig_codigo and ap.asp_estado != 0 ); ";
		return jdbcTemplate.query(sql, new VigilanteSetExtractor());
		
	}


	@Override
	public List<Vigilante> obtenerVigilanteIdentificacion(String id) {
		
		String sql = "select * from carnetizacion.vigilante v "
				+ "inner join tipo_id ti on v.tii_codigo = ti.tii_codigo "
				+ "where v.vig_identificacion = '"+ id +"' and v.vig_estado = 1 ";
		return jdbcTemplate.query(sql, new VigilanteSetExtractor());
		
	}


	@Override
	public List<Vigilante> obtenerVigilanteCodigo(int codigo) {
		
		String sql = "select * from carnetizacion.vigilante v "
				+ "inner join tipo_id ti on v.tii_codigo = ti.tii_codigo "
				+ "where v.vig_codigo = '"+ codigo +"' and v.vig_estado = 1 ";
		return jdbcTemplate.query(sql, new VigilanteSetExtractor());
		
	}

}
