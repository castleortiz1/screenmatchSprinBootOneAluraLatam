package com.aluracursos.screenmatchone.service;

public interface IConvierteDatos {

    <T> T obtenerDatos(String json, Class<T> clase);
}
