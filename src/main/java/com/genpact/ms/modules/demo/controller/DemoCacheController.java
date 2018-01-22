package com.genpact.ms.modules.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.ms.common.controller.BaseController;
import com.genpact.ms.common.utils.JsonResult;
import com.genpact.ms.modules.demo.entity.DemoCache;
import com.genpact.ms.modules.demo.service.IDemoCacheService;



//表示返回数据是json格式
@RestController
@RequestMapping("/demo")
public class DemoCacheController extends BaseController{
	@Autowired
	private IDemoCacheService demoCacheService;

	
	/**
	 * 方法名:put
	 * 描    述: method=RequestMethod.PUT 限定请求的方法
	 * 		  @RequestBody 限定请求的参数是json格式 如果去掉普通表单提交数据
	 * 返回值:SysUser
	 * 参    数:@param demoCache
	 * 参    数:@return
	 * 作    者:710009498
	 * 时    间:Apr 10, 2017 9:22:37 AM
	 */
	@RequestMapping(value="/put",method=RequestMethod.PUT)
	public JsonResult put(@RequestBody DemoCache demoCache) {
		return JsonResult.success(demoCacheService.save(demoCache));
	}

	@RequestMapping("/able")
	public JsonResult cacheable(DemoCache demoCache) {
		return JsonResult.success(demoCacheService.findOne(demoCache));
	}

	@RequestMapping("/evit")
	public JsonResult evit(Integer id) {
		demoCacheService.delete(id);
		return JsonResult.success(null);
	}
	
	@RequestMapping("/findAll")
	public JsonResult  findAll() {
		return JsonResult.success(demoCacheService.findAll());
	}
}
