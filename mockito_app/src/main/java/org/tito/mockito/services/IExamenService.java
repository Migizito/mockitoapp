package org.tito.mockito.services;

import org.tito.mockito.models.Examen;

import java.util.Optional;

public interface IExamenService {
  Optional<Examen> findExamenPorNombre(String nombre);
  Examen examenPorNombreConPreguntas(String nombre);
}
