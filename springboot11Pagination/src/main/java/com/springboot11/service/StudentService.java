package com.springboot11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot11.model.Student;
import com.springboot11.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentReposiotry;
    /**
     * Get students with pagination and sorting
     * 
     * @param pageNo   - Page number (0-based index)
     * @param pageSize - Number of records per page
     * @param sortBy   - Field to sort by
     * @param sortDir  - Sort direction (asc/desc)
     * @return Page of Student objects
     */
	
	public Page<Student> getStudentWithPagination(int PageNo,int pageSize,String sortBy,String sortDir){
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
				?Sort.by(sortBy).ascending()
				:Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(PageNo, pageSize,sort);
		
		return studentReposiotry.findAll(pageable);
	}

}
