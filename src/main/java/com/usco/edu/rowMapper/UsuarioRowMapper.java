package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Usuario;


public class UsuarioRowMapper implements RowMapper<Usuario>{

	@Override
	public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
		Usuario user = new Usuario();
		user.setId(rs.getInt("vig_codigo"));
		user.setPassword(rs.getString("vig_email"));
		user.setRole(rs.getString("vig_empresa"));
		user.setUsername(rs.getString("vig_identificacion"));
		user.setState(rs.getInt("vig_estado") > 0 ? true : false);
		user.setVigilante(new VigilanteRowMapper().mapRow(rs, rowNum));
		user.setPuestoVigilancia(new PuestoVigilanciaRowMapper().mapRow(rs, rowNum));
		return user;
	}

}
