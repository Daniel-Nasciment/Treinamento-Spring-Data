package br.com.alura.spring.data.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.entity.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	
	public List<Funcionario> findByNome(String nome);
	
	@Query("SELECT f FROM Funcionario f WHERE f.salario >= :salario")
	public List<Funcionario> findSalarioMaior(BigDecimal salario);
	
	@Query(value = "SELECT * FROM funcionarios f WHERE f.cpf = :cpf", nativeQuery = true)
	public Optional<Funcionario> findCpfFuncionarip(String cpf);

}
