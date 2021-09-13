package pkg.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class IPValidator implements ConstraintValidator<IP, String> {
	@Override
	public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
		if (s.matches("^(\\d{1,3}\\.){3}\\d{1,3}$")) {
			/*return Arrays.stream(s.split("\\."))
					.mapToInt(Integer::parseInt)
					.filter(value -> value > 255 || value < 0)
					.findAny()
					.isEmpty();*/
			return Arrays.stream(s.split("\\."))
					.mapToInt(Integer::parseInt)
					.filter(value -> value <= 255 && value >= 0)
					.count() == 4;
		}
		return false;
	}
}
