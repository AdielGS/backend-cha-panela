package com.chadepanela.adieljulia.repositories;

import com.chadepanela.adieljulia.models.Convidado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ConvidadoRepository extends JpaRepository<Convidado, Long> {
    @Query("SELECT c.numeroEscolhido FROM Convidado c WHERE c.numeroEscolhido IS NOT NULL")
    List<Integer> findNumerosEscolhidos();
}