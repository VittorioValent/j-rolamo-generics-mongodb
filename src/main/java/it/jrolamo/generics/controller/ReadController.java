package it.jrolamo.generics.controller;

import com.querydsl.core.types.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.jrolamo.generics.domain.AbstractDTO;
import it.jrolamo.generics.service.ICrudService;

/**
 * This Controller handles all read-only requests from client.
 *
 * @author Vittorio Valent
 * @param <DTO>
 *
 * @see CrudController
 * @since 0.0.1
 */
public abstract class ReadController<DTO extends AbstractDTO> {

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
    @GetMapping("/all")
    public abstract Page<DTO> getAll(Predicate predicate, Integer pageSize, Integer pageNumber, Direction direction,
            String sortField);

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/read")
    public DTO read(@RequestParam Object id) {
        return service.read(id);
    }
}
