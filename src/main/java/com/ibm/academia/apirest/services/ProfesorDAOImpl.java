package com.ibm.academia.apirest.services;

import java.util.Optional;

import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.repositories.PersonaRepository;
import com.ibm.academia.apirest.repositories.ProfesorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProfesorDAOImpl extends PersonaDAOImpl implements ProfesorDAO {

  @Autowired
  public ProfesorDAOImpl(@Qualifier("repositorioProfesores") PersonaRepository repository) {
    super(repository);
  }


  @Override
  public Optional<Iterable<Persona>> findProfesoresByCarrera(String carrera) {
    Iterable<Persona> result = ((ProfesorRepository) repository).findProfesoresByCarrera(carrera);

    if (result != null && result.iterator().hasNext()) {
      return Optional.of(result);
    }

    return Optional.empty();
  }
  
}
