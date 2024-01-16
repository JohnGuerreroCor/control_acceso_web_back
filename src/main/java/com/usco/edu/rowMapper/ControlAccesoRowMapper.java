package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.ControlAcceso;

public class ControlAccesoRowMapper implements RowMapper<ControlAcceso> {
	
	@Override
	public ControlAcceso mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ControlAcceso acceso = new ControlAcceso();
		acceso.setCodigo(rs.getInt("coa_codigo"));
		acceso.setIdentificacion(rs.getString("coa_identificacion"));
		acceso.setUsuarioTipo(rs.getInt("coa_usuario_tipo"));
		acceso.setPuesto(new PuestoVigilanciaRowMapper().mapRow(rs, rowNum));
		acceso.setAccesoTipo(rs.getInt("cat_codigo"));
		acceso.setFecha(rs.getDate("coa_fecha_acceso"));
		return acceso;
		
	}

}
