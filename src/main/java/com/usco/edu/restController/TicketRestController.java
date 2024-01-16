package com.usco.edu.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.Respuesta;
import com.usco.edu.entities.Ticket;
import com.usco.edu.service.ITicketService;

@RestController
@RequestMapping(path = "ticket")
public class TicketRestController {
	
	@Autowired
	private ITicketService ticketService;
	
	@GetMapping(path = "obtener-tickets/{tipoTicket}")
	public List<Ticket> obtenerTickets(@PathVariable int tipoTicket) {
		
		return ticketService.obtenerTickets(tipoTicket);
		
	}
	
	@GetMapping(path = "obtener-ticket-tercodigo/{codigo}/{tipoTicket}")
	public List<Ticket> obtenerTicketPorTerCodigo(@PathVariable int codigo, @PathVariable int tipoTicket) {
		
		return ticketService.obtenerTicketPorTerCodigo(codigo, tipoTicket);
		
	}
	
	@GetMapping(path = "obtener-ticket-percodigo/{codigo}/{tipoTicket}")
	public List<Ticket> obtenerTicketPorPerCodigo(@PathVariable int codigo, @PathVariable int tipoTicket) {
		
		return ticketService.obtenerTicketPorPerCodigo(codigo, tipoTicket);
		
	}
	
	@GetMapping(path = "obtener-ticket-identificacion/{identificacion}")
	public List<Ticket> obtenerTicketPorIdentificacion(@PathVariable String identificacion) {
		
		return ticketService.obtenerTicketPorIdentificacion(identificacion);
		
	}
	
	@PostMapping(path = "registrar-ticket")
	public int registrar(@RequestBody Ticket ticket) {

		return ticketService.registrar(ticket);
		
	}
	
	@GetMapping("enviar-ticket-visitante-email/{email}/{nombre}/{id}/{lugar}/{registro}/{vigencia}/{qr}")
	public Respuesta enviarTicketVisitanteEmail(@PathVariable String email, @PathVariable String nombre, @PathVariable String id, @PathVariable String lugar, @PathVariable String registro, @PathVariable String vigencia, @PathVariable String qr) {
		return ticketService.enviarTicketVisitanteEmail(email, nombre, id, lugar, registro, vigencia, qr);
	}
	
	@GetMapping("enviar-ticket-invitado-email/{email}/{foto}/{nombre}/{id}/{lugar}/{registro}/{vigencia}/{qr}")
	public Respuesta enviarTicketInvitadoEmail(@PathVariable String email, @PathVariable String foto, @PathVariable String nombre, @PathVariable String id, @PathVariable String lugar, @PathVariable String registro, @PathVariable String vigencia, @PathVariable String qr) {
		return ticketService.enviarTicketInvitadoEmail(email, foto, nombre, id, lugar, registro, vigencia, qr);
	}
	

}
