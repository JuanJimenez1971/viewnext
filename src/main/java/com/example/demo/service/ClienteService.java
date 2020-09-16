package com.example.demo.service;

import java.util.Collection;
import com.example.demo.model.Cliente;

public interface ClienteService {	

	public Collection<Cliente> addCollection(Collection<Cliente> clientes);
	public Collection<Cliente> getAll();

}
