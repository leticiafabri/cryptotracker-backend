package com.cryptotracker.repository;
import com.cryptotracker.model.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface CarteiraRepository extends JpaRepository<Carteira,Long>{
    List<Carteira> findByUsuarioId(Long usuarioId);
}
