package com.usco.edu.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.usco.edu.entities.Usuario;
import com.usco.edu.service.serviceImpl.UsuarioServiceImpl;

@Component
public class InfoAdicionalToken implements TokenEnhancer{
	
	@Autowired
	private UsuarioServiceImpl usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		Map<String, Object> info = new HashMap<>();
		info.put("info_adicional", "Hola que tal!: ".concat(authentication.getName()));
		info.put("per_codigo", usuario.getVigilante().getCodigo());
		info.put("ucod", usuario.getRole());
		info.put("correo", usuario.getPassword());
		info.put("nombre", usuario.getVigilante().getNombre());
		info.put("apellido", usuario.getVigilante().getApellido());
		info.put("puesto", usuario.getPuestoVigilancia().getCodigo());
		info.put("sede", usuario.getPuestoVigilancia().getSede().getCodigo());
		info.put("subsede", usuario.getPuestoVigilancia().getSubsede().getCodigo());
		
		
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}

}
