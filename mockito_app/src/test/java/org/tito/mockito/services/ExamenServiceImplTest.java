package org.tito.mockito.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.tito.mockito.models.Examen;
import org.tito.mockito.repositories.IExamenRepository;
import org.tito.mockito.repositories.IPreguntasRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ExamenServiceImplTest {
    IExamenRepository repository;
    IExamenService service;
    IPreguntasRepository iPreguntasRepository;

    @BeforeEach
    void setUp() {
        repository = mock(IExamenRepository.class);
        iPreguntasRepository = mock(IPreguntasRepository.class);
        service = new ExamenServiceImpl(repository, iPreguntasRepository);
    }

    @Test
    void findExamenPorNombre() {

        //Mockito también tiene un método when
        when(repository.findAll()).thenReturn(Datos.EXAMENES);

        //Pruebas Unitarias con JUnit (Todavía no Mockito)
        Optional<Examen> examen = service.findExamenPorNombre("Programación");

        //assertNotNull(examen);
        assertTrue(examen.isPresent());
        assertEquals(1L, examen.get().getId());
        assertEquals("Programación", examen.get().getNombre());


    }
    @Test
    void findExamenPorNombreListaVacia() {
        List<Examen> datos = Collections.emptyList();
        //Mockito también tiene un método when
        when(repository.findAll()).thenReturn(datos);

        //Pruebas Unitarias con JUnit (Todavía no Mockito)
        Optional<Examen> examen = service.findExamenPorNombre("Programación");

        //assertNotNull(examen);
        assertTrue(!examen.isPresent());
        assertEquals(1L, examen.get().getId());
        assertEquals("Programación", examen.get().getNombre());
    }

    @Test
    void testEncontrarExamenConPreguntas() {
        when(repository.findAll()).thenReturn(Datos.EXAMENES);
        //when(iPreguntasRepository.findPreguntasExamenId(1L)).thenReturn(Datos.PREGUNTAS);
        when(iPreguntasRepository.findPreguntasExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
        Examen examen = service.examenPorNombreConPreguntas("Programación");
        //Comenzar las pruebas
        assertEquals(4, examen.getPreguntas().size());
        assertTrue(examen.getPreguntas().contains("Bucles"));
    }
    @Test
    void testEncontrarExamenConPreguntasVerify() {
        when(repository.findAll()).thenReturn(Datos.EXAMENES);
        when(iPreguntasRepository.findPreguntasExamenId(1L)).thenReturn(Datos.PREGUNTAS);
        Examen examen = service.examenPorNombreConPreguntas("Programación");
        //Comenzar las pruebas
        assertEquals(4, examen.getPreguntas().size());
        assertTrue(examen.getPreguntas().contains("Bucles"));
        verify(repository).findAll();
        verify(iPreguntasRepository).findPreguntasExamenId(anyLong());
    }
}