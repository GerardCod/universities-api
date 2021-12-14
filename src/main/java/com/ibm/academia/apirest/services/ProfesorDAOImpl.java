package com.ibm.academia.apirest.services;

import java.util.Optional;

import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.repositories.ProfesorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesorDAOImpl implements ProfesorDAO {

  @Autowired
  private ProfesorRepository repository;

  @Override
  public Optional<Iterable<Persona>> findProfesoresByCarrera(String carrera) {
    Iterable<Persona> result = repository.findProfesoresByCarrera(carrera);

    if (result != null && result.iterator().hasNext()) {
      return Optional.of(result);
    }

    return Optional.empty();
  }
  
}
