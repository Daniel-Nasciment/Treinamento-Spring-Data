package br.com.alura.spring.data.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.alura.spring.data.entity.Funcionario;

public class FuncionarioResponse {

	private String nome;

	private BigDecimal salario;

	private LocalDate dataContratacao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public LocalDate getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(LocalDate dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	public void modelToResponse(Funcionario funcionario) {

		this.nome = funcionario.getNome();
		this.salario = funcionario.getSalario();
		this.dataContratacao = funcionario.getDataContratacao();

	}

}
