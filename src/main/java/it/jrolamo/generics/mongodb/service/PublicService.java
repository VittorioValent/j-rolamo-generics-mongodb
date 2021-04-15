package it.jrolamo.generics.mongodb.service;

import com.querydsl.core.types.Predicate;
import it.jrolamo.generics.mongodb.domain.AbstractDTO;
import it.jrolamo.generics.mongodb.domain.AbstractModel;
import it.jrolamo.generics.mongodb.domain.GroupCount;
import it.jrolamo.generics.mongodb.mapper.IMapper;
import it.jrolamo.generics.mongodb.repository.IRepository;
import it.jrolamo.generics.mongodb.utils.PatchUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

/**
 * This class implements all CRUD and common buisness methods from
 * {@link IService} except those which need authentication to be invoked.
 *
 * @author Vittorio Valent
 * @param <Entity>
 * @param <DTO>
 *
 * @see PrivateService
 * @since 0.0.1
 */
public abstract class PublicService<Entity extends AbstractModel, DTO extends AbstractDTO>
        implements IService<Entity, DTO> {

    /**
     *
     */
    @Autowired
    protected IMapper<Entity, DTO> mapper;

    /**
     *
     */
    @Autowired
    protected IRepository<Entity> repository;

    @Autowired
    PatchUtils<DTO> patchUtils;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     *
     * @param dto
     * @return
     */
    @Override
    public DTO create(DTO dto) {
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public List<DTO> find(Example<Entity> spec) {
        return mapper.toDTO(repository.findAll(spec));
    }

    /**
     *
     * @param id
     */
    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(DTO dto) {
        repository.delete(mapper.toEntity(dto));
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public DTO read(String id) {
        // return mapper.toDTO(repository.findById(id).orElse(null)); // ZLATAN SAYS =>
        // NOT WORK!
        Optional<Entity> item = repository.findById(id);
        return item.isPresent() ? mapper.toDTO(item.get()) : null;
    }

    /**
     * @param predicate
     * @return count of filtered objects
     */
    @Override
    public Long count(Predicate predicate) {
        return repository.count(predicate);
    }

    /**
     *
     * @param dto
     * @return
     */
    @Override
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
    public DTO merge(String id, DTO dto) {
        dto = patchUtils.applyPatch(read(id), dto);
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    /**
     *
     * @param predicate
     * @param pageable
     * @return
     */
    @Override
    public Page<DTO> getAll(Predicate predicate, Pageable pageable) {
        if (predicate == null) {
            return mapper.toDTO(repository.findAll(pageable));
        } else {
            return mapper.toDTO(repository.findAll(predicate, pageable));
        }
    }

    @Override
    public Page<GroupCount> getAllGroupBy(Predicate predicate, Pageable pageable, String groupField,
            String collectionName) {

        List<AggregationOperation> aggregationsCount = extractPredicate(predicate, collectionName);
        aggregationsCount.add(Aggregation.group(groupField).count().as("count"));

        final Aggregation aggregationCount = Aggregation.newAggregation(aggregationsCount);

        Long numberElement = Long.valueOf(
                mongoTemplate.aggregate(aggregationCount, collectionName, GroupCount.class).getMappedResults().size());

        List<AggregationOperation> aggregations = extractPredicate(predicate, collectionName);
        aggregations.add(Aggregation.group(groupField).count().as("count"));
        aggregations.add(Aggregation.skip(Long.valueOf(pageable.getPageNumber() * pageable.getPageSize())));
        aggregations.add(Aggregation.limit(pageable.getPageSize()));

        final Aggregation aggregation = Aggregation.newAggregation(aggregations);

        AggregationResults<GroupCount> result = mongoTemplate.aggregate(aggregation, collectionName, GroupCount.class);

        return new PageImpl<GroupCount>(result.getMappedResults(), pageable, numberElement);
    }

    /**
     * TAPULLO TODO se funzionasse sarebbe meglio
     * Aggregation.match(Criteria.byExample(t))
     *
     * @param predicate
     * @return
     */
    private List<AggregationOperation> extractPredicate(Predicate predicate, String collectionName) {
        System.out.println("PREDICATE " + predicate.toString());
        List<AggregationOperation> list = new ArrayList<>();
        String stringa = predicate.toString().replace(collectionName + ".", "");
        stringa = stringa.replace(",", "=");
        stringa = stringa.replace(")", "");
        stringa = stringa.replace("containsIc(", "");
        String[] listaFiltri = stringa.split("&&");
        for (String filtro : listaFiltri) {
            String[] filter = filtro.trim().split("=");
            if (filter.length == 2) {
                list.add(Aggregation.match(Criteria.where(filter[0].trim()).regex(filter[1].trim())));
            }
        }
        return list;
    }
}
