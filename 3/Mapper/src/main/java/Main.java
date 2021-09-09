import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import json.JSONObject;
import json.JSONParser;

public class Main {

	public static void main(String[] args) throws JsonProcessingException {
		Student student = new Student();
		student.setStudentId(1235L);
		student.setFirstName("Christian");
		student.setMiddleName("R.");
		student.setLastName("Counts");
		student.setGender("Female");

		/*Mapper mapper = new Mapper();
		mapper.fieldNamesByAnnotation(true);
		StudentDTO studentDTO = mapper.map(student, StudentDTO.class);
		System.out.println(student);
		System.out.println();
		System.out.println(studentDTO);

		System.out.println();
		Student student1 = mapper.map(studentDTO, Student.class);
		System.out.println(student);
		System.out.println();
		System.out.println(studentDTO);*/

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(student);
		System.out.println(json);

		try {
			JSONObject jsonObject = JSONParser.parseToJSONObject(json);
			System.out.println(jsonObject.getElement("gender").getStringValue());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
