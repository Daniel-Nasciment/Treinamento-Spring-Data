package br.com.alura.spring.data.dto;

import java.util.ArrayList;
import java.util.List;

public class CargoResponse {

	private List<String> cargos = new ArrayList<String>();

	public List<String> getCargos() {
		return cargos;
	}

	public void setCargos(List<String> descricao) {
		this.cargos = descricao;
	}

}
