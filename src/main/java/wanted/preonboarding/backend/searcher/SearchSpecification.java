package wanted.preonboarding.backend.searcher;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class SearchSpecification<T> implements Specification<T> {

    private SearchCriteria criteria;

    public SearchSpecification(final SearchCriteria searchCriteria) {
        super();
        this.criteria = searchCriteria;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        String[] fields = criteria.getDataField().split("\\.");
        Path<?> path = root.get(fields[0]);

        // Join 처리
        Join<?, ?> join = null;
        if (fields.length > 1) {
            for (int i = 0; i < fields.length - 1; i++) {
                join = (join == null) ? root.join(fields[i]) : join.join(fields[i]);
            }
            path = join.get(fields[fields.length - 1]);
        }

        Object arg = criteria.getValues().stream().findFirst().orElse(null);

        switch (criteria.getOperation()) {
            case NULL:
                return builder.isNull(path);
            case NOT_NULL:
                return builder.isNotNull(path);
            case EQUAL:
                return builder.equal(path, arg);
            case NOT_EQUAL:
                return builder.notEqual(path, arg);
            case GREATER_THAN:
                return builder.greaterThan((Path<Comparable>) path, (Comparable) arg);
            case GREATER_THAN_OR_EQUAL:
                return builder.greaterThanOrEqualTo((Path<Comparable>) path, (Comparable) arg);
            case LESS_THAN:
                return builder.lessThan((Path<Comparable>) path, (Comparable) arg);
            case LESS_THAN_OR_EQUAL:
                return builder.lessThanOrEqualTo((Path<Comparable>) path, (Comparable) arg);
            case CONTAINS:
                return builder.like(path.as(String.class), "%" + arg.toString() + "%");
            case CONTAINS_IGNORE_CASE:
                return builder.like(builder.upper(path.as(String.class)), "%" + arg.toString().toUpperCase() + "%");
            case START_WITH:
                return builder.like(path.as(String.class), arg.toString() + "%");
            case START_WITH_IGNORE_CASE:
                return builder.like(builder.upper(path.as(String.class)), arg.toString().toUpperCase() + "%");
            case END_WITH:
                return builder.like(path.as(String.class), "%" + arg.toString());
            case END_WITH_IGNORE_CASE:
                return builder.like(builder.upper(path.as(String.class)), "%" + arg.toString().toUpperCase());
            case IN:
                return path.in(criteria.getValues());
            case NOT_IN:
                return builder.not(path.in(criteria.getValues()));
            case BETWEEN:
                List<Object> values = criteria.getValues();
                return builder.between((Path<Comparable>) path, (Comparable) values.get(0), (Comparable) values.get(1));
            default:
                return null;
        }
    }
}