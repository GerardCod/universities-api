package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.repositories.ProfesorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesorDAOImpl implements ProfesorDAO {

  @Autowired
  private ProfesorRepository repository;

  @Override
  public Iterable<Persona> findProfesoresByCarrera(String carrera) {
    return repository.findProfesoresByCarrera(carrera);
  }
  
}
