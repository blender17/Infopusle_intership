package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "departments")
public class Faculty {

	Long facultyId;
	String facultyCode;
	String facultyName;
	University university;
	List<AcademicDepartment> departments;
}
