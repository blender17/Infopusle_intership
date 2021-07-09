package repository;

import model.AcademicDepartment;
import model.Teacher;

import java.util.List;

public interface TeacherRepository extends Repository<Teacher, Long> {

	List<Teacher> findAllByDepartment(AcademicDepartment academicDepartment);

}
