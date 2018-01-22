package com.genpact.ms.modules.demo.aspect;

import org.springframework.stereotype.Component;

@Component
public class DemoAspectModel {
	public long add(int a,int b){
		return a+b;
	}
}	
