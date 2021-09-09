import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StudentDTO {

	private String firstName;
	private String lastName;
	@Column("middleName")
	private String mName;
	private String gender;

}
