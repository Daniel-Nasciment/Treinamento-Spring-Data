package br.com.alura.spring.data.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.spring.data.dto.CargoRequest;
import br.com.alura.spring.data.dto.CargoResponse;
import br.com.alura.spring.data.entity.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@RestController
@RequestMapping(value = "/cargos")
public class CargoController {

	private final CargoRepository repo;

	public CargoController(CargoRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	public ResponseEntity<CargoResponse> getCargos() {

		CargoResponse resp = new CargoResponse();
		
		Iterable<Cargo> cargos = repo.findAll();
		
		cargos.forEach(c -> resp.getCargos().add(c.getDescricao()));

		return ResponseEntity.ok(resp);
	}
	
	@PostMapping(value = "/novoCargo")
	public ResponseEntity<String> newCargo(@RequestBody CargoRequest request) {

		Cargo cargo = new Cargo();
		cargo.setDescricao(request.getDescricao());

		repo.save(cargo);

		return ResponseEntity.ok("Novo cargo criado!!");
	}
	
	@PutMapping(value = "/atualizaCargo/{id}")
	public ResponseEntity<String> updateCargo(@PathVariable Long id, @RequestBody CargoRequest request) {

		Optional<Cargo> cargo = repo.findById(id);
		
		if (cargo.isPresent()) {
			Cargo cargoRecuperado = cargo.get();
			cargoRecuperado.setDescricao(request.getDescricao());
			repo.save(cargoRecuperado);

			return ResponseEntity.ok("Cargo Atualizado!!");
		}
		
		return ResponseEntity.notFound().build();

	}

}
