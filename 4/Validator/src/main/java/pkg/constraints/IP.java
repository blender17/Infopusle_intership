package pkg.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IPValidator.class)
public @interface IP {

	String message() default "IP address is incorrect";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
