package com.futbol.equipo_de_futbol.repository;

import com.futbol.equipo_de_futbol.entity.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {

}
