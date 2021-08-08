package com.costatayna.mc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.costatayna.mc.domain.Cliente;
import com.costatayna.mc.dto.ClienteDTO;
import com.costatayna.mc.repositories.ClienteRepository;
import com.costatayna.mc.resources.exceptions.FildMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		map.get("id");
		Integer uriId = Integer.parseInt(map.get("id"));
		List<FildMessage> list = new ArrayList<>();

		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux != null && !aux.getId().equals(uriId)) {
			list.add(new FildMessage("email", "Email já existente"));
		}

		for (FildMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFildName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
	
	
}