package com.example.todoapp.infra.itemrepository;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface JpaItemRepositoryInterface extends CrudRepository<ItemJpaEntity, String> {
    List<ItemJpaEntity> getByOwner(String ownerId);
    void deleteAllByOwner(String ownerId);
    void deleteAllByIdIn(Collection<String> ids);
}
