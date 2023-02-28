package com.epam.esm.repository;

import com.epam.esm.model.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Gets list of {@link Order} from database by user ID.
     *
     * @param userId ID of user
     * @param pageable object with pagination info(page number, page size)
     * @return list of orders
     */
    List<Order> getAllByUserId(long userId, Pageable pageable);

}
