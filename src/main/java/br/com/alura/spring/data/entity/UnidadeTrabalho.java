package br.com.alura.spring.data.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "unidades_trabalho")
public class UnidadeTrabalho {

	private Long id;

	private String descricao;

	private String endereco;

	@ManyToMany(mappedBy = "unidadeTrabalho", fetch = FetchType.EAGER)
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();

	@Deprecated
	public UnidadeTrabalho() {
	}

	public UnidadeTrabalho(String descricao, String endereco) {
		this.descricao = descricao;
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

}
