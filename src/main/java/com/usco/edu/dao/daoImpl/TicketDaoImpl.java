package com.usco.edu.dao.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.ITicketDao;
import com.usco.edu.entities.Ticket;
import com.usco.edu.resultSetExtractor.TicketSetExtractor;

@Repository
public class TicketDaoImpl implements ITicketDao {
	
	@Autowired
	@Qualifier("JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("JDBCTemplateEjecucion")
	public JdbcTemplate jdbcTemplateEjecucion;
	

	@Override
	public List<Ticket> obtenerTickets(int tipoTicket) {

		String sql = "select *, floor((cast(convert(varchar(8),getdate(),112) as int) - cast(convert(varchar(8), p.per_fecha_nacimiento ,112) as int) ) / 10000) as edad from carnetizacion.ticket_visitante tv "
				+ "left join tercero t on tv.ter_codigo = t.ter_codigo "
				+ "left join persona p on tv.per_codigo = p.per_codigo "
				+ "left join tipo_id ti on p.tii_codigo = ti.tii_codigo "
				+ "left join grupo_sanguineo gs on p.grs_codigo = gs.grs_codigo " 
				+ "inner join sede s on tv.sed_codigo = s.sed_codigo "
				+ "inner join sub_sede ss on tv.sus_codigo = ss.sus_codigo "
				+ "left join bloque b on tv.blo_codigo = b.blo_codigo "
				+ "left join uaa u on tv.uaa_codigo = u.uaa_codigo "
				+ "where tv.tvt_codigo = " + tipoTicket + " order by tv.tiv_codigo desc";
		return jdbcTemplate.query(sql, new TicketSetExtractor());
	}
	
	@Override
	public List<Ticket> obtenerTicketPorTerCodigo(int terCodigo, int tipoTicket) {
		
		String sql = "select top 1 *, floor((cast(convert(varchar(8),getdate(),112) as int) - cast(convert(varchar(8), p.per_fecha_nacimiento ,112) as int) ) / 10000) as edad from carnetizacion.ticket_visitante tv "
				+ "left join tercero t on tv.ter_codigo = t.ter_codigo "
				+ "left join persona p on tv.per_codigo = p.per_codigo "
				+ "left join tipo_id ti on p.tii_codigo = ti.tii_codigo "
				+ "left join grupo_sanguineo gs on p.grs_codigo = gs.grs_codigo " 
				+ "inner join sede s on tv.sed_codigo = s.sed_codigo "
				+ "inner join sub_sede ss on tv.sus_codigo = ss.sus_codigo "
				+ "left join bloque b on tv.blo_codigo = b.blo_codigo "
				+ "left join uaa u on tv.uaa_codigo = u.uaa_codigo "
				+ "where tv.ter_codigo = " + terCodigo + " and tv.tvt_codigo = " + tipoTicket + " order by tv.tiv_codigo desc";
		return jdbcTemplate.query(sql, new TicketSetExtractor());
	}


	@Override
	public List<Ticket> obtenerTicketPorPerCodigo(int perCodigo, int tipoTicket) {
		
		String sql = "select top 1 *, floor((cast(convert(varchar(8),getdate(),112) as int) - cast(convert(varchar(8), p.per_fecha_nacimiento ,112) as int) ) / 10000) as edad from carnetizacion.ticket_visitante tv "
				+ "left join tercero t on tv.ter_codigo = t.ter_codigo "
				+ "left join persona p on tv.per_codigo = p.per_codigo "
				+ "left join tipo_id ti on p.tii_codigo = ti.tii_codigo "
				+ "left join grupo_sanguineo gs on p.grs_codigo = gs.grs_codigo " 
				+ "inner join sede s on tv.sed_codigo = s.sed_codigo "
				+ "inner join sub_sede ss on tv.sus_codigo = ss.sus_codigo "
				+ "left join bloque b on tv.blo_codigo = b.blo_codigo "
				+ "left join uaa u on tv.uaa_codigo = u.uaa_codigo "
				+ "where tv.per_codigo = " + perCodigo + " and tv.tvt_codigo = " + tipoTicket + " order by tv.tiv_codigo desc";
		return jdbcTemplate.query(sql, new TicketSetExtractor());
	}
	
	@Override
	public List<Ticket> obtenerTicketPorIdentificacion(String identificacion) {
		String sql = "select top 1 *, floor((cast(convert(varchar(8),getdate(),112) as int) - cast(convert(varchar(8), p.per_fecha_nacimiento ,112) as int) ) / 10000) as edad from carnetizacion.ticket_visitante tv "
				+ "left join tercero t on tv.ter_codigo = t.ter_codigo "
				+ "left join persona p on tv.per_codigo = p.per_codigo "
				+ "left join tipo_id ti on p.tii_codigo = ti.tii_codigo "
				+ "left join grupo_sanguineo gs on p.grs_codigo = gs.grs_codigo "
				+ "inner join sede s on tv.sed_codigo = s.sed_codigo "
				+ "inner join sub_sede ss on tv.sus_codigo = ss.sus_codigo "
				+ "left join bloque b on tv.blo_codigo = b.blo_codigo "
				+ "left join uaa u on tv.uaa_codigo = u.uaa_codigo "
				+ "where p.per_identificacion = '" + identificacion + "' or t.ter_identificacion = '" + identificacion + "' order by tv.tiv_codigo desc";
		return jdbcTemplate.query(sql, new TicketSetExtractor());
	}

	@Override
	public int registrar(Ticket ticket) {

		String sql = "INSERT INTO carnetizacion.ticket_visitante "
				+ "(ter_codigo, per_codigo, sed_codigo, sus_codigo, tvt_codigo, tiv_tipo_lugar, blo_codigo, uaa_codigo, tiv_fecha_creacion, tiv_fecha_vigencia, tiv_graduado, tiv_qr) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		int result = jdbcTemplateEjecucion.update(sql, new Object[] {
				ticket.getTercero().getCodigo(),
				ticket.getPersona().getCodigo(),
				ticket.getSede().getCodigo(),
				ticket.getSubSede().getCodigo(),
				ticket.getTipo(),
				ticket.getTipoLugar(),
				ticket.getBloque().getCodigo(),
				ticket.getOficina().getCodigo(),
				ticket.getFechaCreacion(), 
				ticket.getFechaVigencia(),
				ticket.getEmailGraduado(),
				ticket.getQr()
				});
		
		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			
			parameter.addValue("terceroCodigo", ticket.getTercero().getCodigo());
			parameter.addValue("personaCodigo", ticket.getPersona().getCodigo());
			parameter.addValue("sedeCodigo", ticket.getSede().getCodigo());
			parameter.addValue("subSedeCodigo", ticket.getSubSede().getCodigo());
			parameter.addValue("ticketTipo", ticket.getTipo());
			parameter.addValue("lugarTipo", ticket.getTipoLugar());
			parameter.addValue("bloqueCodigo", ticket.getBloque().getCodigo());
			parameter.addValue("oficinaCodigo", ticket.getOficina().getCodigo());
			parameter.addValue("fechaCreacion", ticket.getFechaCreacion());
			parameter.addValue("fechaVigencia", ticket.getFechaVigencia());
			parameter.addValue("emailGraduado", ticket.getEmailGraduado());
			parameter.addValue("qrCodigo", ticket.getQr());
			
			return result;

		} catch (Exception e) {

			e.printStackTrace();
			return 0;
		}
		
	}

}
