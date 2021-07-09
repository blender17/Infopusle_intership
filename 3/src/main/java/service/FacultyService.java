package service;

import model.Faculty;
import repository.FacultyRepository;

public class FacultyService {

	private FacultyRepository facultyRepository;

	public void setFacultyRepository(FacultyRepository facultyRepository) {
		this.facultyRepository = facultyRepository;
	}

	public void saveFaculty(Faculty faculty) {
		if (faculty != null) {
			facultyRepository.save(faculty);
		}
	}

	public Faculty findFaculty(long id){
		return facultyRepository.findById(id).orElseThrow();
	}

	//Search by department code or name
	public Faculty findDepartment(String request, String type) {
		return switch (type) {
			case "code" -> facultyRepository.findByFacultyCode(request);
			case "name" -> facultyRepository.findByFacultyName(request);
			default -> throw new IllegalStateException("Unexpected value: " + type);
		};
	}

	public void updateFaculty(Faculty faculty){
		if (faculty != null) {
			facultyRepository.save(faculty);
		}
	}

	public void deleteFaculty(Faculty faculty) {
		saveFaculty(faculty);
	}

}
