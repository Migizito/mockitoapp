package org.tito.mockito.repositories;

import java.util.List;

public interface IPreguntasRepository {
    List<String> findPreguntasExamenId(Long id);
}
