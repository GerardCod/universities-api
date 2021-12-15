package com.ibm.academia.apirest.services;

import java.util.List;
import java.util.Optional;

import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.repositories.AulaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AulaDAOImpl implements AulaDAO {

  @Autowired
  private AulaRepository repository;

  @Override
  public Optional<List<Aula>> findAulasByPizarron(Pizarron pizarron) {
    List<Aula> result = (List<Aula>) repository.findAulasByPizarron(pizarron);
    
    if (!result.isEmpty()) {
      return Optional.of(result);
    }

    return Optional.empty();
  }

  @Override
  public Optional<List<Aula>> findAulasByPabellonNombre(String nombre) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Optional<List<Aula>> findAulasByNumeroAula(Integer numeroAula) {
    // TODO Auto-generated method stub
    return null;
  }
  
}
