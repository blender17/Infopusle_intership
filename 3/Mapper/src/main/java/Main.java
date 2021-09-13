import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import json.JSONObject;
import json.JSONParser;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws JsonProcessingException {
		Student student = new Student();
		student.setStudentId(1235L);
		student.setFirstName("Christian");
		student.setMiddleName("R.");
		student.setLastName("Counts");
		student.setGender("Female");
		student.nums = new double[] {2.0, 1.1 , 3.3};

		Mapper mapper = new Mapper();
		mapper.fieldNamesByAnnotation(true);
		StudentDTO studentDTO = mapper.map(student, StudentDTO.class);
		/*System.out.println(student);
		System.out.println();
		System.out.println(studentDTO);

		System.out.println();
		Student student1 = mapper.map(studentDTO, Student.class);
		System.out.println(student);
		System.out.println();
		System.out.println(studentDTO);*/
		student.setStudent(studentDTO);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(student);
		System.out.println(json);

		Arrays.stream(JSONParser.countArraysAndObjects(json)).forEach(System.out::println);

		try {
			JSONObject jsonObject = JSONParser.parseToJSONObject(json);
			System.out.println(jsonObject.getElement("student").getJSONObject().getElement("lastName").getStringValue());
			Arrays.stream(jsonObject.getElement("nums").getJSONArray()).forEach(jsonObject1 -> System.out.println(jsonObject1.pop().getDoubleValue()));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
