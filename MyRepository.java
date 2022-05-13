package com.klu.sdp3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyRepository extends JpaRepository<MyModel,String> {

	MyModel findByEmailAndPassword(String email, String password);

}
