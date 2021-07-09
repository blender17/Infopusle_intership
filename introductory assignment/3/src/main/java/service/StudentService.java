package service;

import model.Student;
import repository.StudentRepository;


public class StudentService {

	private StudentRepository studentRepository;

	public void setStudentRepository(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public void saveStudent(Student student) {
		studentRepository.save(student);
	}

	public void updateStudent(Student student) {
		saveStudent(student);
	}

	public Student findStudent(long id) {
		return studentRepository.findById(id).orElseThrow();
	}

	public void deleteStudent(long id) {
		if (studentRepository.findById(id).orElseThrow().getGroup().getStudents().size() > 6) {
			studentRepository.deleteById(id);
		} else throw new IllegalStateException("Group can't have less than 6 students");
	}

	public void deleteStudent(Student student) {
		if (student != null) {
			deleteStudent(student.getId());
		}
	}

}
