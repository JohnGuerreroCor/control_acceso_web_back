package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Ticket;

public interface ITicketDao {
	
	public List<Ticket> obtenerTickets(int tipoTicket);
	
	public List<Ticket> obtenerTicketPorTerCodigo(int terCodigo, int tipoTicket);
	
	public List<Ticket> obtenerTicketPorPerCodigo(int perCodigo, int tipoTicket);
	
	public List<Ticket> obtenerTicketPorIdentificacion(String identificacion);
	
	public int registrar(Ticket ticket);

}
