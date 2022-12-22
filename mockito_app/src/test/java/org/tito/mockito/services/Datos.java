package org.tito.mockito.services;

import org.tito.mockito.models.Examen;

import java.util.Arrays;
import java.util.List;

public class Datos {
    public final static List<Examen> EXAMENES = Arrays.asList(
            new Examen(1L,"Programación"),
            new Examen(2L, "Cálculo"),
            new Examen(3L, "Estructura de Datos"));

    public final static List<String> PREGUNTAS = Arrays.asList(
                "Secuenciales",
                "Condicionales",
                "Bucles",
                "Vectores");
}
