package it.jrolamo.generics.mongodb.domain;

import java.io.Serializable;

/**
 *
 * @author Vittorio Valent
 * @since 0.0.1
 */
public abstract class AbstractModel implements Serializable {

    /**
     *
     * @return
     */
    public abstract String getId();
}
