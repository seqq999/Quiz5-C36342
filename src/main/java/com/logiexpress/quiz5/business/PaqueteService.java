package com.logiexpress.quiz5.business;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.logiexpress.quiz5.data.ClienteRepository;
import com.logiexpress.quiz5.data.PaqueteRepository;
import com.logiexpress.quiz5.domain.Cliente;
import com.logiexpress.quiz5.domain.EstadoPaquete;
import com.logiexpress.quiz5.domain.Paquete;
import com.logiexpress.quiz5.dto.PaqueteRequestDto;

@Service
public class PaqueteService {

    private static final double PESO_MAXIMO_KG = 30.0;

    private final ClienteRepository clienteRepository;
    private final PaqueteRepository paqueteRepository;

    public PaqueteService(ClienteRepository clienteRepository, PaqueteRepository paqueteRepository) {
        this.clienteRepository = clienteRepository;
        this.paqueteRepository = paqueteRepository;
    }

    @Transactional(readOnly = true)
    public List<Paquete> findAll() {
        return paqueteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Paquete> findByEstado(EstadoPaquete estado, Pageable pageable) {
        return paqueteRepository.findByEstado(estado, pageable);
    }

    @Transactional
    public Paquete registrar(Paquete paquete, Long clienteId) {
        validarPeso(paquete);

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNoEncontradoException(clienteId));

        paquete.setCliente(cliente);
        if (paquete.getEstado() == null) {
            paquete.setEstado(EstadoPaquete.REGISTRADO);
        }

        return paqueteRepository.save(paquete);
    }

    @Transactional
    public Paquete registrar(PaqueteRequestDto dto) {
        validarPeso(dto);

        if (dto == null || dto.getClienteId() == null) {
            throw new ClienteNoEncontradoException(null);
        }

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ClienteNoEncontradoException(dto.getClienteId()));

        EstadoPaquete estado = dto.getEstado() == null
                ? EstadoPaquete.REGISTRADO
                : dto.getEstado();

        Paquete paquete = new Paquete(
                dto.getCodigoRastreo(),
                dto.getDescripcion(),
                dto.getPesoKg(),
                estado,
                cliente);

        return paqueteRepository.save(paquete);
    }

    private void validarPeso(PaqueteRequestDto dto) {
        if (dto == null || dto.getPesoKg() == null
                || dto.getPesoKg() > PESO_MAXIMO_KG) {
            throw new PesoExcedidoException(PESO_MAXIMO_KG);
        }
    }

    private void validarPeso(Paquete paquete) {
        if (paquete == null || paquete.getPesoKg() == null
                || paquete.getPesoKg() > PESO_MAXIMO_KG) {
            throw new PesoExcedidoException(PESO_MAXIMO_KG);
        }
    }
}
