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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aulas")
public class AulaController implements GenericController<Aula, Integer> {
  
  @Autowired
  private AulaDAO service;

  @GetMapping
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

  @GetMapping("/aula/{numeroAula}")
  public ResponseEntity<AulaDTO> findAulasByNumeroAula(@PathVariable("numeroAula") Integer numeroAula) {
    Optional<Aula> result = service.findAulaByNumeroAula(numeroAula);
    
    if (result.isEmpty()) {
      throw new NotFoundException("No hay aula con el número: " + numeroAula);
    }
    
    return ResponseEntity.ok(AulaMapper.mapAula(result.get()));
  }

  @Override
  public ResponseEntity<Aula> create(Aula entity) {
    Aula aulaSaved = service.guardar(entity);
    return ResponseEntity.status(HttpStatus.CREATED).body(aulaSaved);
  }

  @Override
  public ResponseEntity<List<Aula>> findAll() {
    List<Aula> result = (List<Aula>) service.buscarTodos();
    return ResponseEntity.ok(result);
  }

  @Override
  public ResponseEntity<Aula> findById(Integer id) {
    Optional<Aula> result = service.buscarPorId(id);
    
    if (!result.isPresent()) {
      throw new NotFoundException("No se encontró un aula con el id: " + id);
    }
    
    return ResponseEntity.ok(result.get());
  }

  @Override
  public ResponseEntity<Aula> update(Integer id, Aula entity) {

    Optional<Aula> result = service.buscarPorId(id);
    if (!result.isPresent()) {
      throw new NotFoundException("No se encontró un aula con el id: " + id);
    }
    Optional<Aula> updated = service.update(result.get(), entity);

    return ResponseEntity.ok(updated.get());
  }

  @Override
  public ResponseEntity<?> deleteById(Integer id) {
    Map<String, String> response = new HashMap<>();
    response.put("message", "Se eliminó el aula con id " + id);
    service.eliminarPorId(id);
    return ResponseEntity.ok(response);
  }

}
