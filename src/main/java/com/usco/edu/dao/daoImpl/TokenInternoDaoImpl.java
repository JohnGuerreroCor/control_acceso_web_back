package com.usco.edu.dao.daoImpl;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.ITokenInternoDao;
import com.usco.edu.entities.Token;
import com.usco.edu.resultSetExtractor.TokenInternoSetExtractor;

@Repository
public class TokenInternoDaoImpl implements ITokenInternoDao {
	
	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("JDBCTemplateEjecucion")
	public JdbcTemplate jdbcTemplateEjecucion;

	@Override
	public List<Token> obtenerToken(String id) {
		String sql = "select top 1 * from carnetizacion.inicio_sesion_vigilante isv where isv.id = '" + id + "' and isv.cise_codigo = 1 ORDER by isv_codigo DESC ";
		return jdbcTemplate.query(sql, new TokenInternoSetExtractor());
	}
	

	@Override
	public int generar(Token token) {
		
		String sql = "INSERT INTO carnetizacion.inicio_sesion_vigilante "
				+ "(mod_codigo, id, isv_token, isv_intento, cise_codigo, isv_ip, isv_fecha_expira, isv_fecha_fin_sesion) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
		
		int result = jdbcTemplateEjecucion.update(sql, new Object[] {
				token.getModulo(),
				token.getId(),
				token.getToken(),
				token.getIntento(),
				token.getEstado(),
				token.getIp(),
				token.getFechaExpiracion(),
				token.getFechaFinSesion(),
				});
		
		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			
			parameter.addValue("modulo", token.getModulo());
			parameter.addValue("id", token.getId());
			parameter.addValue("token", token.getToken());
			parameter.addValue("intento", token.getIntento());
			parameter.addValue("estado", token.getEstado());
			parameter.addValue("ip", token.getIp());
			parameter.addValue("fechaExpiracion", token.getFechaExpiracion());
			parameter.addValue("FechaFinSesion", token.getFechaFinSesion(), Types.DATE);
			
			return result;

		} catch (Exception e) {

			e.printStackTrace();
			return 0;
			
		}
	}

	@Override
	public int actualizar(Token token) {
		
		String sql = "UPDATE carnetizacion.inicio_sesion_vigilante "
				+ "SET isv_intento = ? , cise_codigo = ?, isv_fecha_fin_sesion = ? "
				+ "WHERE isv_codigo = ? ;";

		int result = jdbcTemplateEjecucion.update(sql, new Object[] {token.getIntento(),token.getEstado(),token.getFechaFinSesion(), token.getCodigo()});

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("intento", token.getIntento());
			parameter.addValue("estado", token.getEstado());
			parameter.addValue("FechaFinSesion", token.getFechaFinSesion());
			parameter.addValue("codigo", token.getCodigo());

			return result;
			
		} catch (Exception e) {

			e.printStackTrace();
			return 0;
		}
	}


}
