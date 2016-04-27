package com.truemega.dto;

import java.io.Serializable;

public class NotificationDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	   private Integer id;
	 
	    private Serializable attachFile;
	    
	  
	    private String subject;
	   
	    
	    private String templatename;


		public Integer getId() {
			return id;
		}


		public void setId(Integer id) {
			this.id = id;
		}


		public Serializable getAttachFile() {
			return attachFile;
		}


		public void setAttachFile(Serializable attachFile) {
			this.attachFile = attachFile;
		}


		public String getSubject() {
			return subject;
		}


		public void setSubject(String subject) {
			this.subject = subject;
		}


		public String getTemplatename() {
			return templatename;
		}


		public void setTemplatename(String templatename) {
			this.templatename = templatename;
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			NotificationDTO other = (NotificationDTO) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}
	    

}
