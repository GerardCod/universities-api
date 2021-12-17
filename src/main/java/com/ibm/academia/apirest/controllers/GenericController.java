package com.ibm.academia.apirest.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface GenericController<E, ID> {
  ResponseEntity<E> create(E entity);
  ResponseEntity<List<E>> findAll();
  ResponseEntity<E> findById(ID id);
  ResponseEntity<E> update(ID id, E entity);
  ResponseEntity<?> deleteById(ID id);
}
