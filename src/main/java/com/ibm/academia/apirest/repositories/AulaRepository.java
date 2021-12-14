package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.models.entities.Aula;

import org.springframework.data.repository.CrudRepository;

public interface AulaRepository extends CrudRepository<Integer, Aula> {
  
  Iterable<Aula> findAulasByPizarron(Pizarron pizarron);

  Iterable<Aula> findAulasByPabellonNombre(String nombre);

  Iterable<Aula> findAulasByNumeroAula(Integer numeroAula);
  
}
