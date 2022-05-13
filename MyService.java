package com.klu.sdp3;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MyService {
	private final MyRepository MyRepository;
	
	public MyService(MyRepository MyRepository) {
		this.MyRepository=MyRepository;
	}
	
	public void save(MyModel model) {
		MyRepository.save(model);
	}

	public MyModel findByEmailAndPassword(String email, String password) {
		return  MyRepository.findByEmailAndPassword(email, password);
	}
}
