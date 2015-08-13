package org.home.axon.repositories;


import org.axonframework.domain.AggregateRoot;
import org.axonframework.eventsourcing.AggregateDeletedException;
import org.axonframework.repository.AbstractRepository;
import org.axonframework.repository.AggregateNotFoundException;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryRepository<T extends AggregateRoot<String>> extends AbstractRepository<T> {

    private final ConcurrentMap<String, T> cache = new ConcurrentHashMap<>();

    /**
     * Initializes a repository that stores aggregate of the given <code>aggregateType</code>. All aggregates in this
     * repository must be <code>instanceOf</code> this aggregate type.
     *
     * @param aggregateType The type of aggregate stored in this repository
     */
    public InMemoryRepository(Class<T> aggregateType) {
        super(aggregateType);
    }

    /**
     * Performs the actual saving of the aggregate.
     *
     * @param aggregate the aggregate to store
     */
    @Override
    protected void doSave(T aggregate) {
        cache.put(aggregate.getIdentifier(), aggregate);
    }

    /**
     * Loads and initialized the aggregate with the given aggregateIdentifier.
     *
     * @param aggregateIdentifier the identifier of the aggregate to load
     * @param expectedVersion     The expected version of the aggregate to load
     * @return a fully initialized aggregate
     * @throws AggregateNotFoundException if the aggregate with given identifier does not exist
     */
    @Override
    protected T doLoad(Object aggregateIdentifier, Long expectedVersion) {
        T aggregate = cache.get(aggregateIdentifier);
        if (aggregate == null) throw new IllegalStateException("No object with id " + aggregateIdentifier);
        else if (aggregate.isDeleted()) throw new AggregateDeletedException(aggregateIdentifier);
        else return aggregate;
    }

    /**
     * Removes the aggregate from the repository. Typically, the repository should ensure that any calls to {@link
     * #doLoad(Object, Long)} throw a {@link AggregateNotFoundException} when
     * loading a deleted aggregate.
     *
     * @param aggregate the aggregate to delete
     */
    @Override
    protected void doDelete(T aggregate) {

    }
}
