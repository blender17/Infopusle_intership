import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class Student implements Serializable {

	public double[] nums;
	private StudentDTO student;
	private Long studentId;
	private String firstName;
	private String lastName;
	private String middleName;
	private Integer age;
	private String status;
	private String gender;
	private String photoUrl;

	private String country;
	private String state;
	private String city;
	private String address;
	private String zipCode;
	private String phoneNumber;
	private String email;
	private String motherName;
	private String fatherName;
	private String motherPhoneNumber;
	private String fatherPhoneNumber;

}
