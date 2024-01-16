package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.ControlAcceso;
import com.usco.edu.rowMapper.ControlAccesoRowMapper;

public class ControlAccesoSetExtractor implements ResultSetExtractor<List<ControlAcceso>> {
	
	@Override
	public List<ControlAcceso> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<ControlAcceso> list = new ArrayList<ControlAcceso>();
		while (rs.next()) {
			list.add(new ControlAccesoRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}
}
