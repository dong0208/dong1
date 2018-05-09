package com.kaishengit.util;

public class AjaxResult {

	public static final String STATE_SUCCESS = "success";
	public static final String STATE_ERROR = "error";
	
	private String state;
	private Object data;
	private String message;

	public static AjaxResult success(){
		AjaxResult result = new AjaxResult();
		result.setState(STATE_SUCCESS);
		return result;
	}
	public static AjaxResult success(Object data){
		AjaxResult result = new AjaxResult();
		result.setState(STATE_SUCCESS);
		result.setData(data);
		return result;
	}
	
	public static AjaxResult error(String message){
		AjaxResult result = new AjaxResult();
		result.setState(STATE_ERROR);
		result.setMessage(message);
		return result;
	}
	
	public AjaxResult() {
	}

	
	/*public AjaxResult(Object data) {
		this.state = STATE_SUCCESS;
		this.data = data;
	}

	public AjaxResult(String message) {
		this.state = STATE_ERROR;
		this.message = message;
	}*/

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
