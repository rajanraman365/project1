package com.banking.dtos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "loan")
@Setter
@Getter
public class LoanDTO extends BaseDTO {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account", nullable = false)
	private AccountDTO account;
	
	@Column(name = "account_id")
	private Long accountId;
	
	@Column(name = "loan_amount")
	private Double originalLoanAmount;
	
	@Column(name = "monthly_payment_amount")
	private Double monthlyPaymentAmount;
	
	@Column(name = "total_amount")
	private Double totalAmount;
	
	@Column(name = "payable_amount")
	private Double payableAmount;
	
	@Column(name = "simple_interest")
	private Double simpleInterest;
	
	@Column(name = "rate")
	private Integer interestRate;
	
	@Column(name = "term_length")
	private Integer termLength;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "users", nullable = false)
	private UserDTO user;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "loan_name", length = 255)
	private String loanName;
	
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
