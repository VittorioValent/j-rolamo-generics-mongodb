package it.jrolamo.generics.mongodb.service;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.jrolamo.generics.mongodb.annotations.IsOwnerPostAuth;
import it.jrolamo.generics.mongodb.annotations.IsOwnerPreAuth;
import it.jrolamo.generics.mongodb.domain.AuditDTO;
import it.jrolamo.generics.mongodb.domain.AuditModel;

/**
 * generic implementation for those CRUD methods which need some sort of
 * authentication to be invoked.It should be extended by services related to
 * private entities.
 *
 * @author Vittorio Valent
 * @param <Entity>
 * @param <DTO>
 *
 * @see IsOwnerPostAuth
 * @see IsOwnerPreAuth
 * @see AuditDTO
 * @since 0.0.1
 */
public abstract class PrivateService<Entity extends AuditModel, DTO extends AuditDTO>
        extends ProtectedService<Entity, DTO> {

    /**
     *
     * @param id
     * @return
     */
    @Override
    @IsOwnerPostAuth
    public DTO read(String id) {
        return mapper.toDTO(repository.findById(id).get());
    }

    /**
     *
     * @param predicate
     * @param pageable
     * @return
     */
    @Override
    public Page<DTO> getAll(Predicate predicate, Pageable pageable) {
        if (predicate == null) {
            return mapper.toDTO(repository.findAll(pageable));
        } else {
            return mapper.toDTO(repository.findAll(predicate, pageable));
        }
    }
}
