package com.klu.sdp3;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EBookRepository extends JpaRepository<EBookModel,String> {

	EBookModel findByTitle(String title);

}
