package com.ibm.academia.apirest.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/persona")
public class PersonaController 
{
	/*
	@Autowired
	private PersonaDAO personaDao; */
	
	/**
	 * 
	 * @param nombre
	 * @param apellido
	 * @return
	 */
	@GetMapping("/nombre-apellido")
    public ResponseEntity<?> buscarPersonaPorNombreYApellido(@RequestParam String nombre, @RequestParam String apellido)
	{
        /* Optional<Persona> oPersona = personaDao.buscarPorNombreYApellido(nombre, apellido);
        
        if(!oPersona.isPresent()) 
            throw new NotFoundException(String.format("No se encontro Persona con nombre %s y apellido %s", nombre, apellido));
        
        return new ResponseEntity<Persona>(oPersona.get(), HttpStatus.OK); */
		return null;
    } 
}
