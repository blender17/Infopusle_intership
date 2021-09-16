package pkg.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ZeroValidator implements ConstraintValidator<Zero, Number> {
	@Override
	public boolean isValid(Number number, ConstraintValidatorContext constraintValidatorContext) {
		if (number == null) {
			return true;
		}
		return number.doubleValue() == 0;
	}
}
