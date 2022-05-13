package com.klu.sdp3;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class CourseService {
	private CourseRepository courserepository;
	
	public CourseService(CourseRepository courserepository) {
		this.courserepository = courserepository;
	}
	
	public void save(CourseModel CourseModel) {
		courserepository.save(CourseModel);
	}

	public CourseModel findByTitle(String title) {
		return  courserepository.findByTitle(title);
	}

	// title, writer, duration, language

	public void saveBook(CourseModel CourseModel) {
		courserepository.save(CourseModel);
	}

	public List<CourseModel> getAll() {
		return courserepository.findAll();
	}

	public CourseModel store(CourseModel updatebook) {
		return courserepository.save(updatebook);
		
	}

	public int updateCourse(String title, String writer, int duration, String language) {
		CourseModel  updatebook = courserepository.findByTitle(title);
        updatebook.setWriter(writer);
        updatebook.setDuration(duration);
        updatebook.setLanguage(language);
        if(courserepository.save(updatebook) != null)
            return 1;
        else
            return 0;
	}
}
