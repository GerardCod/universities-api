package com.ibm.academia.apirest.services;

import java.util.Optional;

import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.repositories.EmpleadoRepository;
import com.ibm.academia.apirest.repositories.PersonaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoDAOImpl extends PersonaDAOImpl implements EmpleadoDAO {

  @Autowired
  public EmpleadoDAOImpl(@Qualifier("empleadoRepository") PersonaRepository repository) {
    super(repository);
  }


  @Override
  public Optional<Iterable<Persona>> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado) {
    Iterable<Persona> result = ((EmpleadoRepository) repository).findEmpleadoByTipoEmpleado(tipoEmpleado);

    if (result != null && result.iterator().hasNext()) {
      return Optional.of(result);
    }

    return Optional.empty();
  }

  @Override
  public Optional<Persona> updateEmployee(Persona actual, Persona changed) {
    actual.setNombre(changed.getNombre());
    actual.setApellido(changed.getApellido());
    actual.setDireccion(changed.getDireccion());

    Persona result = repository.save(actual);
    return Optional.of(result);
  }
  
}
