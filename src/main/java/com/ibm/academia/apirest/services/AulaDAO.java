package com.ibm.academia.apirest.services;

import java.util.List;
import java.util.Optional;

import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.models.entities.Aula;

public interface AulaDAO {
  
  Optional<List<Aula>> findAulasByPizarron(Pizarron pizarron);
  
  Optional<List<Aula>> findAulasByPabellonNombre(String nombre);
  
  Optional<Aula> findAulaByNumeroAula(Integer numeroAula);

}
