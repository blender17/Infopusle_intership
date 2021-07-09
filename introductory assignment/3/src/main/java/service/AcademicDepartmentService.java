package service;

import model.AcademicDepartment;
import repository.AcademicDepartmentRepository;

public class AcademicDepartmentService {

	private AcademicDepartmentRepository academicDepartmentRepository;

	//Dependency injection
	public void setDepartmentRepository(AcademicDepartmentRepository academicDepartmentRepository) {
		this.academicDepartmentRepository = academicDepartmentRepository;
	}

	public void saveDepartment(AcademicDepartment academicDepartment){
		if (academicDepartment != null) {
			academicDepartmentRepository.save(academicDepartment);
		}
	}

	public AcademicDepartment findDepartment(long id){
		return academicDepartmentRepository.findById(id).orElseThrow();
	}

	//Search by department code or name
	public AcademicDepartment findDepartment(String request, String type) {
		return switch (type) {
			case "code" -> academicDepartmentRepository.findByDepartmentCode(request);
			case "name" -> academicDepartmentRepository.findByDepartmentName(request);
			default -> throw new IllegalStateException("Unexpected value: " + type);
		};
	}

	public void updateDepartment(AcademicDepartment academicDepartment){
		saveDepartment(academicDepartment);
	}

	public void deleteDepartment(AcademicDepartment department) {
		if (department != null) {
			if (academicDepartmentRepository.findAllByFaculty(department.getFaculty()).size() > 1) {
				academicDepartmentRepository.delete(department);
			} else throw new IllegalStateException("Faculty can't have less than 1 academic department");
		}
	}

}
