package it.jrolamo.generics.mongodb.controller;

import com.querydsl.core.types.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.jrolamo.generics.mongodb.domain.AbstractDTO;
import it.jrolamo.generics.mongodb.domain.GroupCount;
import it.jrolamo.generics.mongodb.service.ICrudService;

/**
 * This Controller handles all read-only requests from client.
 *
 * @author Vittorio Valent
 * @param <DTO>
 *
 *
 * @see CrudController
 *
 * @since 0.0.1
 */
public abstract class PrivateReadController<DTO extends AbstractDTO> {

    /**
     *
     */
    @Autowired
    protected ICrudService<DTO> service;

    /**
     *
     * @param predicate
     * @param pageSize
     * @param pageNumber
     * @param direction
     * @param sortField
     * @return
     */
    @CrossOrigin
    @GetMapping("/all")
    public abstract Page<DTO> getAll(Predicate predicate, Integer pageSize, Integer pageNumber, Direction direction,
            String sortField);

    @CrossOrigin
    @GetMapping("/all/group")
    public abstract Page<GroupCount> getAllGroupBy(Predicate predicate, Integer pageSize, Integer pageNumber,
            Direction direction, String sortField, String groupField);

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/read/{id}")
    public DTO read(@PathVariable("id") String id) {
        return service.read(id);
    }

    @GetMapping("/count")
    public abstract Long count(Predicate predicate);
}
