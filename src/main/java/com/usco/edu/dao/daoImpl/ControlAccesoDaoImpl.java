package com.usco.edu.dao.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IControlAccesoDao;
import com.usco.edu.entities.ControlAcceso;
import com.usco.edu.resultSetExtractor.ControlAccesoSetExtractor;

@Repository
public class ControlAccesoDaoImpl implements IControlAccesoDao {
	
	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("JDBCTemplateEjecucion")
	public JdbcTemplate jdbcTemplateEjecucion;

	@Override
	public List<ControlAcceso> obtenerAccesos() {
		String sql = "";
		return jdbcTemplate.query(sql, new ControlAccesoSetExtractor());
	}

	@Override
	public int insertarAcceso(ControlAcceso acceso) {
		
		String sql = "INSERT INTO carnetizacion.control_acceso "
				+ "(coa_identificacion, coa_usuario_tipo, puv_codigo, cat_codigo) "
				+ "VALUES(?, ?, ?, ?);";
		
		int result = jdbcTemplateEjecucion.update(sql, new Object[] {
				acceso.getIdentificacion(),
				acceso.getUsuarioTipo(),
				acceso.getPuesto().getCodigo(),
				acceso.getAccesoTipo(),
				});
		
		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			
			parameter.addValue("identificacion", acceso.getIdentificacion());
			parameter.addValue("usuarioTipo", acceso.getUsuarioTipo());
			parameter.addValue("puesto", acceso.getPuesto().getCodigo());
			parameter.addValue("accesoTipo", acceso.getAccesoTipo());
			
			return result;

		} catch (Exception e) {

			e.printStackTrace();
			return 0;
			
		}
	}

}
