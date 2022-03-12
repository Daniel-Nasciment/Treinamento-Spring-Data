package br.com.alura.spring.data.entity;

import java.math.BigDecimal;

public interface FuncionariosProjection {

	Long getId();
	String getNome();
	BigDecimal getSalario();
	
}
