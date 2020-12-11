package it.jrolamo.generics.utils;

import java.lang.reflect.Field;

import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import it.jrolamo.generics.domain.AbstractDTO;

/**
 * Component for enable partial update DTO
 *
 * @author Vittorio Valent
 * @param <DTO>
 * @since 0.0.1
 */
@Component
public class PatchUtils<DTO extends AbstractDTO> {

    /**
     * Use reflection for update source DTO with data not null from patched DTO
     *
     * @param dtoSource
     * @param dtoPatch
     * @return updated DTO
     */
    public DTO applyPatch(DTO dtoSource, DTO dtoPatch) {
        Class<? extends Object> clazz = dtoSource.getClass();

        for (Field field : clazz.getDeclaredFields()) {

            // use reflection to get field k on object and set it to value v
            field.setAccessible(true);

            // get value
            Object value = ReflectionUtils.getField(field, dtoPatch);
            if (value != null) {
                // set value if not null
                ReflectionUtils.setField(field, dtoSource, value);
            }
        }
        return dtoSource;
    }

}
