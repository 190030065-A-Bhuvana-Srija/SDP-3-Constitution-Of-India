package com.klu.sdp3;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileModel,String>{

	ProfileModel findByEmail(String email);
	
}
