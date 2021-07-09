package model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "subjects")
public class Teacher extends User{

	Group curatorsGroup;
	AcademicDepartment academicDepartment;
	Group group;
	List<Subject> subjects;

	public Teacher(Long id,
	               String firstName,
	               String middleName,
	               String lastName,
	               LocalDate birthDate,
	               Character gender,
	               String status,
	               String address,
	               Group curatorsGroup,
	               AcademicDepartment academicDepartment,
	               Group group, List<Subject> subjects) {
		super(id, firstName, middleName, lastName, birthDate, gender, status, address);
		this.curatorsGroup = curatorsGroup;
		this.academicDepartment = academicDepartment;
		this.group = group;
		this.subjects = subjects;
	}
}
