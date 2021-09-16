package pkg;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class Validator {

	public boolean validate(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		for (Field field : fields) {
			Annotation[] annotations = field.getAnnotations();
			for (Annotation annotation : annotations) {
				Optional<Annotation> optionalAnnotation = Arrays.stream(annotation.annotationType().getAnnotations())
						.filter(annotation1 -> annotation1 instanceof Constraint)
						.findFirst();
				if (optionalAnnotation.isPresent()) {
					Constraint constraint = (Constraint) optionalAnnotation.get();
					try {
						Class<? extends ConstraintValidator<?, ?>> validator = constraint.validatedBy()[0];
						Method method = validator.getDeclaredMethod("isValid", Object.class, ConstraintValidatorContext.class);
						ConstraintValidator<?, ?> validatorInstance = validator.getDeclaredConstructor().newInstance();
						field.setAccessible(true);
						if (!((boolean) method.invoke(validatorInstance, field.get(o), null))) {
							return false;
						}
					} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return true;
	}

}
