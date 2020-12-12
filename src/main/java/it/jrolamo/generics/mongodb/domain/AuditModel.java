package it.jrolamo.generics.mongodb.domain;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Superclass for entity, provides auditing implementation.
 *
 * @author Vittorio Valent
 * @see AuditDTO
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AuditModel extends AbstractModel {

    /**
     * Indicates when the object was created first
     */
    @CreatedDate
    protected Date createdOn;

    /**
     * Indicates the creator/owner of the given object
     */
    @CreatedBy
    protected String owner;

    /**
     * Indicates when the object was last
     */
    @LastModifiedDate
    protected Date lastModifiedOn;

    /**
     * Indicates last user updating the object
     */
    @LastModifiedBy
    protected String lastModifiedBy;
}
