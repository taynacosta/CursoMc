package com.costatayna.mc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.costatayna.mc.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

	
}
