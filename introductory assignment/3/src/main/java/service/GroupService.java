package service;

import model.Group;
import repository.AcademicDepartmentRepository;
import repository.GroupRepository;

public class GroupService {

	private GroupRepository groupRepository;
	private AcademicDepartmentRepository departmentRepository;

	public void setGroupRepository(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	public void setDepartmentRepository(AcademicDepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	public void saveGroup(Group group) {
		if (group != null) {
			if ((group.getStudents().size() >= 6) && (group.getStudents().size() <= 30)) {
				groupRepository.save(group);
			} else throw new  IllegalStateException("Group must have 6-30 students");
		}
	}

	public void updateGroup(Group group) {
		saveGroup(group);
	}

	public Group findGroup(long id) {
		return groupRepository.findById(id).orElseThrow();
	}

	public Group findGroup(String code) {
		return groupRepository.findByGroupCode(code);
	}

	public void deleteGroup(long id) {
		if (groupRepository.findById(id).orElseThrow().getAcademicDepartment().getGroups().size() > 1) {
			groupRepository.deleteById(id);
		} else throw new IllegalStateException("Departments can't have less than 1 group");
	}

	public void deleteGroup(Group group) {
		if (group != null) {
			deleteGroup(group.getGroupId());
		}
	}

}
