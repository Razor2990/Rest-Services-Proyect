package com.proyect.services.decorator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.proyect.services.entitys.User;
import com.proyect.services.service.IUserService;



/**
 * Clase que permite decorar el objeto del repositorio con el decorador de respuesta
 */
@Service
@Primary
public class DecoratedUserService implements IUserService<User> {

	@Autowired
	private IUserService<User> userServiceImpl;

	@Autowired
	private ResponseDecorator<User> responseDecorator;

	@Override
	public List<User> getAll() {
		List<User> userList = userServiceImpl.getAll();
		return userList;
	}

	@Override
	public Optional<User> findById(long id) {
		Optional<User> user = userServiceImpl.findById(id);
		return user;
	}

	@Override
	public boolean save(User t) {
		return userServiceImpl.save(t);
	}

	@Override
	public boolean update(User t) {
		return userServiceImpl.update(t);

	}

	@Override
	public boolean delete(long id) {
		return userServiceImpl.delete(id);

	}

	public Map<String, Object> getAllWithResponse() {
		List<User> userList = userServiceImpl.getAll();
		return responseDecorator.decorate(userList);
	}

	public Map<String, Object> findByIdResponse(long id) {
		Optional<User> user = userServiceImpl.findById(id);
		return responseDecorator.decorate(user);
	}

	public Map<String, Object> saveResponse(User t) {
		boolean flagSave = userServiceImpl.save(t);
		if(flagSave != true) {
			return responseDecorator.decorateSaveOrUpdate(true);
		} else {
			return responseDecorator.decorateValidate();
		}
	}

	public Map<String, Object> updateResponse(User t) {
		boolean flagSave = userServiceImpl.update(t);
		
		if(flagSave) {
			return responseDecorator.decorateSaveOrUpdate(flagSave);
		} else {
			return responseDecorator.decorateValidate();
		}
			

	}

	public Map<String, Object> deleteResponse(long id) {
		boolean flagDelete = userServiceImpl.delete(id);
			return responseDecorator.decorateDelete(flagDelete);

	}

}
