package andras.database;

import java.util.UUID;

public class Greeting {

	private String id = UUID.randomUUID().toString();
	
	private String msg;
	


	@SuppressWarnings("unused")
	private Greeting() {
		
	}
	
	public Greeting (String msg) {
		this.msg= msg;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String toString() {
		return "Greeting [msg="+msg+"]";
	}
	
	
}
