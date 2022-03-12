package br.com.alura.spring.data.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.alura.spring.data.entity.Funcionario;

public class FuncionarioRequest {

	private String nome;

	private String cpf;

	private BigDecimal salario;

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public Funcionario toModel() {

		return new Funcionario(this.nome, this.cpf, this.salario, LocalDate.now());

	}

}
