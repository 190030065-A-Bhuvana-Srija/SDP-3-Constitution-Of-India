package com.klu.sdp3;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseModel,String> {

	CourseModel findByTitle(String title);

	CourseModel save(CourseModel courseModel);

}
