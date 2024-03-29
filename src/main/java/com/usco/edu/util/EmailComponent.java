package com.usco.edu.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.usco.edu.dto.Email;

@Component
public class EmailComponent {
	
	
	Logger logger = LoggerFactory.getLogger(EmailComponent.class);
	
	public void enviar(Email email) {
		
		this.enviar(email, false);
	}
	
	public void enviar(Email email, boolean lanzarError) {
		
		MimeMessagePreparator messagePreparator = mimeMessage -> {

			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setTo(email.getDestinatario());
			messageHelper.setSubject(email.getAsunto());
			
			String mje = this.build(email);
			messageHelper.setText( mje , true);
		};

		try {

			JavaMailSender emailSender = this.crearJavaMailSender();
			emailSender.send(messagePreparator);

		} catch (MailException e) {
			logger.error(email.toString());
			e.printStackTrace();
			
			if(lanzarError) throw e;
		}
	}
	
	
	private String build(Email email ) throws Exception {

		String plantillaCorreo = this
				.obtenerTextoDeArchivo(new ClassPathResource("static/plantilla_email/notificacion_programa.html").getFile());
		
		plantillaCorreo = plantillaCorreo.replaceAll(":nombrePersona", email.getNombreDestinatario());
		plantillaCorreo = plantillaCorreo.replaceAll(":ruta", email.getHash());
		plantillaCorreo = plantillaCorreo.replaceAll(":textoUno", email.getTextoUno());
		plantillaCorreo = plantillaCorreo.replaceAll(":textoDos", email.getTextoDos());
		plantillaCorreo = plantillaCorreo.replaceAll(":confidencial", email.getConfidencial());
		plantillaCorreo = plantillaCorreo.replaceAll(":titulo", email.getTitulo());
		plantillaCorreo = plantillaCorreo.replaceAll(":textoTres", email.getTextoTres());
		plantillaCorreo = plantillaCorreo.replaceAll(":footer", email.getFooter());
		plantillaCorreo = plantillaCorreo.replaceAll(":programa", email.getPrograma());
		
		
		return plantillaCorreo;
	}
	
	
	
	private String obtenerTextoDeArchivo(File file) {

		FileReader fr = null;
		BufferedReader br = null;
		String contenido = "";
		try {
			fr = new FileReader(file.getPath());
			br = new BufferedReader(fr);
			String linea = "";
			while ((linea = br.readLine()) != null) {
				contenido += linea;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return contenido;
	}
	
	
	
	private JavaMailSender crearJavaMailSender() {

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		UserPass email = this.getEmailRandom();
		// Using gmail
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername(email.user);
		mailSender.setPassword(email.pass);
		mailSender.setDefaultEncoding("UTF-8");

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		mailSender.setJavaMailProperties(javaMailProperties);

		return mailSender;
	}
	
	
	
	//---- metodo para seleccionar un correo de forma aleatoria
	private UserPass getEmailRandom() {
		
		UserPass emails[] = { 
				new UserPass("autenticacion.usco1@usco.edu.co","4untent1c4cion1"),
				new UserPass("autenticacion.usco2@usco.edu.co","4untent1c4cion2"),
				new UserPass("autenticacion.usco3@usco.edu.co","4untent1c4cion3"),
				new UserPass("autenticacion.usco4@usco.edu.co","4untent1c4cion4"),
				new UserPass("autenticacion.usco5@usco.edu.co","4untent1c4cion5"),
				new UserPass("autenticacion.usco6@usco.edu.co","4untent1c4cion6"),
				new UserPass("autenticacion.usco7@usco.edu.co","4untent1c4cion7"),
				new UserPass("autenticacion.usco8@usco.edu.co","4untent1c4cion8"),
				new UserPass("autenticacion.usco9@usco.edu.co","4untent1c4cion9"),
				new UserPass("autenticacion.usco10@usco.edu.co","4untent1c4cion10"),
				new UserPass("autenticacion.usco11@usco.edu.co","4untent1c4cion11"),
				new UserPass("autenticacion.usco12@usco.edu.co","4untent1c4cion12"),
				new UserPass("autenticacion.usco13@usco.edu.co","4untent1c4cion13"),
				};

		Random random = new Random();
		int n = random.nextInt(emails.length);

		UserPass email = emails[n];

		return email;
	}


	//---- clase para para establecer el usuario y contrasena del correo---//
	private class UserPass {
		
		String user;
		String pass;
		
		public UserPass(String user, String pass) {
			this.user = user;
			this.pass = pass;
		}
	}


}
