package com.cryptotracker.repository;
import com.cryptotracker.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TransacaoRepository extends JpaRepository<Transacao,Long>{
    List<Transacao> findByCarteiraId(Long carteiraId);
}
