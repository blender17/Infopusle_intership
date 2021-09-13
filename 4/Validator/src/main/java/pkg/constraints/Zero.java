package pkg.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ZeroValidator.class)
public @interface Zero {

	String message() default "Value must be zero";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
