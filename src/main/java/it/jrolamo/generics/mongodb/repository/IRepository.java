package it.jrolamo.generics.mongodb.repository;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.NoRepositoryBean;

import it.jrolamo.generics.mongodb.domain.AbstractModel;

/**
 * This Repository groups other repositores we need:
 * <ul>
 * <li>{@link JpaRepository} for all Crud and Pageable methods,
 * <li>{@link QuerydslBinderCustomizer} for QueryDsl bindings,
 * <li>{@link QuerydslPredicateExecutor} to use URL predicates,
 * <li>{@link JpaSpecificationExecutor} to apply spec filters,
 * </ul>
 *
 * @author Vittorio Valent
 * @param <Entity>
 *
 * @since 0.0.1
 */
@NoRepositoryBean
public interface IRepository<Entity extends AbstractModel> extends MongoRepository<Entity, Object>,
        QuerydslBinderCustomizer<EntityPath<Entity>>, QuerydslPredicateExecutor<Entity> {

    /**
     *
     * @param bindings
     * @param qEntity
     */
    @Override
    default void customize(QuerydslBindings bindings, EntityPath<Entity> qEntity) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }
}
