package pkg.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
	@Override
	public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
		return s.matches("^(?:\\+38)?\\s?0\\s?\\d{2}\\s?\\d{7}$");
	}
}
