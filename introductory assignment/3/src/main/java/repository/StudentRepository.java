package repository;

import model.Group;
import model.Student;

import java.util.List;

public interface StudentRepository extends Repository<Student, Long> {

	List<Student> findAllByGroup(Group group);

}
