package com.usco.edu.restController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.Uaa;
import com.usco.edu.entities.UaaTipo;
import com.usco.edu.service.IUaaService;

@RestController
public class UaaRestController {

	@Autowired
	private IUaaService uaaService;

	@GetMapping("/uaa/uuaAll/{username}")
	public List<Uaa> allUaa(@PathVariable("username") String userdb) {
		return uaaService.allUaa(userdb);
	}

	@GetMapping("/uaa/findBySede/{username}/{sede}")
	public List<Uaa> findBySede(@PathVariable("username") String userdb, @PathVariable("sede") int sed_codigo) {
		return uaaService.findBySede(userdb, sed_codigo);
	}

	@GetMapping("/uaa/listUnificadas/{username}")
	public List<Uaa> listUaaUnificadas(@PathVariable("username") String userdb) {
		return uaaService.listUaaUnificadas(userdb);
	}

	@GetMapping("/uaa/findById/{username}/{uaacodigo}")
	public ResponseEntity<?> findById(@PathVariable("username") String userdb,
			@PathVariable("uaacodigo") int UaaCodigo) {

		Map<String, Object> response = new HashMap<>();
		String cod = "" + UaaCodigo;
		if (uaaService.validarIdDeLaUaa(userdb, UaaCodigo) == false) {
			response.put("mensaje", "La UAA codigo: ".concat(cod.concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		List<Uaa> uaa = new ArrayList<Uaa>();
		uaa.add(uaaService.findById(userdb, UaaCodigo));
		return new ResponseEntity<List<Uaa>>(uaa, HttpStatus.OK);
	}

	@GetMapping("/uaa/uaafindById/{username}/{uaacodigo}")
	public Uaa UaafindById(@PathVariable("username") String userdb, @PathVariable("uaacodigo") int UaaCodigo) {
		return uaaService.findById(userdb, UaaCodigo);
	}

	@GetMapping("/uaa/uuaTipos/{username}")
	public List<UaaTipo> tiposDeUaa(@PathVariable("username") String userdb) {
		return uaaService.uaaTipos(userdb);
	}

	@GetMapping("/uaa/getTotalUaa/{username}/{uaatipo}")
	public int getTotalUaabyTipo(@PathVariable("username") String userdb, @PathVariable("uaatipo") int tipoUaa) {
		return uaaService.getTotalUaabyTipo(userdb, tipoUaa);
	}

	@GetMapping("/uaa/getTotalUaaAll/{username}")
	public int getTotalUaa(@PathVariable("username") String userdb) {
		return uaaService.getTotalUaa(userdb);
	}

	@GetMapping("/uaa/findByName/{username}/{name}")
	public List<Uaa> findByName(@PathVariable("username") String userdb, @PathVariable("name") String name) {
		return uaaService.findByName(userdb, name);
	}

	@GetMapping("/uaa/getTotalUaaByName/{username}/{name}")
	public int getTotalUaaByName(@PathVariable("username") String userdb, @PathVariable("name") String name) {
		return uaaService.getTotalUaaByName(userdb, name);
	}

}
