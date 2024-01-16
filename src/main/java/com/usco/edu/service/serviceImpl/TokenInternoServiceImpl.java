package com.usco.edu.service.serviceImpl;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.ITokenInternoDao;
import com.usco.edu.dto.EmailToken;
import com.usco.edu.entities.Respuesta;
import com.usco.edu.entities.Token;
import com.usco.edu.service.ITokenInternoService;
import com.usco.edu.util.EmailTokenComponent;

@Service
public class TokenInternoServiceImpl implements ITokenInternoService {
	
	@Autowired
	private ITokenInternoDao tokenDao;
	
	@Autowired
	private EmailTokenComponent emailTokenComponent;

	@Override
	public List<Token> obtenerToken(String id) {
		
		return tokenDao.obtenerToken(id);
	}

	@Override
	public int generar(Token token, HttpServletRequest request) {
		String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder codigo = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(caracteres.length());
            codigo.append(caracteres.charAt(index));
        }
        token.setToken(codigo.toString());
        String ipClient = request.getHeader("X-FORWARDED-FOR");
        token.setIp(ipClient);
		return tokenDao.generar(token);
	}

	@Override
	public int actualizar(Token token) {
		return tokenDao.actualizar(token);
	}
	
	@Override
	public Respuesta enviarToken(String token, String email, String nombre) {
		
		System.out.println("Entra por Email");
		
		Respuesta rta = new Respuesta();
		EmailToken emailToken = new EmailToken();
		emailToken.setToken(token);
		emailToken.setEmail(email);
		emailToken.setNombre(nombre);
		
		
		try {
			emailTokenComponent.enviar(emailToken, true);
			rta.setEstado(true);
			return rta;
			
		}catch(Exception e) {
			e.printStackTrace();
			rta.setEstado(false);
			rta.setMensaje("El correo no pudo ser enviado");
			rta.setConsola("El correo no pudo ser enviado. Revisar log");
			return rta;
		}
	}
	
	
}
