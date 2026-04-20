package com.Trainingbackend.service;

public interface Passwordrestserviceinter {
	String cretaeResetToken(String email);
	
	void restPassword(String token, String  newPassword);

}
