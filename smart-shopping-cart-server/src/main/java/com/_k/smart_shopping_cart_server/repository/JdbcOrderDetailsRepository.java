package com._k.smart_shopping_cart_server.repository;

import com._k.smart_shopping_cart_server.domain.OrderDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcOrderDetailsRepository implements OrderDetailsRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcOrderDetailsRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public int saveOrderDetail(OrderDetails orderDetails) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("ORDERDETAILS").usingGeneratedKeyColumns("ID");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ORDER_ID", orderDetails.getOrderId());
        parameters.put("PRODUCT_ID", orderDetails.getProductId());
        parameters.put("QUANTITY", orderDetails.getQuantity());

        return simpleJdbcInsert.executeAndReturnKey(parameters).intValue();
    }

    @Override
    public Optional<OrderDetails> readOrderDetailByOrderDetailId(int id) {
        List<OrderDetails> result = jdbcTemplate.query("SELECT * FROM ORDERDETAILS WHERE ID = ?", orderDetailsRowMapper(), id);
        return result.stream().findFirst();
    }

    @Override
    public List<OrderDetails> readOrderDetailListByOrderId(int orderId) {
        return jdbcTemplate.query("SELECT * FROM ORDERDETAILS WHERE ORDER_ID = ?", orderDetailsRowMapper(), orderId);
    }

    @Override
    public List<OrderDetails> readAllOrderDetail() {
        return jdbcTemplate.query("SELECT * FROM ORDERDETAILS", orderDetailsRowMapper());
    }

    @Override
    public void deleteAllOrderDetail() {
        jdbcTemplate.update("DELETE FROM ORDERDETAILS");
    }


    private RowMapper<OrderDetails> orderDetailsRowMapper() {
        return (rs, rowNum) -> new OrderDetails(
                rs.getInt("ID"),
                rs.getInt("ORDER_ID"),
                rs.getInt("PRODUCT_ID"),
                rs.getInt("QUANTITY")
        );
    }
}
