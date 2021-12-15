package com.ibm.academia.apirest.models.dto;

import com.ibm.academia.apirest.enums.Pizarron;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AulaDTO {
  private Integer id;
  private Integer numeroAula;
  private Pizarron pizarron;  
}
