package com.batch.springboot.customers.common;

public class CommonStatus {
	
	    private String status;

	    /**
		 * @param status the status to set
		 */
		public void setStatus(String status) {
			this.status = status;
		}

		public CommonStatus(String status) {
	        this.status = status;
	    }

	    public CommonStatus() {
			// TODO Auto-generated constructor stub
		}

		public String getStatus() {
	        return status;
	    }
	

}
