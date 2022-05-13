package com.klu.sdp3;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class EBookService {
	private EBookRepository ebookrepository;
	
	public EBookService(EBookRepository ebookrepository) {
		this.ebookrepository = ebookrepository;
	}
	
	public void save(EBookModel ebookmodel) {
		ebookrepository.save(ebookmodel);
	}

	public EBookModel findByTitle(String title) {
		return  ebookrepository.findByTitle(title);
	}

	// title, author, cost, language, filesize

	public int updateEBook(String title, String author, int cost, String language, int filesize) {
		EBookModel  updatebook = ebookrepository.findByTitle(title);
        updatebook.setAuthor(author);
        updatebook.setCost(cost);
        updatebook.setLanguage(language);
        updatebook.setFilesize(filesize);
        if(ebookrepository.save(updatebook) != null)
            return 1;
        else
            return 0;
	}

	public void saveBook(EBookModel ebookmodel) {
		ebookrepository.save(ebookmodel);
	}

	public List<EBookModel> getAll() {
		return ebookrepository.findAll();
	}

	public EBookModel store(EBookModel updatebook) {
		return ebookrepository.save(updatebook);
		
	}
}
