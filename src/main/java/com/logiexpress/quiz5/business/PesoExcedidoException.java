package com.logiexpress.quiz5.business;

public class PesoExcedidoException extends RuntimeException {
	public PesoExcedidoException(double pesoMaximo) {
		super("El peso del paquete debe ser menor o igual a " + pesoMaximo + " kg");
	}
}
