package com.ibm.academia.apirest.services;

import java.util.Optional;

import com.ibm.academia.apirest.models.entities.Persona;

public interface ProfesorDAO {
  
  Optional<Iterable<Persona>> findProfesoresByCarrera(String carrera);

}
