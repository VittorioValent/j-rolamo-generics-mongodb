package it.jrolamo.generics.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * 
 * Method-level security annotation: returns 403 error if the owner of the
 * parameter entity is not the logged user nor an admin.
 * 
 * @author Vittorio Valent
 * @since 0.0.1
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("(#dto.owner == authentication.principal.username) or (hasRole('ROLE_ADMIN'))")
public @interface IsOwnerPreAuth {

}
