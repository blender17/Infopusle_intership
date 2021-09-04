import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {
		Student student = new Student();
		student.setFirstName("Christian");
		student.setMiddleName("R.");
		student.setLastName("Counts");
		student.setBirthday(LocalDate.now());
		student.setGender("Female");

		Mapper mapper = new Mapper();
		mapper.fieldNamesByAnnotation(true);
		StudentDTO studentDTO = mapper.map(student, StudentDTO.class);
		System.out.println(student);
		System.out.println();
		System.out.println(studentDTO);

		System.out.println();
		Student student1 = mapper.map(studentDTO, Student.class);
		System.out.println(student);
		System.out.println();
		System.out.println(studentDTO);
	}

}
