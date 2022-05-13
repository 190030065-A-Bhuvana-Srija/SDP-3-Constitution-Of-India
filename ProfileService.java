package com.klu.sdp3;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProfileService {
	private final ProfileRepository profilerepository;
	
	public ProfileService(ProfileRepository profilerepository) {
		this.profilerepository = profilerepository;
	}
	
	public void save(ProfileModel profilemodel) {
		profilerepository.save(profilemodel);
	}

	public ProfileModel findByEmail(String email) {
		return  profilerepository.findByEmail(email);
	}
}
