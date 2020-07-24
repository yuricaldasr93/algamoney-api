package com.example.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoarepository;
	
	public Pessoa atualizar(Integer codigo, Pessoa pessoa) {
		Pessoa pessoalSalva = buscarPessoa(codigo);
		
		BeanUtils.copyProperties(pessoa, pessoalSalva, "codigo");
		return pessoarepository.save(pessoalSalva);
	}
	
	public void atualizarPropriedadeAtivo(Integer codigo, Boolean ativo) {
		Pessoa pessoaSalva = buscarPessoa(codigo);
		pessoaSalva.setAtivo(ativo);
		pessoarepository.save(pessoaSalva);
	}
	
	private Pessoa buscarPessoa(Integer codigo) {
		Pessoa pessoalSalva = pessoarepository.findById(codigo).orElse(null);
		if(pessoalSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoalSalva;
	}
	
}
