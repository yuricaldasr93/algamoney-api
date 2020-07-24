package com.example.algamoney.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.event.RecursoCriadoEvent;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.PessoaRepository;
import com.example.algamoney.api.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaRepository pessoarepository;
	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@GetMapping
	public List<Pessoa> listar(){
		return pessoarepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
		Pessoa pessoaSalva = pessoarepository.save(pessoa);
		
		eventPublisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Integer codigo){
		return pessoarepository.findById(codigo).map(m -> ResponseEntity.ok(m))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer codigo) {
		pessoarepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}") 
	public ResponseEntity<Pessoa> atualizar(@PathVariable Integer codigo,@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
		Pessoa pessoalSalva = pessoaService.atualizar(codigo, pessoa);
		
		return ResponseEntity.ok(pessoalSalva);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Integer codigo, @RequestBody Boolean ativo){
		pessoaService.atualizarPropriedadeAtivo(codigo, ativo);
	}
}
