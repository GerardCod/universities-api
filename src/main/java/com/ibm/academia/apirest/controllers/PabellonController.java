package com.ibm.academia.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.models.entities.Direccion;
import com.ibm.academia.apirest.models.entities.Pabellon;
import com.ibm.academia.apirest.services.PabellonDAO;

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
@RequestMapping("/pabellones")
public class PabellonController implements GenericController<Pabellon, Integer> {
  
  @Autowired
  private PabellonDAO service;
  
  /**
   * Endpoint para consultar pabellones de acuerdo a su direccion
   * @param calle Calle de la dirección
   * @param numero Numero de la dirección
   * @param codigoPostal Código postal de la dirección.
   * @param localidad Nombre de la localidad de la dirección.
   * @return ResponseEntity con la información de los pabellones en esa dirección.
   * @throws NotFoundException si no se encuentran pabellones en la dirección ingresada.
   */
  @GetMapping("/direccion")
  public ResponseEntity<List<Pabellon>> getPabellonesByDireccion(
    @RequestParam(required = true, name = "calle") String calle,
    @RequestParam(required = true, name = "numero") String numero,
    @RequestParam(required = true, name = "codigoPostal") String codigoPostal,
    @RequestParam(required = true, name = "localidad") String localidad
  ) {

    Direccion direccion = new Direccion();
    direccion.setCalle(calle);
    direccion.setCodigoPostal(codigoPostal);
    direccion.setNumero(numero);
    direccion.setLocalidad(localidad);

    Optional<List<Pabellon>> result = service.getPabellonesByDireccion(direccion);

    if (!result.isPresent()) {
      throw new NotFoundException("No hay pabellones en esa dirección");
    }

    return ResponseEntity.ok(result.get());
  }

  /**
   * Endpoint para consultar pabellones por nombre
   * @param nombre Nombre del pabellon ingresado por parámetro.
   * @return ResponseEntity con la información de los pabellones con ese nombre.
   * @throws NotFoundException si no existen pabellones con ese nombre.
   */
  @GetMapping("/nombre")
  public ResponseEntity<List<Pabellon>> getPabellonesByNombre(@RequestParam("nombre") String nombre) {
    Optional<List<Pabellon>> result = service.getPabellonesByNombre(nombre);
    
    if (!result.isPresent()) {
      throw new NotFoundException("No hay pabellones con ese nombre");
    }

    return ResponseEntity.ok(result.get());
  }

  /**
   * Endpoint para crear un nuevo pabellon.
   * @param entity Información del nuevo pabellon.
   * @return ResponseEntity con la información del pabellón creado.
   */
  @Override
  @PostMapping
  public ResponseEntity<Pabellon> create(@RequestBody Pabellon entity) {
    Pabellon result = service.guardar(entity);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  /**
   * Endpoint para consultar la lista de Pabellones en el sistema.
   * @return ResponseEntity con la información de los pabellones existentes.
   * @throws NotFoundException si no hay pabellones en el sistema.
   */
  @Override
  @GetMapping
  public ResponseEntity<List<Pabellon>> findAll() {

    List<Pabellon> result = (List<Pabellon>) service.buscarTodos();

    if (result.isEmpty()) {
      throw new NotFoundException("No hay pabellones registrados");
    }

    return ResponseEntity.ok(result);
  }

  /**
   * Endpoint para consultar la información de un pabellón por id.
   * @param id Identificador del Pabellon.
   * @return ResponseEntity con la información del pabellón solicitado.
   * @throws NotFoundException si no se encuentra el pabellón solicitado.
   */
  @Override
  @GetMapping("/{id}")
  public ResponseEntity<Pabellon> findById(@PathVariable Integer id) {

    Optional<Pabellon> result = service.buscarPorId(id);

    if (!result.isPresent()) {
      throw new NotFoundException("No se encontró un pabellón con el id: " + id);
    }

    return ResponseEntity.ok(result.get());
  }

  /**
   * Endpoint para actualizar la información de un pabellón
   * @param id Identificador del pabellón.
   * @param entity Información actualizada del pabellón.
   * @return ResponseEntity con la información actualizada del pabellón.
   * @throws NotFoundException si no existe un pabellón con el id ingresado.
   */
  @Override
  @PutMapping("/{id}")
  public ResponseEntity<Pabellon> update(@PathVariable Integer id, @RequestBody Pabellon entity) {
    
    Optional<Pabellon> result = service.buscarPorId(id);

    if (!result.isPresent()) {
      throw new NotFoundException("No hay un pabellón con id: " + id);
    }

    Optional<Pabellon> resultUpdate = service.updatePabellon(result.get(), entity);

    return ResponseEntity.ok(resultUpdate.get());
  }

  /**
   * Endpoint para eliminar un Pabellon.
   * @param id Identificador del pabellón.
   * @return ResponseEntity con un mensaje de confirmación de la eliminación de un pabellón.
   */
  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteById(@PathVariable Integer id) {
    Map<String, String> response = new HashMap<>();
    response.put("message", "Pabellón eliminado");
    service.eliminarPorId(id);
    return ResponseEntity.ok(response);
  }

}
