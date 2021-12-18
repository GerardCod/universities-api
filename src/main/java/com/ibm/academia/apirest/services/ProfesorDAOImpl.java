package com.ibm.academia.apirest.services;

import java.util.Optional;


import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.repositories.PersonaRepository;
import com.ibm.academia.apirest.repositories.ProfesorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfesorDAOImpl extends PersonaDAOImpl implements ProfesorDAO {

  @Autowired
  public ProfesorDAOImpl(@Qualifier("repositorioProfesores") PersonaRepository repository) {
    super(repository);
  }


  @Override
  @Transactional(readOnly = true)
  public Optional<Iterable<Persona>> findProfesoresByCarrera(String carrera) {
    Iterable<Persona> result = ((ProfesorRepository) repository).findProfesoresByCarrera(carrera);

    if (result != null && result.iterator().hasNext()) {
      return Optional.of(result);
    }

    return Optional.empty();
  }


  @Override
  @Transactional
  public Optional<Persona> updateProfesor(Persona actualProfesor, Persona profesorChanged) {
    actualProfesor.setNombre(profesorChanged.getNombre());
    actualProfesor.setApellido(profesorChanged.getApellido());
    actualProfesor.setDireccion(profesorChanged.getDireccion());

    Persona personaSaved = repository.save(actualProfesor);
    return Optional.of(personaSaved);
  }
  
}
