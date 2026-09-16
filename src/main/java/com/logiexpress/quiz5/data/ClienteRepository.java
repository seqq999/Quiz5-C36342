package com.logiexpress.quiz5.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logiexpress.quiz5.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
