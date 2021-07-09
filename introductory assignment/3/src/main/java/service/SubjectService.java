package service;

import model.Subject;
import repository.SubjectRepository;

public class SubjectService {

	private SubjectRepository subjectRepository;

	public void setSubjectRepository(SubjectRepository subjectRepository) {
		this.subjectRepository = subjectRepository;
	}


	public void saveSubject(Subject subject) {
		subjectRepository.save(subject);
	}

	public void updateSubject(Subject subject) {
		saveSubject(subject);
	}

	public Subject findSubject(long id) {
		return subjectRepository.findById(id).orElseThrow();
	}

	public Subject findSubject(String name) {
		return subjectRepository.findByName(name).orElseThrow();
	}

	public void deleteSubject(long id) {
		subjectRepository.deleteById(id);
	}

	public void deleteSubject(Subject subject) {
		if (subject != null) {
			deleteSubject(subject.getSubjectId());
		}
	}

}
