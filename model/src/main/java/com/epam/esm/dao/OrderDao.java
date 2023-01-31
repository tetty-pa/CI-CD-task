package com.epam.esm.dao;

import com.epam.esm.entity.Order;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    /**
     * Creates new Order.
     *
     * @param entity Order to create
     * @return Order if founded or Empty if not
     */
    Order create(Order entity);
    /**
     * Gets User by id.
     *
     * @param id User id to get
     * @return User
     */
    Optional<Order> getById(long id);
    /**
     * Gets list of {@link Order} from database by user ID.
     *
     * @param userId ID of user
     * @param pageable object with pagination info(page number, page size)
     * @return list of orders
     */
    List<Order> getAllByUserId(long userId, Pageable pageable);

}
