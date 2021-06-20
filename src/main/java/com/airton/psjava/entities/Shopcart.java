package com.airton.psjava.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_shopcart")
public class Shopcart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime moment;

    @OneToMany(mappedBy = "id.shopcart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShopcartProduct> products = new ArrayList<>();

    public Shopcart() {

    }

    public Shopcart(Long id, LocalDateTime moment) {
        this.id = id;
        this.moment = moment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getMoment() {
        return moment;
    }

    public void setMoment(LocalDateTime moment) {
        this.moment = moment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shopcart)) return false;

        Shopcart shopcart = (Shopcart) o;

        return getId() != null ? getId().equals(shopcart.getId()) : shopcart.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
