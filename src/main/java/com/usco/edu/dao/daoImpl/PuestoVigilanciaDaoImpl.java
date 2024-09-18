package com.usco.edu.dao.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IPuestoVigilanciaDao;
import com.usco.edu.entities.PuestoVigilancia;
import com.usco.edu.entities.PuestoVigilanciaTipo;
import com.usco.edu.resultSetExtractor.PuestoVigilanciaSetExtractor;
import com.usco.edu.resultSetExtractor.PuestoVigilanciaTipoSetExtractor;

@Repository
public class PuestoVigilanciaDaoImpl implements IPuestoVigilanciaDao {

	@Autowired
	@Qualifier("JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<PuestoVigilanciaTipo> obtenerTipoPuesto(String userdb) {

		String sql = "select * from carnetizacion.puesto_vigilancia_tipo pvt where pvt.pvt_estado = 1";
		return jdbcTemplate.query(sql, new PuestoVigilanciaTipoSetExtractor());

	}

	@Override
	public List<PuestoVigilancia> obtenerPuestoVigilancia(String userdb) {

		String sql = "select * from carnetizacion.puesto_vigilancia pv "
				+ "inner join sede s on pv.sed_codigo = s.sed_codigo "
				+ "inner join sub_sede ss on pv.sus_codigo = ss.sus_codigo "
				+ "inner join bloque b on pv.blo_codigo = b.blo_codigo "
				+ "inner join carnetizacion.puesto_vigilancia_tipo pvt on pv.pvt_codigo = pvt.pvt_codigo ";
		return jdbcTemplate.query(sql, new PuestoVigilanciaSetExtractor());

	}

	@Override
	public List<PuestoVigilancia> obtenerPuestoVigilanciaCodigo(int codigo, String userdb) {

		String sql = "select * from carnetizacion.puesto_vigilancia pv "
				+ "inner join sede s on pv.sed_codigo = s.sed_codigo "
				+ "inner join sub_sede ss on pv.sus_codigo = ss.sus_codigo "
				+ "inner join bloque b on pv.blo_codigo = b.blo_codigo "
				+ "inner join carnetizacion.puesto_vigilancia_tipo pvt on pv.pvt_codigo = pvt.pvt_codigo "
				+ "where pv.puv_estado = 1 and pv.puv_codigo = " + codigo + " ";
		return jdbcTemplate.query(sql, new PuestoVigilanciaSetExtractor());

	}

	@Override
	public List<PuestoVigilancia> obtenerPuestoVigilanciaPorBloqueTipo(int subsede, int tipo, String userdb) {

		String sql = "select * from carnetizacion.puesto_vigilancia pv "
				+ "inner join sede s on pv.sed_codigo = s.sed_codigo "
				+ "inner join sub_sede ss on pv.sus_codigo = ss.sus_codigo "
				+ "inner join bloque b on pv.blo_codigo = b.blo_codigo "
				+ "inner join carnetizacion.puesto_vigilancia_tipo pvt on pv.pvt_codigo = pvt.pvt_codigo "
				+ "where pv.puv_estado = 1 and pv.sus_codigo = " + subsede + " and pv.pvt_codigo = " + tipo + " ";
		return jdbcTemplate.query(sql, new PuestoVigilanciaSetExtractor());

	}

}
