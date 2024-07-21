package com.banking.dao;

import java.util.List;

import com.banking.dtos.UserDTO;





public interface UserDAOInt {

	public long add(UserDTO dto);	
	
	public void delete(UserDTO dto);
	
	public void deleteDonar(UserDTO dto);
	
	public UserDTO findBypk(long pk);
	
	public UserDTO findByLogin(String login);
	
	public void update(UserDTO dto);
	
	public List<UserDTO> list();
	
	public List<UserDTO>list(int pageNo,int pageSize);
	
	public List<UserDTO> search(UserDTO dto);
	
	public List<UserDTO> search(UserDTO dto,int pageNo,int pageSize);
	
	public UserDTO authentication(UserDTO dto);

	String findByMaxEmployeeId();

	UserDTO findByEmployeeId(String pk);
	
	public void accept(UserDTO dto);
	
	public List<UserDTO> getAllDrivers(UserDTO dto);

}
