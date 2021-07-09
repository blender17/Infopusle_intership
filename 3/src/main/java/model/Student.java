package model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Student extends User{

	Group group;

	public Student(Long id,
	               String firstName,
	               String middleName,
	               String lastName,
	               LocalDate birthDate,
	               Character gender,
	               String status,
	               String address,
	               Group group) {
		super(id, firstName, middleName, lastName, birthDate, gender, status, address);
		this.group = group;
	}

}
