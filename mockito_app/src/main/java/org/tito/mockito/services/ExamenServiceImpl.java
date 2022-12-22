package org.tito.mockito.services;

import org.tito.mockito.models.Examen;
import org.tito.mockito.repositories.IExamenRepository;
import org.tito.mockito.repositories.IPreguntasRepository;

import java.util.List;
import java.util.Optional;

public class ExamenServiceImpl implements IExamenService{
    private IExamenRepository iExamenRepository;
    private IPreguntasRepository iPreguntasRepository;

    public ExamenServiceImpl(IExamenRepository iExamenRepository,
                             IPreguntasRepository iPreguntasRepository ) {

        this.iExamenRepository = iExamenRepository;
        this.iPreguntasRepository = iPreguntasRepository;
    }

    @Override
    public Optional<Examen> findExamenPorNombre(String nombre) {
         return iExamenRepository.findAll()
                .stream()
                .filter(e -> e.getNombre().contains(nombre))
                .findFirst();
    }

    @Override
    public Examen examenPorNombreConPreguntas(String nombre) {
        Optional<Examen> optionalExamen = findExamenPorNombre(nombre);
        Examen examen = null;
        if (optionalExamen.isPresent()){
            examen = optionalExamen.get();
            List<String> preguntas = iPreguntasRepository.
                    findPreguntasExamenId(examen.getId());
            examen.setPreguntas(preguntas);
        }
        return examen;
    }
}
