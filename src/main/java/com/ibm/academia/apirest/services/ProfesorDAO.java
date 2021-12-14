package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.models.entities.Persona;

public interface ProfesorDAO {
  
  Iterable<Persona> findProfesoresByCarrera(String carrera);

}
