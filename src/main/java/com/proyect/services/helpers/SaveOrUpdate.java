package com.proyect.services.helpers;

import java.util.Optional;

public class SaveOrUpdate {

	
	/**
	 * metodo que realiza la lógica y validacion de inserción o actualización
	 * @param <T>
	 * @param t
	 * @param idValue
	 * @return boolean
	 */
	public static <T> boolean logic(T t, Optional<T> idValue) {
		
		if(idValue.isPresent()) {
			System.out.println("El usuario ya se encuetra registrado se procede sólo actualizar la información.");
			return false;
		} else {
			System.out.println("El usuario no se encuentra registrado por lo tanto se procede a registrar.");
			return true;
		}
	}
	
}
