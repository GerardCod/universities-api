package com.ibm.academia.apirest.services;

import java.util.Optional;

import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.models.entities.Persona;

public interface EmpleadoDAO extends PersonaDAO {
  
  Optional<Iterable<Persona>> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado);

  Optional<Persona> updateEmployee(Persona actual, Persona changed);
}
