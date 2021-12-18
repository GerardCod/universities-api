package com.ibm.academia.apirest.services;

import java.util.List;
import java.util.Optional;

import com.ibm.academia.apirest.models.entities.Direccion;
import com.ibm.academia.apirest.models.entities.Pabellon;
import com.ibm.academia.apirest.repositories.PabellonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PabellonDAOImpl extends GenericoDAOImpl<Pabellon, PabellonRepository>  implements PabellonDAO {

  @Autowired
  public PabellonDAOImpl(PabellonRepository repository) {
    super(repository);
  }

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

  @Override
  public Optional<Pabellon> updatePabellon(Pabellon actual, Pabellon changed) {

    actual.setNombre(changed.getNombre());
    actual.setMetrosCuadrados(changed.getMetrosCuadrados());
    actual.setDireccion(changed.getDireccion());

    Pabellon result = repository.save(actual);

    return Optional.of(result);
  }
  
}
