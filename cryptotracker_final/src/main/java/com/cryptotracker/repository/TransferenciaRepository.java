package com.cryptotracker.repository;
import com.cryptotracker.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TransferenciaRepository extends JpaRepository<Transferencia,Long>{
    List<Transferencia> findByCarteiraOrigemOrCarteiraDestino(Long origem, Long destino);
}
