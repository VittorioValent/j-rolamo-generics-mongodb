package it.jrolamo.generics.domain;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Provides Auditing fields and method-level security access for DTOs
 * 
 * @author Vittorio Valent
 *
 * @see AuditModel
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AuditDTO extends AbstractDTO {

    /**
     * Indicates when the object was created first
     */
    protected Date createdOn;

    /**
     * Indicates the creator/owner of the given object
     */
    protected String owner;

    /**
     * Indicates when the object was last
     */
    protected Date lastModifiedOn;

    /**
     * Indicates last user updating the object
     */
    protected String lastModifiedBy;
}
