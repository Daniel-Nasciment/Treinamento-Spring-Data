package br.com.alura.spring.data.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.alura.spring.data.entity.Funcionario;

public class FuncionarioSpecification {

	
	public static Specification<Funcionario> nome(String nome){
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
	}
	
	public static Specification<Funcionario> cpf(String cpf){
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.equal(root.get("cpf"), cpf);
	}
	
}
