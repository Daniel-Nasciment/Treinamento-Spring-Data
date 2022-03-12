package br.com.alura.spring.data.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.spring.data.dto.FuncionarioRequest;
import br.com.alura.spring.data.dto.FuncionarioResponse;
import br.com.alura.spring.data.entity.Funcionario;
import br.com.alura.spring.data.entity.FuncionariosProjection;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specification.FuncionarioSpecification;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {

	private final FuncionarioRepository repo;

	public FuncionarioController(FuncionarioRepository repo) {
		this.repo = repo;
	}

	@GetMapping(value = "/{page}")
	public ResponseEntity<FuncionarioResponse> getFuncionarios(@PathVariable int page) {

		FuncionarioResponse resp = new FuncionarioResponse();

		Pageable pageable = PageRequest.of(page, 1, Sort.by(Sort.Direction.ASC, "nome"));

		Iterable<Funcionario> funcionarios = repo.findAll(pageable);

		funcionarios.forEach(f -> {
			resp.setNome(f.getNome());
			resp.setSalario(f.getSalario());
			resp.setDataContratacao(f.getDataContratacao());
		});

		return ResponseEntity.ok(resp);
	}

	@GetMapping(value = "/projection")
	public ResponseEntity<FuncionarioResponse> getFuncionariosProjection() {

		FuncionarioResponse resp = new FuncionarioResponse();

		Iterable<FuncionariosProjection> funcionarios = repo.relatorioProjection();

		funcionarios.forEach(f -> {
			resp.setNome(f.getNome());
			resp.setSalario(f.getSalario());
		});

		return ResponseEntity.ok(resp);
	}

	@GetMapping(value = "/consultaDinamica/{filter}")
	public ResponseEntity<FuncionarioResponse> getFuncionariosConsultaDinamica(@PathVariable String filter) {

		FuncionarioResponse resp = new FuncionarioResponse();

		Iterable<Funcionario> funcionarios = repo.findAll(
				Specification.where(FuncionarioSpecification.nome(filter).or(FuncionarioSpecification.cpf(filter))));

		funcionarios.forEach(f -> {
			resp.setNome(f.getNome());
			resp.setSalario(f.getSalario());
		});

		return ResponseEntity.ok(resp);
	}

	@GetMapping(value = "/maiorSalario")
	public ResponseEntity<FuncionarioResponse> getFuncionariosMaiorSalario(@RequestBody String salario) {

		FuncionarioResponse resp = new FuncionarioResponse();

		List<Funcionario> funcionarios = repo.findSalarioMaior(new BigDecimal(salario));

		System.out.println(funcionarios);

		funcionarios.forEach(f -> {
			resp.setNome(f.getNome());
			resp.setSalario(f.getSalario());
			resp.setDataContratacao(f.getDataContratacao());
		});

		return ResponseEntity.ok(resp);
	}

	@GetMapping(value = "/buscaCpf")
	public ResponseEntity<FuncionarioResponse> getFuncionarioCpf(@RequestBody String cpf) {

		FuncionarioResponse resp = new FuncionarioResponse();

		Optional<Funcionario> funcionario = repo.findCpfFuncionarip(cpf);

		if (funcionario.isPresent()) {

			Funcionario funcEncontrado = funcionario.get();

			resp.setNome(funcEncontrado.getNome());
			resp.setDataContratacao(funcEncontrado.getDataContratacao());
			resp.setSalario(funcEncontrado.getSalario());

			return ResponseEntity.ok(resp);

		}

		return ResponseEntity.notFound().build();

	}

	@GetMapping(value = "buscaNome")
	public ResponseEntity<FuncionarioResponse> getFuncionariosNome(@RequestBody String nome) {

		FuncionarioResponse resp = new FuncionarioResponse();

		List<Funcionario> funcionarios = repo.findByNome(nome);

		funcionarios.forEach(f -> {
			resp.setNome(f.getNome());
			resp.setSalario(f.getSalario());
			resp.setDataContratacao(f.getDataContratacao());
		});

		return ResponseEntity.ok(resp);
	}

	@PostMapping(value = "/novoFuncionario")
	public ResponseEntity<String> newFuncionarios(@RequestBody FuncionarioRequest request) {

		Funcionario funcionario = request.toModel();

		repo.save(funcionario);

		return ResponseEntity.ok("Novo funcionario criado!!");
	}

	@PutMapping(value = "/atualizaFuncionario/{id}")
	public ResponseEntity<FuncionarioResponse> updateFuncionario(@PathVariable Long id,
			@RequestBody FuncionarioRequest request) {

		FuncionarioResponse resp = new FuncionarioResponse();

		Optional<Funcionario> funcionario = repo.findById(id);

		if (funcionario.isPresent()) {

			Funcionario funcRecuperado = funcionario.get();

			// faz o update dos campos
			funcRecuperado.setNome(request.getNome());
			funcRecuperado.setSalario(request.getSalario());

			// monta a resposta a partir do objeto de entidade
			resp.modelToResponse(funcRecuperado);

			repo.save(funcRecuperado);

			return ResponseEntity.ok(resp);
		}

		return ResponseEntity.notFound().build();

	}

	@DeleteMapping(value = "/removeFuncionario/{id}")
	public ResponseEntity<String> deleteFuncionario(@PathVariable Long id) {

		boolean existsId = repo.existsById(id);

		if (existsId) {
			repo.deleteById(id);

			return ResponseEntity.ok("Funcionario Removido!!");
		}

		return ResponseEntity.notFound().build();

	}

}
