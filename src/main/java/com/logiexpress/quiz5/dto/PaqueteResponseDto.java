package com.logiexpress.quiz5.dto;

import com.logiexpress.quiz5.domain.EstadoPaquete;
import com.logiexpress.quiz5.domain.Paquete;

public class PaqueteResponseDto {
	private Long id;
	private String codigoRastreo;
	private String descripcion;
	private Double pesoKg;
	private EstadoPaquete estado;
	private Long clienteId;

	public PaqueteResponseDto() {
	}

	public PaqueteResponseDto(Paquete paquete) {
		this.id = paquete.getId();
		this.codigoRastreo = paquete.getCodigoRastreo();
		this.descripcion = paquete.getDescripcion();
		this.pesoKg = paquete.getPesoKg();
		this.estado = paquete.getEstado();
		this.clienteId = paquete.getCliente() == null ? null : paquete.getCliente().getId();
	}

	public Long getId() {
		return id;
	}

	public String getCodigoRastreo() {
		return codigoRastreo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Double getPesoKg() {
		return pesoKg;
	}

	public EstadoPaquete getEstado() {
		return estado;
	}

	public Long getClienteId() {
		return clienteId;
	}
}
