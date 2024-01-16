package com.usco.edu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IUsuarioDao;
import com.usco.edu.entities.Usuario;
import com.usco.edu.rowMapper.UsuarioRowMapper;

@Repository
public class UsuarioDaoImpl implements IUsuarioDao{
	
	@Autowired
	@Qualifier("JDBCTemplatePlanesLogin")
	public JdbcTemplate jdbcTemplate;

	@Override
	public Usuario findByUsername(String username) {
		String sql = "select top 1 * from carnetizacion.vigilantes v "
				+ "where v.vig_estado = 1 and v.puv_estado = 1 and v.vig_identificacion = ? ";
		return jdbcTemplate.queryForObject(sql, new Object[] { username }, new UsuarioRowMapper());
	}

	@Override
	public boolean validarUser(String username) {
		int result = 0;
		String sql = "select count(v.vig_codigo) from carnetizacion.vigilantes v "
				+ "where v.vig_estado = 1 and v.puv_estado = 1 and v.vig_identificacion = ?";
		result =  jdbcTemplate.queryForObject(sql, new Object[] { username }, Integer.class);
		return result > 0 ? true : false;
	}

}
