package br.com.alura.spring.data.dto;

import br.com.alura.spring.data.entity.UnidadeTrabalho;

public class UnidadeTrabalhoRequest {

	private String descricao;

	private String endereco;

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

	public UnidadeTrabalho toModel() {
		return new UnidadeTrabalho(this.descricao, this.endereco);
	}

}
