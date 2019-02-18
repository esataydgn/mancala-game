package com.backbase.model.response;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class ResponseBody {

	private Integer id;
	private String uri;
	private HashMap<Integer, String> status;

	public HashMap<Integer, String> getStatus() {
		return status;
	}

	public void setStatus(HashMap<Integer, String> status) {
		this.status = status;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
