package com.usco.edu.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.Dia;
import com.usco.edu.entities.Hora;
import com.usco.edu.entities.Horario;
import com.usco.edu.service.IHorarioService;

@RestController
@RequestMapping(path = "horario")
public class HorarioRestController {

	@Autowired
	IHorarioService horarioServie;

	@GetMapping(path = "obtener-dias/{username}")
	public List<Dia> obtenerDias(@PathVariable String username) {

		return horarioServie.obtenerDias(username);

	}

	@GetMapping(path = "obtener-horas/{username}")
	public List<Hora> obtenerHoras(@PathVariable String username) {

		return horarioServie.obtenerHoras(username);

	}

	@GetMapping(path = "obtener-horarios/{username}")
	public List<Horario> obtenerHorarios(@PathVariable String username) {

		return horarioServie.obtenerHorarios(username);

	}

}
