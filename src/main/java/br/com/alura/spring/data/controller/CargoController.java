package br.com.alura.spring.data.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.spring.data.dto.CargoRequest;
import br.com.alura.spring.data.entity.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@RestController
@RequestMapping(value = "/cargos")
public class CargoController {

	private final CargoRepository repo;

	public CargoController(CargoRepository repo) {
		this.repo = repo;
	}

	@PostMapping(value = "/novoCargo")
	public ResponseEntity<String> newCargo(@RequestBody CargoRequest request) {

		System.out.println(request);

		Cargo cargo = new Cargo();
		cargo.setDescricao(request.getDescricao());

		repo.save(cargo);

		return ResponseEntity.ok("Novo cargo criado!!");
	}

}
