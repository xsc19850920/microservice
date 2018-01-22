package com.genpact.ms.modules.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.genpact.ms.modules.demo.dao.DemoCacheRepository;
import com.genpact.ms.modules.demo.entity.DemoCache;
@Service
public class DemoCacheService implements IDemoCacheService {
	
	@Autowired
	private DemoCacheRepository demoCacheRepository;
	//缓存新增或更新的数据到缓存，其中缓存的名称为demoCache(名字和参数要保持一致要不然会有问题) 缓存的key是demoCache的id属性
	@CachePut(value="demoCache",key="#demoCache.id")
	@Override
	public DemoCache save(DemoCache demoCache) {
		DemoCache s = demoCacheRepository.save(demoCache);
		System.out.println("为 key 为 demoCache.id :" +s.getId() +"数据作了缓存");
		return s;
	}
	
	@CacheEvict(value="demoCache")
	@Override
	public void delete(Integer id) {
		demoCacheRepository.delete(id);
		System.out.println("删除 为 key 为 demoCache.id :" +id +"缓存数据");
	}

	@Cacheable(value="demoCache",key="#demoCache.id")
	@Override
	public DemoCache findOne(DemoCache demoCache) {
		DemoCache s = demoCacheRepository.findOne(demoCache.getId());
		System.out.println("为 key 为 demoCache.id :" +s.getId() +"数据作了缓存");
		return s;
	}
	
	@Cacheable(value="demoCache")
	public List<DemoCache> findAll(){
		return demoCacheRepository.findAll();
	}

}
