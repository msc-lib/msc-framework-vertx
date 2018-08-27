package fun.pplm.msc.framework.vertx.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

public final class ResHelper {
	
	public static ResBean success() {
		return success(null);
	}
	
	public static ResBean success(Object content) {
		return new ResBean(content);
	}
	
	public static class ResBean {
		private String code = "0";
		private String message = "success";
		
		@JsonInclude(JsonInclude.Include.NON_NULL)
		private Object content;

		public ResBean() {
			super();
		}

		public ResBean(Object content) {
			super();
			this.content = content;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Object getContent() {
			return content;
		}

		public void setContent(Object content) {
			this.content = content;
		}
		
	}
	
}
