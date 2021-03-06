package it.jrolamo.generics.mongodb.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.data.mongodb.repository.Query;

/**
 * Sets as a quary param the logged user's name in order to filter resultset and
 * keep pagination as well. If the logged user has Admin Role then the filter
 * will not be set on.
 * 
 * @author Vittorio Valent
 * @since 0.0.1
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Query("{owner: ?#{ hasRole('ADMIN') ? {$exists:true} : principal.username}}")
public @interface IsOwnerListPostAuth {

}
