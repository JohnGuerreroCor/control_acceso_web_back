package com.usco.edu.entities;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class ControlAcceso implements Serializable {
	
	 int codigo;
	 String identificacion;
	 int usuarioTipo;
	 PuestoVigilancia puesto;
	 int accesoTipo;
	 Date fecha;
	
	private static final long serialVersionUID = 1L;
	
}
