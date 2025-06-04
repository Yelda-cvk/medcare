package com.medcare.medcare.repository;

import com.medcare.medcare.model.Hasta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HastaRepository extends JpaRepository<Hasta, Long> {
    Hasta findByTcKimlikNo(String tcKimlikNo);
}
