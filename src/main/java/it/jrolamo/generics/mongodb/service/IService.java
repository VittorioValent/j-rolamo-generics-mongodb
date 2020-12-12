package it.jrolamo.generics.mongodb.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import it.jrolamo.generics.mongodb.domain.AbstractDTO;
import it.jrolamo.generics.mongodb.domain.AbstractModel;

/**
 * All CRUD methods and buisness helpers can be found in this interface.
 *
 * @author Vittorio Valent
 * @param <Entity>
 * @param <DTO>
 *
 * @see ICrudService
 * @since 0.0.1
 */
@Service
public interface IService<Entity extends AbstractModel, DTO extends AbstractDTO> extends ICrudService<DTO> {

    /**
     * Buisness-only method: supports filtering by specification instead of QDSDL
     * Predicate
     *
     * @param spec
     * @return List<DTO>
     */
    public List<DTO> find(Example<Entity> spec);
}
