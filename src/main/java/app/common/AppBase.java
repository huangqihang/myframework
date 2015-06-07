package app.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 任何类都基类
 * 提供所有业务类都需要的基础功能：日志
 * 
 * MyBaseAction extends Base
 * 
 * MyBaseService extends Base
 * 
 * MyBaseDao extends Base
 */
public abstract class AppBase {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void trace(Object msg) {
		logger.trace(msg.toString());
	}
	
	public void debug(Object msg) {
		logger.debug(msg.toString());
	}
	
	public void info(Object msg) {
		logger.info(msg.toString());
	}
	
	public void warn(Object msg) {
		logger.warn(msg.toString());
	}
	
	public void error(Throwable t,Object... error) {
		logger.error(error == null ? "" : error[0].toString(), t);
	}
	
	public String currentMethod() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}
	
	public String toJson(Object obj) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return  gson.toJson(obj);
	}
	
}
