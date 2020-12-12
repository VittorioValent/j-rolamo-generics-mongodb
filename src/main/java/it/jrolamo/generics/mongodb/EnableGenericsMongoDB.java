package it.jrolamo.generics.mongodb;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 *
 * @author Vittorio Valent
 *
 * @since 0.0.2
 */
@Import(value = JRolamoGenericsMongoDB.class)
@Target(value = { ElementType.TYPE })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface EnableGenericsMongoDB {

}
