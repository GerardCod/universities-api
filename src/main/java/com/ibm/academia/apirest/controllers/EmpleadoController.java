package com.ibm.academia.apirest.controllers;

import java.util.Optional;

import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.services.EmpleadoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {
  
  @Autowired
  private EmpleadoDAO service;

  /**
   * Regresa una lista de empleados de acuerdo a su tipo
   * @param tipoEmpleado Valor del tipo de empleado puede ser ADMINISTRATIVO o MANTENIMIENTO.
   * @return regresa un objeto ResponseEntity con la lista de empleados.
   */
  @GetMapping
  public ResponseEntity<Iterable<Persona>> findEmpleadosByTipoEmpleado(
    @RequestParam("tipoEmpleado") TipoEmpleado tipoEmpleado) {
    
    Optional<Iterable<Persona>> result = service.findEmpleadoByTipoEmpleado(tipoEmpleado);
    
    if (result.isEmpty()) {
      throw new NotFoundException("No hay empleados de tipo " + tipoEmpleado);
    }

    return ResponseEntity.ok(result.get());
  }

}
