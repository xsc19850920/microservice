package com.genpact.ms.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties(prefix = "site")
public class SiteConfig {

    private String name;
    private String baseUrl;
    private String staticUrl;
    private int pageSize;
    private String uploadPath;
    private String theme;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	public String getStaticUrl() {
		return staticUrl;
	}
	public void setStaticUrl(String staticUrl) {
		this.staticUrl = staticUrl;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

}
