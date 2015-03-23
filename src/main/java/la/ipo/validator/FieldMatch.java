/**
 * 
 */
package la.ipo.validator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 2014年2月13日 上午10:29:53
 * @author WANGJUN
 * 验证两个属性是否一致
 * Example, compare 1 pair of fields:
 * @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
 * 
 * Example, compare more than 1 pair of fields:
 * @FieldMatch.List({
 *   @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
 *   @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")})
 *
 */
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
@Documented
public @interface FieldMatch {

	String message() default "{constraints.fieldmatch}";
	
	Class<?>[] groups() default{};
	
	Class<? extends Payload>[] payload() default {};
	
	String first();
	
	String second();
	
	@Target({TYPE, ANNOTATION_TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List
	{
		FieldMatch[] value();
	}
}
