package com.ibm.academia.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.services.CarreraDAO;
import com.ibm.academia.apirest.services.ProfesorDAO;

import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profesor")
public class ProfesorController implements GenericController<Persona, Integer> {
  
  @Autowired
  private ProfesorDAO service;

  @Autowired
  private CarreraDAO careerService;

  @PatchMapping("/association/{professorId}/{careerId}")
  public ResponseEntity<Persona> addProfessorToCareer(@PathVariable("") Integer professorId, @PathVariable Integer careerId) {
    Optional<Persona> professorFound = service.buscarPorId(professorId);

    if (!professorFound.isPresent()) {
      throw new NotFoundException("No existe un profesor con id: " + professorId);
    }

    Optional<Carrera> careerFound = careerService.buscarPorId(careerId);

    if (!careerFound.isPresent()) {
      throw new NotFoundException("No existe una carrera con id: " + careerId);
    }

    Optional<Persona> result = service.addProfessorToCareer(professorFound.get(), careerFound.get());
    return ResponseEntity.ok(result.get());
  }

  /**
   * Endpoint que regresa información de los profesores que pertenecen al nombre de una carrera pasada por parámetro.
   * @param carrera Nombre de la carrera que usaremos para buscar a los profesores.
   * @return un objeto de tipo ResponseEntity<Iterable<Persona>>
   */
  @GetMapping("/carrera")
  public ResponseEntity<Iterable<Persona>> findProfesoresByCarrera(@RequestParam("carrera") String carrera) {
    Optional<Iterable<Persona>> result = service.findProfesoresByCarrera(carrera);

    if (result.isEmpty()) {
      throw new NotFoundException("No se encontraron profesores que pertenezcan a la carrera de " + carrera);
    }

    return ResponseEntity.ok(result.get());
  }

  /**
   * Endpoint para crear un nuevo profesor.
   * @param entity Informacion del nuevo profesor.
   * @return ResponseEntity con información del profesor creado.
   */
  @Override
  @PostMapping
  public ResponseEntity<Persona> create(@RequestBody Persona entity) {
    Persona result = service.guardar(entity);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  /**
   * Endpoint para consultar la información de todos los profesores creados en el sistema.
   * @return ResponseEntity con la información de los profesores creados en el sistema.
   * @throws NotFoundException si no hay profesores creados en el sistema.
   */
  @Override
  @GetMapping
  public ResponseEntity<List<Persona>> findAll() {
    List<Persona> result = (List<Persona>) service.buscarTodos();

    if (result.isEmpty()) {
      throw new NotFoundException("No hay profesores creados");
    }

    return ResponseEntity.ok(result);
  }

  /**
   * Endpoint para consultar la información de un profesor por id.
   * @param id Identificador del profesor.
   * @return ResponseEntity con la información del profesor solicitado.
   * @throws NotFoundException si no existe un profesor con el id ingresado.
   */
  @Override
  @GetMapping("/{id}")
  public ResponseEntity<Persona> findById(@PathVariable Integer id) {
    Optional<Persona> result = service.buscarPorId(id);
    
    if (!result.isPresent()) {
      throw new NotFoundException("No se encontró un Profesor con ID: " + id);
    }
    
    return ResponseEntity.ok(result.get());
  }

  /**
   * Endpoint para actualizar la información de un profesor.
   * @param id Identificador del profesor.
   * @param entity Información actualizada del profesor.
   * @return ResponseEntity con la nueva información del profesor.
   * @throws NotFoundException si no existe un profesor con el id ingresado.
   */
  @Override
  @PutMapping("/{id}")
  public ResponseEntity<Persona> update(@PathVariable Integer id, @RequestBody Persona entity) {

    Optional<Persona> result = service.buscarPorId(id);

    if (!result.isPresent()) {
      throw new NotFoundException("No se encontró un profesor con id: " + id);
    }

    Optional<Persona> profesorSaved = service.updateProfesor(result.get(), entity);
    return ResponseEntity.ok(profesorSaved.get());
  }

  /**
   * Endpoint para eliminar un profesor por id.
   * @param id Identificador del profesor.
   * @return ResponseEntity con un mensaje de confirmación de la eliminación del profesor.
   */
  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteById(@PathVariable Integer id) {
    Map<String, String> response = new HashMap<>();
    service.eliminarPorId(id);
    response.put("message", "Profesor con id " + id + " eliminado");
    return ResponseEntity.ok(response);
  }


}
