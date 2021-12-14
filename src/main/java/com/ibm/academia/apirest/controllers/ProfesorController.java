package com.ibm.academia.apirest.controllers;

import java.util.Optional;

import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.services.ProfesorDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {
  
  @Autowired
  private ProfesorDAO service;

  /**
   * Regresa información de los profesores que pertenecen al nombre de una carrera pasada por parámetro.
   * @param carrera Nombre de la carrera que usaremos para buscar a los profesores.
   * @return un objeto de tipo ResponseEntity<Iterable<Persona>>
   */
  @GetMapping
  public ResponseEntity<Iterable<Persona>> findProfesoresByCarrera(@RequestParam("carrera") String carrera) {
    Optional<Iterable<Persona>> result = service.findProfesoresByCarrera(carrera);

    if (result.isEmpty()) {
      throw new NotFoundException("No se encontraron profesores que pertenezcan a la carrera de " + carrera);
    }

    return ResponseEntity.ok(result.get());
  }


}
