package com.banking.dtos;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "account")
@Setter
@Getter
public class AccountDTO extends BaseDTO {

	@Column(name = "user_id")
	private Long userId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user", nullable = false)
	private UserDTO user;
	@Column(name = "account_no")
	private Integer accountNumber;
	@Column(name = "account_type")
	private String accountType;
	@Column(name = "amount")
	private Long amount;
	@Column(name = "account_name", length = 255)
	private String accountName;
	@Transient
	private String from;
	@Transient
	private String to;
	@Column(name = "status",length = 255)
	private String status;
	
	@OneToMany(mappedBy = "accounts",cascade = { CascadeType.ALL })
	private List<CreditCardDTO> list ;
	
	@OneToMany(mappedBy = "account",cascade = { CascadeType.ALL })
	private List<LoanDTO> loanList ;
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
