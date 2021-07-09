package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"groups", "teachers"})
public class AcademicDepartment {

	Long departmentId;
	String departmentCode;
	String departmentName;
	Faculty faculty;
	List<Group> groups;
	List<Teacher> teachers;

}
