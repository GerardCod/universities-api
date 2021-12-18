package com.ibm.academia.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.mapper.AulaMapper;
import com.ibm.academia.apirest.models.dto.AulaDTO;
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.services.AulaDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aulas")
public class AulaController implements GenericController<Aula, Integer> {
  
  @Autowired
  private AulaDAO service;

  /**
   * Endpoint para consultar aulas por tipo de pizarrón.
   * @param pizarron Tipo de pizarrón del aula.
   * @return ResponseEntity con la información de las aulas que tienen el tipo de pizarrón ingresado.
   * @throws NotFoundException si no hay aulas con el tipo de pizarron ingresado.
   */
  @GetMapping("/pizarron")
  public ResponseEntity<List<AulaDTO>> findAulasByPizarron(
    @RequestParam(name = "pizarron", required = true) Pizarron pizarron
    ) {
    
    Optional<List<Aula>> result = service.findAulasByPizarron(pizarron);

    if (result.isEmpty()) {
      throw new NotFoundException("No hay aulas con pizarrón: " + pizarron);
    }

    List<AulaDTO> aulas = result.get()
    .stream().map(AulaMapper::mapAula)
    .collect(Collectors.toList());

    return ResponseEntity.ok(aulas);
  }

  /**
   * Endpoint para consultar aulas por el nombre del pabellon en el que se encuentran.
   * @param nombre Nombre del pabellón.
   * @return ResponseEntity con la información de las aulas que se encuentran en el pabellón con el nombre ingresado.
   * @throws NotFoundException si no hay aulas con en el pabellon con el nombre ingresado.
   */
  @GetMapping("/pabellon")
  public ResponseEntity<List<AulaDTO>> findAulasByPabellon(
    @RequestParam("pabellon") String nombre
    ) {
    
    Optional<List<Aula>> result = service.findAulasByPabellonNombre(nombre);
    
    if (result.isEmpty()) {
      throw new NotFoundException("No hay aulas en el pabellon: " + nombre);
    }

    List<AulaDTO> aulas = result.get().stream()
    .map(AulaMapper::mapAula).collect(Collectors.toList());

    return ResponseEntity.ok(aulas);

  }

  /**
   * Endpoint para consultar un aula por el número de aula.
   * @param numeroAula Número del aula que deseamos consultar.
   * @return ResponseEntity con la información del aula solicitada.
   * @throws NotFoundException si no hay un aula con el número ingresado.
   */
  @GetMapping("/aula/{numeroAula}")
  public ResponseEntity<AulaDTO> findAulasByNumeroAula(@PathVariable("numeroAula") Integer numeroAula) {
    Optional<Aula> result = service.findAulaByNumeroAula(numeroAula);
    
    if (result.isEmpty()) {
      throw new NotFoundException("No hay aula con el número: " + numeroAula);
    }
    
    return ResponseEntity.ok(AulaMapper.mapAula(result.get()));
  }
  
  /**
   * Endpoint para crear un aula nueva.
   * @param información del aula que será creada.
   * @return ResponseEntity con información del aula creada.
   */
  @Override
  @PostMapping
  public ResponseEntity<Aula> create(@RequestBody Aula entity) {
    Aula aulaSaved = service.guardar(entity);
    return ResponseEntity.status(HttpStatus.CREATED).body(aulaSaved);
  }

  /**
   * Endpoint para consultar la lista de todas las aulas creadas en el sistema.
   * @return ResponseEntity con la información de las aulas creadas.
   */
  @Override
  @GetMapping
  public ResponseEntity<List<Aula>> findAll() {
    List<Aula> result = (List<Aula>) service.buscarTodos();
    return ResponseEntity.ok(result);
  }

  /**
   * Endpoint para consultar la información de un aula por su id.
   * @param id Identificador del aula.
   * @return ResponseEntity con la información del aula solicitada.
   * @throws NotFoundException si no hay un aula con el id ingresado.
   */
  @Override
  @GetMapping("/{id}")
  public ResponseEntity<Aula> findById(@PathVariable Integer id) {
    Optional<Aula> result = service.buscarPorId(id);
    
    if (!result.isPresent()) {
      throw new NotFoundException("No se encontró un aula con el id: " + id);
    }
    
    return ResponseEntity.ok(result.get());
  }

  /**
   * Endpoint para actualizar la información de un aula.
   * @param id Identificador del aula.
   * @param entity Información actualizada del aula.
   * @return ResponseEntity con la nueva información del aula.
   * @throws NotFoundException si no hay un aula con el id ingresado.
   */
  @Override
  @PutMapping("/{id}")
  public ResponseEntity<Aula> update(@PathVariable Integer id, @RequestBody Aula entity) {

    Optional<Aula> result = service.buscarPorId(id);
    if (!result.isPresent()) {
      throw new NotFoundException("No se encontró un aula con el id: " + id);
    }
    Optional<Aula> updated = service.update(result.get(), entity);

    return ResponseEntity.ok(updated.get());
  }
  
  /**
   * Endpoint para eliminar un aula por id.
   * @param id Identificador del aula.
   * @return ResponseEntity con un mensaje de confirmación de la eliminación del aula.
   */
  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteById(@PathVariable Integer id) {
    Map<String, String> response = new HashMap<>();
    response.put("message", "Se eliminó el aula con id " + id);
    service.eliminarPorId(id);
    return ResponseEntity.ok(response);
  }

}
