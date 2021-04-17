package it.jrolamo.generics.mongodb.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.data.mongodb.repository.Query;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Query(value = "{owner: ?#{ hasRole('ADMIN') ? {$exists:true} : principal.username}}", count = true)
public @interface IsOwnerCountPostAuth {

}
