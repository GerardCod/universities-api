package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.models.entities.Persona;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends PersonaRepository 
{

  @Query("select p from Profesor join fetch p.carreras c where c.nombre = ?1")
  Iterable<Persona> findProfesoresByCarrera(String carrera);

}