package com.example.todoapp.infra.itemrepository;

import com.example.todoapp.core.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemJpaEntity {
    @Id
//    @GeneratedValue(strategy = AUTO)
    private String id;
    private String name;
    private String owner;
    private String state;

    public static ItemJpaEntity createFrom(Item item) {
        return new ItemJpaEntity(
                item.getId(),
                item.getName(),
                item.getOwner(),
                item.getState());
    }

    public Item toItem(){
        return new Item(id,name,owner,state);
    }
}
