package it.jrolamo.generics.mongodb.service;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.jrolamo.generics.mongodb.controller.PrivateCrudController;
import it.jrolamo.generics.mongodb.domain.AbstractDTO;
import it.jrolamo.generics.mongodb.domain.GroupCount;

/**
 * All CRUD methods service
 *
 * @author Vittorio Valent
 * @param <DTO>
 *
 * @see PrivateCrudController
 *
 * @since 0.0.1
 */
@Service
public interface ICrudService<DTO extends AbstractDTO> {

    /**
     *
     * @param entity
     * @return
     */
    public DTO create(DTO entity);

    /**
     *
     * @param id
     */
    public void delete(String id);

    /**
     * 
     * @param dto
     */
    public void delete(DTO dto);

    /**
     *
     * @param entity
     * @return
     */
    public DTO update(DTO entity);

    /**
     *
     * @param id
     * @param entity
     * @return
     */
    public DTO merge(String id, DTO entity);

    /**
     *
     * @param predicate
     * @param pageable
     * @return
     */
    public Page<DTO> getAll(Predicate predicate, Pageable pageable);

    /**
     * 
     * @param predicate
     * @param pageable
     * @param groupField
     * @param collectionName
     * @return
     */
    public Page<GroupCount> getAllGroupBy(Predicate predicate, Pageable pageable, String groupField,
            String collectionName);

    /**
     *
     * @param id
     * @return
     */
    public DTO read(String id);

    public Long count(Predicate predicate);
}
