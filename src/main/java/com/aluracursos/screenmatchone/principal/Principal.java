package com.aluracursos.screenmatchone.principal;

import com.aluracursos.screenmatchone.model.DatosEpisodio;
import com.aluracursos.screenmatchone.model.DatosSerie;
import com.aluracursos.screenmatchone.model.DatosTemporada;
import com.aluracursos.screenmatchone.model.Episodio;
import com.aluracursos.screenmatchone.service.ConsumoAPI;
import com.aluracursos.screenmatchone.service.ConvierteDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static javax.print.attribute.Size2DSyntax.MM;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private final String URL = "https://www.omdbapi.com/?t=";
    private final String APIKEY = "&apikey=4fc7c187";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    public void muestraElMenu(){
        System.out.println("Escribe el nombre de la série que deseas buscar");
        var nombreSerie = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL + nombreSerie.replace(" ", "+") + APIKEY);
        //https://www.omdbapi.com/?t=game+of+thrones&apikey=4fc7c187
        DatosSerie datos = conversor.obtenerDatos(json, DatosSerie.class);
//        System.out.println(datos);

        List<DatosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= datos.totalTemporadas(); i++) {
            json = consumoAPI.obtenerDatos(URL + nombreSerie.replace(" ", "+") + "&Season=" + i + APIKEY);
            DatosTemporada datosTemporada = conversor.obtenerDatos(json, DatosTemporada.class);
            temporadas.add(datosTemporada);
        }
//        temporadas.forEach(System.out::println);
//
//        for (int i = 0; i < datos.totalTemporadas(); i++) {
//            List<DatosEpisodio> episodiosTemporadas = temporadas.get(i).episodios();
//            for (int j = 0; j < episodiosTemporadas.size(); j++) {
//                System.out.println(episodiosTemporadas.get(j).titulo());
//            }
//        }

        // Mejoria usando funciones Lambda
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        //convertir todas las informaciones a una lista del tipo de DatosEpisodio
        List<DatosEpisodio> datosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .toList();



        // top 5 episodios
        System.out.println("Top 5 episodios");
        datosEpisodios.stream()
                .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
                .peek(e -> System.out.println("Primer filtro (N/A)" + e))
//                .filter(e -> e.numeroEpisodio() != null)
                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
                .peek(e -> System.out.println("Segundo ordenacion (M>m)" + e))
//                .sorted((e1, e2) -> e2.numeroEpisodio().compareTo(e1.numeroEpisodio()))
                .map(e -> e.titulo().toUpperCase())
                .peek(e -> System.out.println("Tercer filtro mayuscula (m>M)"))
                .limit(5)
                .forEach(System.out::println);

        // Convirtiendo los datos a una lista del tipo Episodio
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(),d)))
                .collect(Collectors.toList());

//        episodios.forEach(System.out::println);

        // Busqueda de episodios a partir de x año
//        System.out.println("Por favor indica el año a partir del cual deseas buscar");
//        var fecha = teclado.nextInt();
//        teclado.nextLine();

//        LocalDate fechaBusqueda = LocalDate.of(fecha,1,1);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        episodios.stream()
//                .filter(e -> e.getFechaDeLanzamiento() != null && e.getFechaDeLanzamiento().isAfter(fechaBusqueda))
//                .forEach(e -> System.out.println(
//                        "Temporada " + e.getTemporada() +
//                                "Episodio " + e.getTitulo() +
//                                "Fecha de lanzamiento " + e.getFechaDeLanzamiento().format(dtf)
//
//                ));


        // Busca episodios por pedazo del titulo
        System.out.println("Por favor escribe el titulo del episodio que deseas ver");
        var pedazoTitulo = teclado.nextLine();
        Optional<Episodio> episodioBuscado = episodios.stream()
                .filter(e -> e.getTitulo().toUpperCase().contains(pedazoTitulo.toUpperCase()))
                .findFirst();
        if (episodioBuscado.isPresent()) {
            System.out.println(" Episodio encontrado");
            System.out.println("Los datos son: " + episodioBuscado.get());
        } else {
            System.out.println("Episodio no encontrado");
        }

//                .forEach(e -> System.out.println(
//                        "Temporada " + e.getTemporada() +
//                                "Episodio " + e.getTitulo() +
//                                "Fecha de lanzamiento " + e.getFechaDeLanzamiento().format(dtf)
//                ));

    }
}