package com.cibertec.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "prueba")
public class Prueba {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPrueba;
	
	@Pattern(regexp = "[a-zA-ZáéíóúñüÁÉÍÓÚÑÜ\\s]{2,100}" , message = "El nombre es de 2 a 20 caracteres")
	private String descripcion;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPais")
	private Pais pais;
}
