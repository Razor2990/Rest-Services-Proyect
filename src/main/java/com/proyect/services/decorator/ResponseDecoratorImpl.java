package com.proyect.services.decorator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.proyect.services.entitys.MessageResponse;
import com.proyect.services.utils.EnumStatusResponse;

/**
 * Clase que nos permite decorar la respuesta con el objeto
 * @param <T>
 */
@Service
public class ResponseDecoratorImpl<T> implements ResponseDecorator<T> {

	@Override
	public Map<String, Object> decorate(List<T> itemsList) {
		EnumStatusResponse statusResponse = EnumStatusResponse.OK;

		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("data", itemsList);
		responseMap.put("messageResponse", new MessageResponse(statusResponse.getCode(), statusResponse.getMessage()));

		return responseMap;
	}

	@Override
	public Map<String, Object> decorate(Object itemObject) {
		EnumStatusResponse statusResponse = EnumStatusResponse.OK;

		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("data", itemObject);
		responseMap.put("messageResponse", new MessageResponse(statusResponse.getCode(), statusResponse.getMessage()));

		return responseMap;
	}

	@Override
	public Map<String, Object> decorate() {
		EnumStatusResponse statusResponse = EnumStatusResponse.CREATED;

		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("messageResponse", new MessageResponse(statusResponse.getCode(), statusResponse.getMessage()));

		return responseMap;
	}

	@Override
	public Map<String, Object> decorateSaveOrUpdate(boolean flag) {
		if (flag) {
			EnumStatusResponse statusResponse = EnumStatusResponse.CREATED;

			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("messageResponse",
					new MessageResponse(statusResponse.getCode(), statusResponse.getMessage()));

			return responseMap;
		} else {
			EnumStatusResponse statusResponse = EnumStatusResponse.CREATED_UPDATE;

			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("messageResponse",
					new MessageResponse(statusResponse.getCode(), statusResponse.getMessage()));
			return responseMap;
		}
		
	}

	@Override
	public Map<String, Object> decorateValidate() {
		EnumStatusResponse statusResponse = EnumStatusResponse.CONFLICT;

		Map<String, Object> responseMap = new HashMap<>();

		responseMap.put("messageResponse", new MessageResponse(statusResponse.getCode(), statusResponse.getMessage()));

		return responseMap;
	}

	@Override
	public Map<String, Object> decorateDelete(boolean flagValidation) {
		EnumStatusResponse statusResponse;
		
		if(flagValidation) {
			 statusResponse = EnumStatusResponse.OK_DELETE;
		} else {
			 statusResponse = EnumStatusResponse.CONFLICT_DELETE;
		}
		
		Map<String, Object> responseMap = new HashMap<>();

		responseMap.put("messageResponse", new MessageResponse(statusResponse.getCode(), statusResponse.getMessage()));

		return responseMap;
	}

}
