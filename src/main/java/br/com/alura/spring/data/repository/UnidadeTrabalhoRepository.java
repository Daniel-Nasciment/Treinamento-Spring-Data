package br.com.alura.spring.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.entity.UnidadeTrabalho;

@Repository
public interface UnidadeTrabalhoRepository extends JpaRepository<UnidadeTrabalho, Long>{

}
