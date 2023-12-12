package com.flowiee.app.entity;

import com.flowiee.app.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "pro_order_cart")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderCart extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "orderCart", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Items> listItems;

    @Override
    public String toString() {
        return "Cart{" +
                "listItems=" +
                '}';
    }
}