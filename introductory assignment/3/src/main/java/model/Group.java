package model;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"subjects", "students"})
public class Group {

	Long groupId;
	String groupCode;
	String specializationCode;
	@NonNull
	Teacher curator;
	AcademicDepartment academicDepartment;
	List<Student> students;
	List<Subject> subjects;

}
