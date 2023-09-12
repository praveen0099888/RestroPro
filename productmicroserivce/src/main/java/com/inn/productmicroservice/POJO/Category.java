package com.inn.productmicroservice.POJO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@NamedQuery(name = "Category.getAllCategory", query = "select c from Category c where c.id in" +
        " (select p.category from Product p where p.status='true')")


@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "category")
public class Category implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;

//    @OneToMany( mappedBy = "category",cascade = CascadeType.REMOVE,orphanRemoval = true)
//    private List<Product> product;



}