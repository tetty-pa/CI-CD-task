package com.epam.esm.service;

import com.epam.esm.entity.Order;

import java.util.List;


public interface OrderService {
    /**
     * Gets list of {@link Order} from database by user ID.
     *
     * @param userId ID of user
     * @param page   page number for pagination
     * @param size   page size for pagination
     * @return list of orders
     */
    List<Order> getAllByUserId(long userId, int page, int size);
    /**
     * Creates an Order.
     *
     * @param userId user`s ID
     * @param certificateId certificate`s ID
     * @return Entity object
     */
    Order create(long userId, long certificateId);
    /**
     * Gets  {@link Order} from database by  ID.
     *
     * @param orderId ID of user
     * @return order
     */
    Order getById( long orderId);
}
