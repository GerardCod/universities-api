package com.ibm.academia.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.services.EmpleadoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/empleado")
public class EmpleadoController implements GenericController<Persona, Integer> {
  
  @Autowired
  private EmpleadoDAO service;

  /**
   * Regresa una lista de empleados de acuerdo a su tipo
   * @param tipoEmpleado Valor del tipo de empleado puede ser ADMINISTRATIVO o MANTENIMIENTO.
   * @return regresa un objeto ResponseEntity con la lista de empleados.
   */
  @GetMapping("/filter")
  public ResponseEntity<Iterable<Persona>> findEmpleadosByTipoEmpleado(
    @RequestParam("tipoEmpleado") TipoEmpleado tipoEmpleado) {
    
    Optional<Iterable<Persona>> result = service.findEmpleadoByTipoEmpleado(tipoEmpleado);
    
    if (result.isEmpty()) {
      throw new NotFoundException("No hay empleados de tipo " + tipoEmpleado);
    }

    return ResponseEntity.ok(result.get());
  }

  /**
   * Endpoint para crear un nuevo empleado por medio de una petición post.
   * @param entity Información del nuevo empleado que vamos a crear.
   * @return ResponseEntity con la información del nuevo empleado creado.
   */
  @Override
  @PostMapping
  public ResponseEntity<Persona> create(@RequestBody Persona entity) {
    Persona result = service.guardar(entity);

    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  /**
   * Endpoint para consulta todos los empleados en el sistema.
   * @return ResponseEntity con la información de todos los empleados.
   * @throws NotFoundException si no hay empleados en el sistema.
   */
  @Override
  @GetMapping
  public ResponseEntity<List<Persona>> findAll() {
    List<Persona> people = (List<Persona>) service.buscarTodos();

    if (people.isEmpty()) {
      throw new NotFoundException("No hay empleados");
    }

    return ResponseEntity.ok(people);
  }

  /**
   * Endpoint para consultar la información de un empleado específico.
   * @param id Identificador numérico del empleado
   * @return ResponseEntity con la información del empleado.
   * @throws NotFoundException si no existe un empleado con el id ingresado.
   */
  @Override
  @GetMapping("/{id}")
  public ResponseEntity<Persona> findById(@PathVariable Integer id) {
    Optional<Persona> result = service.buscarPorId(id);
    
    if (!result.isPresent()) {
      throw new NotFoundException("No se encontró un empleado con el id: " + id);
    }
    
    return ResponseEntity.ok(result.get());
  }

  /**
   * Endpoint para actualizar la información de un empleado.
   * @param id Identificador del empleado
   * @param entity Información actualizada del empleado.
   * @return ResponseEntity con la información actualizada del empleado.
   * @throws NotFoundEntity si no se encuentra el empleado que se desea modificar.
   */
  @Override
  @PutMapping("/{id}")
  public ResponseEntity<Persona> update(@PathVariable Integer id, @RequestBody Persona entity) {
    Optional<Persona> result = service.buscarPorId(id);

    if (!result.isPresent()) {
      throw new NotFoundException("No se encontró un empleado con el id: " + id);
    }

    Optional<Persona> resultUpdate = service.updateEmployee(result.get(), entity);

    return ResponseEntity.ok(resultUpdate.get());
  }

  /**
   * Endpoint para dar de baja a un empleado.
   * @param id Identificador del empleado.
   * @return ResponseEntity con un mensaje de confirmación de la eliminación del empleado.
   */
  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteById(Integer id) {
    Map<String, String> response = new HashMap<>();
    response.put("message", "Empleado dado de baja");
    service.eliminarPorId(id);
    return ResponseEntity.ok(response);
  }

}
