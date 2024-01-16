package com.usco.edu.dto;

public class EmailToken {
	
	private String token;
	private String email;
	private String nombre;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return "EmailTicket [token=" + token + ", email=" + email + ", nombre=" + nombre +  "]";
	}

}
