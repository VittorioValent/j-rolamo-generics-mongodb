package it.jrolamo.generics.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PostAuthorize;

/**
 * Method-level security annotation: returns 403 error if the owner of the
 * returned entity is not the logged user nor an admin.
 * 
 * @author Vittorio Valent
 * @since 0.0.1
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PostAuthorize("(returnObject.owner == authentication.principal.username) or (hasRole('ROLE_ADMIN'))")
public @interface IsOwnerPostAuth {

}
