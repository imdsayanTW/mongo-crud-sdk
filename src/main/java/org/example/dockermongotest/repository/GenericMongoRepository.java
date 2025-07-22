package org.example.dockermongotest.repository;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Generic repository for MongoDB entities.
 */
public class GenericMongoRepository<T> {

    private final MongoTemplate mongoTemplate;
    private final Class<T> entityClass;

    public GenericMongoRepository(MongoTemplate mongoTemplate, Class<T> entityClass) {
        this.mongoTemplate = mongoTemplate;
        this.entityClass = entityClass;
    }

    public void save(T entity) {
        mongoTemplate.save(entity);
    }

    public T findById(String id, String idField) {
        Query query = new Query();
        query.addCriteria(Criteria.where(idField).is(id));
        return mongoTemplate.findOne(query, entityClass);
    }

    public void deleteById(String id, String idField) {
        Query query = new Query();
        query.addCriteria(Criteria.where(idField).is(id));
        mongoTemplate.remove(query, entityClass);
    }

    public List<T> findAll() {
        return mongoTemplate.findAll(entityClass);
    }

    public void deleteAll() {
        mongoTemplate.dropCollection(entityClass);
    }
}