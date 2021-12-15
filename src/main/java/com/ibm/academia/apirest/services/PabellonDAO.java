package com.ibm.academia.apirest.services;

import java.util.List;
import java.util.Optional;

import com.ibm.academia.apirest.models.entities.Direccion;
import com.ibm.academia.apirest.models.entities.Pabellon;

public interface PabellonDAO {

  Optional<List<Pabellon>> getPabellonesByDireccion(Direccion direccion);

  Optional<List<Pabellon>> getPabellonesByNombre(String nombre);

}
