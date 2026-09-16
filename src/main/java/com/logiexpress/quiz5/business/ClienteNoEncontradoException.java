package com.logiexpress.quiz5.business;

public class ClienteNoEncontradoException extends RuntimeException {
	public ClienteNoEncontradoException(Long clienteId) {
		super(clienteId == null
				? "El paquete debe tener un cliente existente"
				: "No se encontro el cliente con id " + clienteId);
	}
}
