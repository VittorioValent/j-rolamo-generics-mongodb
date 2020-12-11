package it.jrolamo.generics.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import it.jrolamo.generics.domain.AbstractDTO;
import it.jrolamo.generics.service.ICrudService;

/**
 * This controller contains all CRUD methods.Notice that method
 * {@code create(<DTO> dto)} needs a @Valid input.
 *
 * @author Vittorio Valent
 * @param <DTO>
 *
 * @see ReadController
 * @see ICrudService
 * @since 0.0.1
 */
public abstract class CrudController<DTO extends AbstractDTO> extends ReadController<DTO> {

    /**
     *
     * @param dto
     * @return
     */
    @PostMapping("/create")
    public DTO create(@Valid @RequestBody DTO dto) {
        return service.create(dto);
    }

    /**
     *
     * @param id
     */
    @DeleteMapping("/delete/{id}")
    public void delete(@RequestParam("id") Object id) {
        service.delete(id);
    }

    /**
     *
     * @param dto
     * @return
     */
    @PutMapping("/update")
    public DTO update(@RequestBody DTO dto) {
        return service.update(dto);
    }
}
