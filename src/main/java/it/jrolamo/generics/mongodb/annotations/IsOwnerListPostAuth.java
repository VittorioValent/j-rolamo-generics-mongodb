package it.jrolamo.generics.mongodb.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
// @Query("select entity from #{#entityName} entity where entity.owner =
// ?#{principal.username} or 1=?#{hasRole('ROLE_ADMIN') ? 1 : 0}")
public @interface IsOwnerListPostAuth {

}
