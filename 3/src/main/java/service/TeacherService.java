package service;

import model.Teacher;
import repository.TeacherRepository;

public class TeacherService {

	private TeacherRepository teacherRepository;

	public void setTeacherRepository(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}

	public void saveTeacher(Teacher teacher) {
		teacherRepository.save(teacher);
	}

	public void updateTeacher(Teacher teacher) {
		saveTeacher(teacher);
	}

	public Teacher findTeacher(long id) {
		return teacherRepository.findById(id).orElseThrow();
	}

	public void deleteTeacher(long id) {
		if (teacherRepository.findById(id).orElseThrow().getAcademicDepartment().getTeachers().size() > 3) {
			teacherRepository.deleteById(id);
		} else throw new IllegalStateException("Academic department can't have less than 3 teacher");
	}

	public void deleteTeacher(Teacher teacher) {
		if (teacher != null) {
			deleteTeacher(teacher.getId());
		}
	}
}
