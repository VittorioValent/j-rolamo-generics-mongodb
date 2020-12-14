package it.jrolamo.generics.mongodb.service;

import it.jrolamo.generics.mongodb.annotations.IsOwnerPreAuth;
import it.jrolamo.generics.mongodb.domain.AbstractDTO;
import it.jrolamo.generics.mongodb.domain.AbstractModel;

/**
 * Read all, Write only owner
 *
 * @author Vittorio Valent
 * @param <Entity>
 * @param <DTO>
 * @since 0.0.1
 */
public abstract class ProtectedService<Entity extends AbstractModel, DTO extends AbstractDTO> extends PublicService<Entity, DTO> {

    /**
     *
     * @param id
     */
    @Override
    public void delete(String id) {
        delete(read(id));
    }

    @IsOwnerPreAuth
    public void delete(DTO dto) {
        repository.delete(mapper.toEntity(dto));
    }

    /**
     *
     * @param dto
     * @return
     */
    @Override
    @IsOwnerPreAuth
    public DTO update(DTO dto) {
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    /**
     *
     * @param id
     * @param dto
     * @return
     */
    @Override
    @IsOwnerPreAuth
    public DTO merge(String id, DTO dto) {
        dto = (DTO) patchUtils.applyPatch(read(id), dto);
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }
}
