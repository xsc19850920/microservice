package com.genpact.ms.common.utils;

public class JsonResult {
	private String status;
	private Object data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public JsonResult(String status, Object data) {
		this.status = status;
		this.data = data;
	}

	public static JsonResult success(Object data) {
		JsonResult result = new JsonResult("success", data);
		return result;
	}
	
	public static JsonResult error(String message) {
		JsonResult result = new JsonResult("error", message);
		return result;
	}
}