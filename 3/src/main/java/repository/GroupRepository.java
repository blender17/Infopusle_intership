package repository;

import model.AcademicDepartment;
import model.Group;
import model.Teacher;

import java.util.List;

public interface GroupRepository extends Repository<Group, Long> {

	Group findByGroupCode(String groupCode);

	Group findByCurator(Teacher curator);

	List<Group> findAllBySpecCode(String specCode);

	List<Group> findAllByDepartment(AcademicDepartment department);

}
