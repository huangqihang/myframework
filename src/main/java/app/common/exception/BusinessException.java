package app.common.exception;

/**
 * 当发生此异常时，事务需要回滚
 * 通过配置事务管理器的rollback-for属性实现
 * rollback-for=BusinessException.class
 */
public class BusinessException extends RuntimeException {
	
}
