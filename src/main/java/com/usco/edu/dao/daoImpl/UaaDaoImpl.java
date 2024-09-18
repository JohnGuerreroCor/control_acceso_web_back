package com.usco.edu.dao.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IUaaDao;
import com.usco.edu.entities.Uaa;
import com.usco.edu.entities.UaaTipo;
import com.usco.edu.resultSetExtractor.UaaSetExtractor;
import com.usco.edu.resultSetExtractor.UaaSimpleSetExtractor;
import com.usco.edu.resultSetExtractor.UaaTipoSetExtractor;
import com.usco.edu.resultSetExtractor.UaaUnificadaSetExtractor;
import com.usco.edu.rowMapper.UaaRowMapper;

@Repository
public class UaaDaoImpl implements IUaaDao {

	@Autowired
	@Qualifier("JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<Uaa> allUaa(String userdb) {

		String sql = "Select * from uaa ua " + "inner join sede s on s.sed_codigo = ua.sed_codigo "
				+ "inner join uaa_tipo ut on ut.uat_codigo = ua.uat_codigo "
				+ "left join municipio m on m.mun_codigo = ua.mun_codigo  where ua.uaa_estado = 1";
		List<Uaa> uaas = null;

		try {
			uaas = jdbcTemplate.query(sql, new UaaSetExtractor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return uaas;
	}

	@Override
	public List<UaaTipo> uaaTipos(String userdb) {
		// String sql = "select * from uaa_tipo where uat_estado = 1" ;
		String sql = "select * from uaa_tipo ";
		return jdbcTemplate.query(sql, new UaaTipoSetExtractor());
	}

	@Override
	public Uaa findById(String userdb, int UaaCodigo) {

		String sql = "Select * from uaa ua " + "inner join sede s on s.sed_codigo = ua.sed_codigo "
				+ "inner join uaa_tipo ut on ut.uat_codigo = ua.uat_codigo "
				+ "left join municipio m on m.mun_codigo = ua.mun_codigo where uaa_codigo = ? ";
		Uaa uaa = null;
		try {
			uaa = jdbcTemplate.queryForObject(sql, new Object[] { UaaCodigo }, new UaaRowMapper());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return uaa;
	}

	@Override
	public int getTotalUaabyTipo(String userdb, int tipoUaa) {

		String sql = "	select count ( uaa_codigo )  from uaa ua "
				+ "inner join sede s on s.sed_codigo = ua.sed_codigo where uat_codigo = ? ";
		int total = 0;
		try {
			total = jdbcTemplate.queryForObject(sql, new Object[] { tipoUaa }, Integer.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}

	@Override
	public int getTotalUaa(String userdb) {

		String sql = "	select count ( uaa_codigo ) from uaa  where uaa_estado = 1 ";
		int total = 0;
		try {
			total = jdbcTemplate.queryForObject(sql, new Object[] {}, Integer.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}

	@Override
	public List<Uaa> findByName(String userdb, String name) {

		String sql = "Select * from uaa ua " + "inner join sede s on s.sed_codigo = ua.sed_codigo "
				+ "inner join uaa_tipo ut on ut.uat_codigo = ua.uat_codigo "
				+ "left join municipio m on m.mun_codigo = ua.mun_codigo  where ua.uaa_estado = 1 and uaa_nombre like ? ";
		List<Uaa> uaas = null;
		try {
			uaas = jdbcTemplate.query(sql, new Object[] { "%" + name + "%" }, new UaaSetExtractor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uaas;
	}

	@Override
	public int getTotalUaaByName(String userdb, String name) {

		String sql = "select count ( uaa_codigo ) from uaa  where uaa_estado = 1 and uaa_nombre like ?";
		int total = 0;
		try {
			total = jdbcTemplate.queryForObject(sql, new Object[] { "%" + name + "%" }, Integer.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}

	@Override
	public int validarIdDeLaUaa(String userdb, int UaaCodigo) {

		String sql = "Select count ( uaa_codigo ) from uaa ua "
				+ "inner join uaa_tipo ut on ut.uat_codigo = ua.uat_codigo where uaa_codigo = ? ";
		int id = 0;
		try {
			id = jdbcTemplate.queryForObject(sql, new Object[] { UaaCodigo }, Integer.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return id;
	}

	@Override
	public List<Uaa> findBySede(String userdb, int sedeCod) {

		String sql = "Select * FROM uaa ua "
				+ "inner join sede s on s.sed_codigo = ua.sed_codigo Where ua.sed_codigo = ? and uat_codigo = 3  AND uaa_estado = 1 ";
		List<Uaa> uaas = null;
		try {
			uaas = jdbcTemplate.query(sql, new Object[] { sedeCod }, new UaaSimpleSetExtractor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return uaas;
	}

	@Override
	public List<Uaa> listUaaUnificadas(String userdb) {

		String sql = "Select * from uaa ua " + "inner join uaa_tipo ut on ut.uat_codigo = ua.uat_codigo "
				+ " where ua.uaa_estado = 1 and ut.uat_codigo = 24 ";
		List<Uaa> uaas = null;
		try {
			uaas = jdbcTemplate.query(sql, new UaaUnificadaSetExtractor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return uaas;
	}

	@Override
	public boolean validarUaaDpto(Uaa uaa) {
		int result = 0;
		String sql = "select COUNT(uaa_codigo) from uaa where uat_codigo = 2 and uaa_nombre like ? ";
		result = jdbcTemplate.queryForObject(sql, new Object[] { "%" + uaa.getNombre() + "%" }, Integer.class);
		return result > 0 ? true : false;
	}

}
