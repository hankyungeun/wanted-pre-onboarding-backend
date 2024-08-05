package wanted.preonboarding.backend.searcher;

public enum SearchOperationType {

    CONTAINS,
    CONTAINS_IGNORE_CASE,
    START_WITH,
    START_WITH_IGNORE_CASE,
    END_WITH,
    END_WITH_IGNORE_CASE,
    NULL,
    NOT_NULL,
    EQUAL,
    NOT_EQUAL,
    LESS_THAN,
    LESS_THAN_OR_EQUAL,
    GREATER_THAN,
    GREATER_THAN_OR_EQUAL,
    IN,
    NOT_IN,
    BETWEEN
}
