package demo.dwr.async;

import app.entity.Person;

import com.google.gson.JsonObject;

/**
 * Remote calls with DWR - handling the asynchronous nature of AJAX with callbacks
 * 远程调用-DWR通过callback回调函数实现AJAX的异步功能
 *
 */
public class Remote {
	
	
	public String getData(int id) {
		
		JsonObject json = new JsonObject();
		json.addProperty("id", id);
		json.addProperty("name", "张三");
		json.addProperty("age", "20");
		
		String data = json.toString();

		return data;
	}
	
	
	public void doSomethingWithPerson(Person p) {
	    
		System.out.println(p);
		
	}
	
}
