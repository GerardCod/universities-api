package com.ibm.academia.apirest.services;

import java.util.List;
import java.util.Optional;

import com.ibm.academia.apirest.models.entities.Direccion;
import com.ibm.academia.apirest.models.entities.Pabellon;
import com.ibm.academia.apirest.repositories.PabellonRepository;

import org.springframework.stereotype.Service;

@Service
public class PabellonDAOImpl implements PabellonDAO {

  private PabellonRepository repository;

  @Override
  public Optional<List<Pabellon>> getPabellonesByDireccion(Direccion direccion) {
    List<Pabellon> result = (List<Pabellon>) repository.findPabellonesByDireccion(direccion);

    if (!result.isEmpty()) {
      return Optional.of(result);
    }

    return Optional.empty();
  }

  @Override
  public Optional<List<Pabellon>> getPabellonesByNombre(String nombre) {
    List<Pabellon> result = (List<Pabellon>) repository.findPabellonesByNombre(nombre);

    if (!result.isEmpty()) {
      return Optional.of(result);
    }

    return Optional.empty();
  }
  
}
