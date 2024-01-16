package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.Respuesta;
import com.usco.edu.entities.Ticket;

public interface ITicketService {
	
	public List<Ticket> obtenerTickets(int tipoTicket);
	
	public List<Ticket> obtenerTicketPorTerCodigo(int terCodigo, int tipoTicket);
	
	public List<Ticket> obtenerTicketPorPerCodigo(int perCodigo, int tipoTicket);
	
	public List<Ticket> obtenerTicketPorIdentificacion(String identificacion);
	
	int registrar(Ticket ticket);
	
	Respuesta enviarTicketVisitanteEmail(String email, String nombre, String id, String lugar, String registro, String vigencia, String qr);
	
	Respuesta enviarTicketInvitadoEmail(String email, String foto, String nombre, String id, String lugar, String registro, String vigencia, String qr);

}
