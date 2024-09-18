package com.usco.edu.dao.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IHorarioDao;
import com.usco.edu.entities.Dia;
import com.usco.edu.entities.Hora;
import com.usco.edu.entities.Horario;
import com.usco.edu.resultSetExtractor.DiaSetExtractor;
import com.usco.edu.resultSetExtractor.HoraSetExtractor;
import com.usco.edu.resultSetExtractor.HorarioSetExtractor;

@Repository
public class HorarioDaoImpl implements IHorarioDao {

	@Autowired
	@Qualifier("JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<Dia> obtenerDias(String userdb) {

		String sql = "select d.dia_codigo, RTRIM(d.dia_nombre) as dia_nombre, d.dia_posicion_semana from dbo.dia d where d.dia_codigo != 8";
		return jdbcTemplate.query(sql, new DiaSetExtractor());

	}

	@Override
	public List<Hora> obtenerHoras(String userdb) {

		String sql = "select * from dbo.hora h ";
		return jdbcTemplate.query(sql, new HoraSetExtractor());

	}

	@Override
	public List<Horario> obtenerHorarios(String userdb) {

		String sql = "select * from carnetizacion.horario_puesto_vigilancia hpv "
				+ "inner join carnetizacion.puesto_vigilancia pv on hpv.puv_codigo = pv.puv_codigo "
				+ "inner join sede s on pv.sed_codigo = s.sed_codigo "
				+ "inner join sub_sede ss on pv.sus_codigo = ss.sus_codigo "
				+ "inner join bloque b on pv.blo_codigo = b.blo_codigo "
				+ "inner join carnetizacion.puesto_vigilancia_tipo pvt on pv.pvt_codigo = pvt.pvt_codigo "
				+ "inner join dia d on hpv.dia_codigo = d.dia_codigo " + "ORDER BY hpv.puv_codigo, hpv.dia_codigo";
		return jdbcTemplate.query(sql, new HorarioSetExtractor());

	}

}
