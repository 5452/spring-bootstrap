/**
 * 
 */
package la.ipo.quartz.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 2014年2月21日 上午11:37:45
 * @author WANGJUN
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Scope("prototype")
public @interface QuartzJob {
	String name();
	String displayName();
	String group() default "DEFAULT GROUP";
	String cronExp();
	String description();
}
