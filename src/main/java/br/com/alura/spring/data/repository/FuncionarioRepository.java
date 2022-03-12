package br.com.alura.spring.data.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.entity.Funcionario;
import br.com.alura.spring.data.entity.FuncionariosProjection;

// JPARepository -> Trablhar com lotes, salvar ou deletar varios registros de uma vez
// CRUDRepository -> Operações com CRUD
// PagingAndSortingRepository -> Trabalhando com Paginacao

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Long>{
	
	public List<Funcionario> findByNome(String nome);
	
	@Query("SELECT f FROM Funcionario f WHERE f.salario >= :salario")
	public List<Funcionario> findSalarioMaior(BigDecimal salario);
	
	@Query(value = "SELECT * FROM funcionarios f WHERE f.cpf = :cpf", nativeQuery = true)
	public Optional<Funcionario> findCpfFuncionarip(String cpf);
	
	@Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f", nativeQuery = true)
	public List<FuncionariosProjection> relatorioProjection();

}
