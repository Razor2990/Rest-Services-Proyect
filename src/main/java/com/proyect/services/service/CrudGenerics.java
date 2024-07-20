package com.proyect.services.service;

import java.util.List;
import java.util.Optional;

public interface CrudGenerics<T> {
	
	/**
	 * metodo generico que obtiene todos los registros
	 * @return List to Object Generic
	 */
	List<T> getAll();
	
	/**
	 * metodo generico que permite buscar por id un registro
	 * @param id
	 * @return Object Generic
	 */
	Optional<T> findById(long id);
	
	/**
	 * metodo generico que permite guardar registro
	 * @param t
	 */
	boolean save(T t);
	
	/**
	 * metodo generico quer permite actualizar registro
	 * @param t
	 */
	boolean update(T t);
	
	/**
	 * metodo generico que permite eliminar registro por id
	 * @param id
	 */
	boolean delete(long id);

}
