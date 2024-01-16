package com.usco.edu.dao.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.ITerceroDao;
import com.usco.edu.entities.Tercero;
import com.usco.edu.resultSetExtractor.TerceroSetExtractor;

@Repository
public class TerceroDaoImpl implements ITerceroDao {
	
	
	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("JDBCTemplateEjecucion")
	public JdbcTemplate jdbcTemplateEjecucion;
	

	@Override
	public List<Tercero> obtenerTerceroId(String id) {

		String sql = "select * from dbo.tercero t where t.ter_identificacion = '" + id + "' and t.ter_borrado = 0";
		return jdbcTemplate.query(sql, new TerceroSetExtractor());
	}


	@Override
	public int registrar(Tercero tercero) {
		
		String sql = "INSERT INTO dbo.tercero "
				+ "(tii_codigo, ter_identificacion, ter_nombre, ter_email, ter_apellido1, ter_apellido2, ter_nombre1, ter_nombre2, ter_borrado, ter_fecha) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		int result = jdbcTemplateEjecucion.update(sql, new Object[] {
				tercero.getTipoDocumento(),
				tercero.getIdentificacion(),
				tercero.getNombreCompleto(),
				tercero.getEmail(),
				tercero.getApellido1(),
				tercero.getApellido2(),
				tercero.getNombre1(),
				tercero.getNombre2(),
				tercero.getEstado(), 
				tercero.getFechaRegistro()
				});
		
		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			
			parameter.addValue("tipoDocumento", tercero.getTipoDocumento());
			parameter.addValue("identificacion", tercero.getIdentificacion());
			parameter.addValue("nombreCompleto", tercero.getNombreCompleto());
			parameter.addValue("email", tercero.getEmail());
			parameter.addValue("apellido1", tercero.getApellido1());
			parameter.addValue("apellido2", tercero.getApellido2());
			parameter.addValue("nombre1", tercero.getNombre1());
			parameter.addValue("nombre2", tercero.getNombre2());
			parameter.addValue("estado", tercero.getEstado());
			parameter.addValue("fechaRegistro", tercero.getFechaRegistro());
			
			return result;

		} catch (Exception e) {

			e.printStackTrace();
			return 0;
			
		}
		
	}
	
	@Override
	public int actualizar(Tercero tercero) {

		String sql = "UPDATE dbo.tercero "
				+ "SET ter_email = ? "
				+ "WHERE ter_codigo = ?;";

		int result = jdbcTemplateEjecucion.update(sql, new Object[] {tercero.getEmail(), tercero.getCodigo()});

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("email", tercero.getEmail());
			parameter.addValue("codigo", tercero.getCodigo());

			return result;
			
		} catch (Exception e) {

			e.printStackTrace();
			return 0;
		}
		
	}

}