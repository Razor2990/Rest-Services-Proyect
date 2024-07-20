package com.proyect.services.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.services.entitys.User;
import com.proyect.services.repository.UsersRepository;


@Service
public class UserServiceImpl<T> implements IUserService<T> {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UsersRepository userRespository;

	@Override
	public List<User> getAll() {
		logger.info("Consulta correcta");
		return (List<User>) userRespository.findAll();
	}

	@Override
	public Optional<User> findById(long id) {
		logger.info("Consulta correcta por ID");
		return userRespository.findById(id);
	}

	@Override
	public boolean save(User t) {
		boolean flag = false;
		try {
			Optional<User> searchObject = userRespository.findById(t.getId());

			if (searchObject.isPresent() && t.equals(searchObject.get())) {
				logger.info("El objeto a insertar es igual a uno existente");
				flag = true;
			} else {
				userRespository.save(t);
				logger.info("Registro guardado correctamente");
				flag = false;
			}

		} catch (Exception e) {
			logger.info("Registro no fue almacenado. Error");
			e.getStackTrace();
		}
		return flag;

	}

	@Override
	public boolean update(User t) {
	    boolean flag = false;
	    try {

	    	userRespository.save(t);
	        logger.info("Registro actualizado correctamente");
	        flag = true;

	    } catch (Exception e) {
	        logger.error("Error al intentar actualizar el registro.", e);
	        e.printStackTrace();
	    }
	    return flag;
	}

	@Override
	public boolean delete(long id) {
		// Antes de la eliminación, verifica si el usuario existe
		Optional<User> existingUser = userRespository.findById(id);
		if (existingUser.isPresent()) {
			// El usuario existe, procede con la eliminación
			userRespository.deleteById(id);
			logger.info("Registro eliminado correctamente");
			// Después de la eliminación, verifica si el usuario ya no existe
			Optional<User> deletedUser = userRespository.findById(id);
			// Si el usuario ya no existe, la eliminación fue exitosa
			return !deletedUser.isPresent();
		}
		// Si el usuario no existe, no se puede eliminar
		logger.error("El registro no se puede eliminar");
		return false;

	}

}
