package com.aluracursos.screenmatchone.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosSerie(

        @JsonAlias("Title") String titulo,

        @JsonAlias("imdbRating") String evaluacion,

        @JsonAlias("totalSeasons") Integer totalTemporadas


) {

//    private static Integer totalDeTemporada;
//
//    public Integer getTotalDeTemporada() {
//        return totalDeTemporada;
//    }
//
//    public void setTotalDeTemporada(Integer totalDeTemporada) {
//        DatosSerie.totalDeTemporada = totalDeTemporada;
//    }
//
//    public Integer totalDeTemporadas () {
//        return totalDeTemporadas != null ? totalDeTemporadas : 0;
//
//    }
}
