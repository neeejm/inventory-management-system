package com.neeejm.inventory.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@AllArgsConstructor
public class OrderProductId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID orderId;
    private UUID productId;
}
