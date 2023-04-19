package tacos.data;

import tacos.Order;
import tacos.User;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long>{

    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
	
}
