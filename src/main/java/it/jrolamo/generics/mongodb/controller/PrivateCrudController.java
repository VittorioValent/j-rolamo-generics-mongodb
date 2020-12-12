package it.jrolamo.generics.mongodb.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import it.jrolamo.generics.mongodb.domain.AbstractDTO;
import it.jrolamo.generics.mongodb.service.ICrudService;

/**
 * This controller contains all CRUD methods.Notice that method
 * {@code create(<DTO> dto)} needs a @Valid input.
 *
 * @author Vittorio Valent
 * @param <DTO>
 *
 * @see PrivateReadController
 * @see ICrudService
 * @since 0.0.1
 */
public abstract class PrivateCrudController<DTO extends AbstractDTO> extends PrivateReadController<DTO> {

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
    public void delete(@RequestParam("id") String id) {
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

    /**
     *
     * @param id
     * @param dto
     * @return
     */
    @PatchMapping("/update")
    public DTO merge(@RequestParam String id, @RequestBody DTO dto) {

        return service.merge(id, dto);
    }

}
