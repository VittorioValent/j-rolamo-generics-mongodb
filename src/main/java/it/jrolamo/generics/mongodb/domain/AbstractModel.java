package it.jrolamo.generics.mongodb.domain;

import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Persistable;

/**
 *
 * @author Vittorio Valent
 * @since 0.0.1
 */
public abstract class AbstractModel implements Serializable, Persistable<String> {

    @Override
    public boolean isNew() {
        return StringUtils.isBlank(getId());
    }
}
