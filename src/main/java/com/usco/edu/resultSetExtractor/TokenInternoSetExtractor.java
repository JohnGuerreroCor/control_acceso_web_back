package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Token;
import com.usco.edu.rowMapper.TokenInternoRowMapper;

public class TokenInternoSetExtractor implements ResultSetExtractor<List<Token>> {
	
	@Override
	public List<Token> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Token> list = new ArrayList<Token>();
		while (rs.next()) {
			list.add(new TokenInternoRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}

}

