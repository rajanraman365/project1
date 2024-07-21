package com.banking.dtos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Setter
@Getter
public class UserDTO extends BaseDTO {

	@Column(name = "first_name", length = 255)
	private String firstName;
	@Column(name = "last_name", length = 255)
	private String lastName;
	@Column(name = "login", length = 255)
	private String login;
	@Column(name = "password", length = 255)
	private String password;
	@Column(name = "role_id")
	private Long roleId;
	@Column(name = "role_name", length = 255)
	private String roleName;
	
	@OneToMany(mappedBy = "user",cascade = { CascadeType.ALL })
	private List<AccountDTO> list ;
	
	@OneToMany(mappedBy = "user",cascade = { CascadeType.ALL })
	private List<LoanDTO> loanList ;
	
	@OneToMany(mappedBy = "user",cascade = { CascadeType.ALL })
	private List<CreditCardDTO> creditCardList ;
	
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
