package com.ibm.academia.apirest.datos;

import java.math.BigDecimal;

import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.models.entities.*;

public class DatosDummy 
{
	public static Carrera carrera01() 
	{
		return new Carrera(null, "Ingenieria en Sistemas", 50, 5); 
	}

	public static Carrera carrera02() 
	{
		return new Carrera(null, "Licenciatura en Sistemas", 45, 4);
	}

	public static Carrera carrera03() 
	{
		return new Carrera(null, "Ingenieria Industrial", 60, 5);
	}
	
	public static Persona empleado01() 
	{
		return new Empleado(null, "Lautaro", "Lopez", "25174036", new Direccion(), new BigDecimal("46750.70"), TipoEmpleado.ADMINISTRATIVO);
	}

	public static Persona empleado02() 
	{
		return new Empleado(null, "Lenadro", "Lopez", "25174630", new Direccion(), new BigDecimal("46750.70"), TipoEmpleado.MANTENIMIENTO);
	}
	
	public static Persona profesor01() 
	{
		return new Profesor(null, "Martin", "Lugone", "33908461", new Direccion(), new BigDecimal("60000.00"));
	}
	
	public static Persona alumno01() 
	{
		return new Alumno(null, "Jhon", "Benitez", "45233715", new Direccion());
	}

	public static Persona alumno02() 
	{
		return new Alumno(null, "Andres", "Benitez", "45233891", new Direccion());
	}

	public static Persona alumno03() 
	{
		return new Alumno(null, "Joaquin", "Leon", "45233012", new Direccion());
	}

	public static Aula aula01() {
		return new Aula(null,10, "15 x 15", 35, Pizarron.PIZARRA_TIZA);
	}

	public static Aula aula02() {
		return new Aula(null,15, "15 x 15", 35, Pizarron.PIZARRA_BLANCA);
	}

	public static Aula aula03() {
		return new Aula(null,20, "15 x 15", 35, Pizarron.PIZARRA_BLANCA);
	}
}