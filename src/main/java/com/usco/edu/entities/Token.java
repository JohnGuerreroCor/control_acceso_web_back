package com.usco.edu.entities;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Token implements Serializable {
	
	int codigo;
	int modulo;
	String id;
	String token;
	int intento;
	int estado;
	String ip;
	Date fechaGeneracion;
	Date fechaExpiracion;
	Date fechaFinSesion;
	
	private static final long serialVersionUID = 1L;
	
}