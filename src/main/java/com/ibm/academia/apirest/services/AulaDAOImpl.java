package com.ibm.academia.apirest.services;

import java.util.List;
import java.util.Optional;

import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.repositories.AulaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AulaDAOImpl extends GenericoDAOImpl<Aula, AulaRepository>  implements AulaDAO {

  @Autowired
  public AulaDAOImpl(AulaRepository repository) {
    super(repository);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<List<Aula>> findAulasByPizarron(Pizarron pizarron) {
    List<Aula> result = (List<Aula>) repository.findAulasByPizarron(pizarron);
    
    if (!result.isEmpty()) {
      return Optional.of(result);
    }

    return Optional.empty();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<List<Aula>> findAulasByPabellonNombre(String nombre) {
    List<Aula> result = (List<Aula>) repository.findAulasByPabellonNombre(nombre);
    
    if (!result.isEmpty()) {
      return Optional.of(result);
    }

    return Optional.empty();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Aula> findAulaByNumeroAula(Integer numeroAula) {
    Aula result = repository.findAulaByNumeroAula(numeroAula);

    if (result != null) {
      return Optional.of(result);
    }

    return Optional.empty();
  }

  @Override
  @Transactional
  public Optional<Aula> update(Aula actual, Aula changed) {
    actual.setMedidas(changed.getMedidas());
    actual.setCantidadPupitres(changed.getCantidadPupitres());
    actual.setNumeroAula(changed.getNumeroAula());
    actual.setPabellon(changed.getPabellon());
    actual.setPizarron(changed.getPizarron());

    Aula result = repository.save(actual);
    return Optional.of(result);
  }
  
}
