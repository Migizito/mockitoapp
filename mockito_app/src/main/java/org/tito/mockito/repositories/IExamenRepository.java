package org.tito.mockito.repositories;

import org.tito.mockito.models.Examen;

import java.util.List;

public interface IExamenRepository {
    List<Examen> findAll();
}
