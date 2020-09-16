package com.example.demo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;

@Service
public class ClienteServiceIml implements ClienteService{

	@Autowired
	ClienteRepository clienteRepository;
	

	@Override
	public Collection<Cliente> addCollection(Collection<Cliente> clientes) {
		
		return clienteRepository.saveAll(clientes);
	}


	@Override
	public Collection<Cliente> getAll() {
		
		return clienteRepository.findAll();
		
	}

}
