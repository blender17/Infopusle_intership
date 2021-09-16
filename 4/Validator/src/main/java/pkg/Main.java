package pkg;

import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Validation;

public class Main {

	public static void main(String[] args) {
//		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

		POJO pojo = new POJO();
		pojo.setPhoneNumber("+30951695243");
		pojo.setIpAddress("254.274.0.1");
		pojo.setNum(0.0000000000);

//		validator.validate(pojo).forEach(violation -> System.out.println(violation.getMessage()));

		Validator validator = new Validator();

		System.out.println(validator.validate(pojo));
	}
}
