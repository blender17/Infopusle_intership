package repository;

import model.AcademicDepartment;
import model.Faculty;

import java.util.List;


public interface AcademicDepartmentRepository extends Repository<AcademicDepartment, Long>{

	AcademicDepartment findByDepartmentCode(String departmentCode);

	AcademicDepartment findByDepartmentName(String departmentName);

	List<AcademicDepartment> findAllByFaculty(Faculty faculty);

	List<AcademicDepartment> findAllByFacultyId(Long id);

}
