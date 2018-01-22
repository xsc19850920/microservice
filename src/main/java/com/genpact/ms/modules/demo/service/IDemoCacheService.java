package com.genpact.ms.modules.demo.service;

import java.util.List;

import com.genpact.ms.modules.demo.entity.DemoCache;

public interface IDemoCacheService {
	public DemoCache save(DemoCache obj);
	public void delete(Integer id);
	public DemoCache findOne(DemoCache obj);
	public List<DemoCache> findAll();
}
