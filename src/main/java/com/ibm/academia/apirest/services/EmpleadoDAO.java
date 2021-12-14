package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.models.entities.Persona;

public interface EmpleadoDAO {
  
  Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado);

}
