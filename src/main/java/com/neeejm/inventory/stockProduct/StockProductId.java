package com.neeejm.inventory.stockProduct;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockProductId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "stock_id")
    private UUID stockId;

    @Column(name = "product_id")
    private UUID productId;
}
