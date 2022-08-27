package com.neeejm.inventory.entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockProductId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID stockId;

    private UUID productId;
}
