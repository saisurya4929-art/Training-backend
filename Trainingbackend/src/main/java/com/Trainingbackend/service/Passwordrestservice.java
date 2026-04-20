package com.Trainingbackend.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Trainingbackend.entity.Passwordrest;
import com.Trainingbackend.entity.User;
import com.Trainingbackend.repository.PasswordRepository;
import com.Trainingbackend.repository.UserRepository;

@Service
public class Passwordrestservice implements Passwordrestserviceinter {
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private PasswordRepository passrepo;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
	

	@Override
	public String cretaeResetToken(String email) {
		User user = userrepo.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("Email not found");
        }

        String token = UUID.randomUUID().toString();

        Passwordrest resetToken = new Passwordrest();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpiryDate(LocalDateTime.now().plusMinutes(15));

        passrepo.save(resetToken);
		
		return token;
	}

	@Override
	public void restPassword(String token, String newPassword) {
		Passwordrest resetToken = passrepo.findByToken(token);

        if (resetToken == null) {
            throw new RuntimeException("Invalid token");
        }

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expired");
        }

        if (newPassword == null || newPassword.length() < 8) {
            throw new RuntimeException("Password must be at least 8 characters");
        }

        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userrepo.save(user);

        passrepo.delete(resetToken);
	
		
	}

}
