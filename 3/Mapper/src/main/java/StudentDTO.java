import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class StudentDTO {

	private String firstName;
	private String lastName;
	@Column("middleName")
	private String mName;
	private String gender;
	private LocalDate birthday;

}
