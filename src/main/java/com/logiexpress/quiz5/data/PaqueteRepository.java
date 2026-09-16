package com.logiexpress.quiz5.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.logiexpress.quiz5.domain.EstadoPaquete;
import com.logiexpress.quiz5.domain.Paquete;

public interface PaqueteRepository extends JpaRepository<Paquete, Long> {

	Page<Paquete> findByEstado(EstadoPaquete estado, Pageable pageable);
}
