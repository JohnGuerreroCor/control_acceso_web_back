package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Token;

public class TokenInternoRowMapper  implements RowMapper<Token> {
	
	@Override
	public Token mapRow(ResultSet rs, int rowNum) throws SQLException {
		Token tercero = new Token();
		tercero.setCodigo(rs.getInt("isv_codigo"));
		tercero.setModulo(rs.getInt("mod_codigo"));
		tercero.setId(rs.getString("id"));
		tercero.setToken(rs.getString("isv_token"));
		tercero.setIntento(rs.getInt("isv_intento"));
		tercero.setEstado(rs.getInt("cise_codigo"));
		tercero.setIp(rs.getString("isv_ip"));
		tercero.setFechaGeneracion(rs.getDate("isv_fecha_generacion"));
		tercero.setFechaExpiracion(rs.getDate("isv_fecha_expira"));
		tercero.setFechaFinSesion(rs.getDate("isv_fecha_fin_sesion"));
		
		return tercero;
	}

}