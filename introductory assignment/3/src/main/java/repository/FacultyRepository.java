package repository;

import model.AcademicDepartment;
import model.Faculty;

import java.util.List;

public interface FacultyRepository extends Repository<Faculty, Long> {

	Faculty findByFacultyCode(String facultyCode);

	Faculty findByFacultyName(String facultyName);

	List<Faculty> findAllByDepartment(AcademicDepartment academicDepartment);

}
