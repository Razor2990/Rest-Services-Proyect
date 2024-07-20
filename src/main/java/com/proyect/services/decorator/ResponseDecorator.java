package com.proyect.services.decorator;

import java.util.List;
import java.util.Map;

/**
 * Interfaz decoradora genérica
 * @param <T>
 */
public interface ResponseDecorator<T> {
	
	Map<String, Object> decorate(List<T> itemsObject);
	
	Map<String, Object> decorate(Object itemsObject);
	
	Map<String, Object> decorate();
	
	Map<String, Object> decorateSaveOrUpdate(boolean flag);
	
	Map<String, Object> decorateValidate();
	
	Map<String, Object> decorateDelete(boolean flagValidation);
	
}
