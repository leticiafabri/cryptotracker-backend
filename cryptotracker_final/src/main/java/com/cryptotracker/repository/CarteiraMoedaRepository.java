package com.cryptotracker.repository;
import com.cryptotracker.model.CarteiraMoeda;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface CarteiraMoedaRepository extends JpaRepository<CarteiraMoeda,Long>{
    List<CarteiraMoeda> findByCarteiraId(Long carteiraId);
    List<CarteiraMoeda> findByCarteiraIdAndMoedaId(Long carteiraId, Long moedaId);
}
