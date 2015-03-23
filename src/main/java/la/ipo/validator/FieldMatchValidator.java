/**
 * 
 */
package la.ipo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 2014年2月13日 上午10:36:10
 * 
 * @author WANGJUN
 * 
 */
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

	private static final Logger LOGGER = LoggerFactory.getLogger(FieldMatchValidator.class);

	private String firstFieldName;
	private String secondFieldName;

	@Override
	public void initialize(FieldMatch constraintAnnotation) {
		this.firstFieldName = constraintAnnotation.first();
		this.secondFieldName = constraintAnnotation.second();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		try {
			final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
			final Object secondObj = BeanUtils.getProperty(value, secondFieldName);
			return firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
		} catch (final Exception e) {
			LOGGER.error("FieldMatch comparing failed.", e);
		}
		return true;
	}

}
