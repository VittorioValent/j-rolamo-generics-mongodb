package it.jrolamo.generics.mapper;

import it.jrolamo.generics.domain.AbstractDTO;
import it.jrolamo.generics.domain.AbstractModel;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

/**
 * Interface for all mapper methods implemented by Mapstruct
 *
 * @author Vittorio Valent
 * @param <Entity>
 * @param <DTO>
 *
 * @since 0.0.1
 */
@Component
public interface IMapper<Entity extends AbstractModel, DTO extends AbstractDTO> extends ListMapper {

    /**
     *
     * @param dto
     * @return
     */
    public Entity toEntity(DTO dto);

    /**
     *
     * @param entity
     * @return
     */
    public DTO toDTO(Entity entity);

    /**
     *
     * @param iterEntity
     * @return
     */
    public List<DTO> toDTO(Iterable<Entity> iterEntity);

    /**
     *
     * @param iterDTO
     * @return
     */
    public List<Entity> toEntity(Iterable<DTO> iterDTO);

    /**
     *
     * @param dtoPage
     * @return
     */
    default Page<Entity> toEntity(Page<DTO> dtoPage) {
        return new PageImpl<>(toEntity(dtoPage.getContent()), dtoPage.getPageable(), dtoPage.getTotalElements());
    }

    /**
     *
     * @param entityPage
     * @return
     */
    default Page<DTO> toDTO(Page<Entity> entityPage) {
        return new PageImpl<>(toDTO(entityPage.getContent()), entityPage.getPageable(), entityPage.getTotalElements());
    }
}
