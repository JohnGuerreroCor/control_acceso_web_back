package com.usco.edu.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "carnetizacion.vigilante v")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid")
	private int id;

	@Column(name = "us",unique = true)
	private String username;

	@Column(name="uwd2")
	private String password;
	
	@Column(name = "state")
	private boolean state;

	@Column(name="gru_id")
	private String role;
	
	@Column(name="vig_codigo")
	private Vigilante vigilante;
	
	@Column(name="puv_codigo")
	private PuestoVigilancia puestoVigilancia;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public Vigilante getVigilante() {
		return vigilante;
	}

	public void setVigilante(Vigilante vigilante) {
		this.vigilante = vigilante;
	}
	
	public PuestoVigilancia getPuestoVigilancia() {
		return puestoVigilancia;
	}

	public void setPuestoVigilancia(PuestoVigilancia puestoVigilancia) {
		this.puestoVigilancia = puestoVigilancia;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", password=" + password
				+ ", state=" + state + ", vigilante=" + vigilante + ", puestoVigilancia" + puestoVigilancia + "]";
	}


	private static final long serialVersionUID = 1L;

}
