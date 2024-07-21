package com.banking.form;

import com.banking.dtos.BaseDTO;
import com.banking.dtos.UserDTO;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class LoginForm extends BaseForm {

	private String login;
	private String password;
	@Override
	public BaseDTO getDTO() {
		UserDTO bean = new UserDTO();
		bean.setId(id);
		bean.setLogin(login);
		bean.setPassword(password);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		UserDTO bean = (UserDTO)bDto;
		id = bean.getId();
		login = bean.getLogin();
		password = bean.getPassword();
		
	}

}
