package com.genpact.ms.modules.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genpact.ms.modules.demo.entity.DemoCache;

public interface DemoCacheRepository extends JpaRepository<DemoCache, Integer> {
			
}
