package com.aluracursos.screenmatchone;

import com.aluracursos.screenmatchone.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchoneApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchoneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.muestraElMenu();
//		EjemploStreams ejemploStreams = new EjemploStreams();
//		ejemploStreams.muestraEjemplo();

//		System.out.println("Primer proyecto Spring Boot sin Web");
//		var consumoApi = new ConsumoAPI();
//
//		// Cambiar la URL a una serie en lugar de una película
//		var json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=game+of+thrones&apikey=5a2b07a");
//		System.out.println(json);
//
//		ConvierteDatos conversor = new ConvierteDatos();
//		var datos = conversor.obtenerDatos(json, DatosSerie.class);
//		System.out.println(datos);
//
//		// Obtener información del primer episodio de la primera temporada
//		json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=game+of+thrones&Season=1&episode=1&apikey=5a2b07a");
//		DatosEpisodio episodios = conversor.obtenerDatos(json, DatosEpisodio.class);
//		System.out.println(episodios);

	}
}