package br.com.alura.spring.data.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.spring.data.dto.UnidadeTrabalhoRequest;
import br.com.alura.spring.data.dto.UnidadeTrabalhoResponse;
import br.com.alura.spring.data.entity.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@RestController
@RequestMapping(value = "/unidade")
public class UnidadeTrabalhoController {

	private final UnidadeTrabalhoRepository repo;

	public UnidadeTrabalhoController(UnidadeTrabalhoRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	public ResponseEntity<UnidadeTrabalhoResponse> getUnidadesTrabalho() {

		UnidadeTrabalhoResponse resp = new UnidadeTrabalhoResponse();

		List<UnidadeTrabalho> unTrabalho = repo.findAll();

		unTrabalho.forEach(u -> {
			resp.setDescricao(u.getDescricao());
			resp.setEndereco(u.getEndereco());
		});

		return ResponseEntity.ok(resp);
	}

	@PostMapping(value = "/novaUnTrabalho")
	public ResponseEntity<String> newUnidadeTrabalho(@RequestBody UnidadeTrabalhoRequest request) {

		UnidadeTrabalho unTrabalho = request.toModel();

		repo.save(unTrabalho);

		return ResponseEntity.ok("Nova unidade de trabalho criada !!");
	}

	@PutMapping(value = "/atualizaUnidadeTrabalho/{id}")
	public ResponseEntity<UnidadeTrabalhoResponse> updateUnidadeTrabalho(@PathVariable Long id,
			@RequestBody UnidadeTrabalhoRequest request) {

		UnidadeTrabalhoResponse resp = new UnidadeTrabalhoResponse();

		Optional<UnidadeTrabalho> unTrabalho = repo.findById(id);

		if (unTrabalho.isPresent()) {

			UnidadeTrabalho unidadeRecuperada = unTrabalho.get();

			// faz o update dos campos
			unidadeRecuperada.setDescricao(request.getDescricao());
			unidadeRecuperada.setEndereco(request.getEndereco());

			// monta a resposta a partir do objeto de entidade
			resp.modelToResponse(unidadeRecuperada);

			repo.save(unidadeRecuperada);

			return ResponseEntity.ok(resp);
		}

		return ResponseEntity.notFound().build();

	}

	@DeleteMapping(value = "/removeUnTrabalho/{id}")
	public ResponseEntity<String> deleteUnidadeTrabalho(@PathVariable Long id) {

		boolean existsId = repo.existsById(id);

		if (existsId) {
			repo.deleteById(id);

			return ResponseEntity.ok("Unidade de trabalho removida!!");
		}

		return ResponseEntity.notFound().build();

	}

}
