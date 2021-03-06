package it.jrolamo.generics.mongodb.repository;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import it.jrolamo.generics.mongodb.annotations.IsOwnerCountPostAuth;
import it.jrolamo.generics.mongodb.annotations.IsOwnerListPostAuth;
import it.jrolamo.generics.mongodb.domain.AuditModel;

/**
 * Repository to fetch private objects based on logged user in order to keep
 * pagination
 *
 * @author Vittorio Valent
 * @param <Entity>
 *
 * @see IsOwnerListPostAuth
 * @since 0.0.1
 */
@NoRepositoryBean
public interface IPrivateRepository<Entity extends AuditModel> extends IRepository<Entity> {

    /**
     *
     * @param pageable
     * @return
     */
    @Override
    @IsOwnerListPostAuth
    public Page<Entity> findAll(Pageable pageable);

    /**
     *
     * @param predicate
     * @param pageable
     * @return
     */
    @Override
    @IsOwnerListPostAuth
    public Page<Entity> findAll(Predicate predicate, Pageable pageable);

    @Override
    @IsOwnerCountPostAuth
    public long count(Predicate predicate);
}
