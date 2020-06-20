package com.example.todoapp.infra.itemrepository;

import com.example.todoapp.core.Item;
import com.example.todoapp.core.ItemRepository;
import com.example.todoapp.core.User;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JpaItemRepository implements ItemRepository {

    private final JpaItemRepositoryInterface jpaRepository;

    public JpaItemRepository(JpaItemRepositoryInterface jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Item save(Item item) {
        ItemJpaEntity jpaEntity = ItemJpaEntity.createFrom(item);
        Item savedItem = jpaRepository.save(jpaEntity).toItem();
        return savedItem;
    }

    @Override
    public void deleteAll() {
        jpaRepository.deleteAll();
    }

    @Override
    public List<Item> getItemsForUser(String userId) {
        return jpaRepository.getByOwner(userId).stream()
                .map(ItemJpaEntity::toItem)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllForUser(User user) {
        jpaRepository.deleteAllByOwner(user.getId());
    }

    @Transactional
    @Override
    public void deleteByIds(Collection<String> itemIds) {
        jpaRepository.deleteAllByIdIn(itemIds);
    }

    @Override
    public void deleteById(String itemId) {
        jpaRepository.deleteById(itemId);
    }

    @Override
    public boolean exists(String itemId) {
        return jpaRepository.existsById(itemId);
    }

    @Override
    public Optional<Item> getById(String itemId) {
        return jpaRepository.findById(itemId)
                .map(ItemJpaEntity::toItem);
    }

    @Override
    public Item upsert(Item updatedItem) {
        ItemJpaEntity jpaEntity = ItemJpaEntity.createFrom(updatedItem);
        return jpaRepository.save(jpaEntity).toItem();
    }
}
