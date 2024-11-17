package com._k.smart_shopping_cart_server.repository;

import com._k.smart_shopping_cart_server.domain.Orders;
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
public class JdbcOrdersRepository implements OrdersRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcOrdersRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int saveOrder() {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("ORDERS").usingGeneratedKeyColumns("ORDER_ID");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("TIMESTAMP", null);
        return simpleJdbcInsert.withTableName("ORDERS").executeAndReturnKey(parameters).intValue();
    }

    @Override
    public Optional<Orders> readOrderByOrderId(int orderId) {
        List<Orders> rs = jdbcTemplate.query("SELECT * FROM ORDERS WHERE ORDER_ID = ?", ordersRowMapper(), orderId);
        return rs.stream().findFirst();
    }

    @Override
    public List<Orders> readAllOrder() {
        return jdbcTemplate.query("SELECT * FROM ORDERS", ordersRowMapper());
    }

    @Override
    public int updateOrder(Orders order) {
        jdbcTemplate.update("UPDATE ORDERS SET ORDER_DATE_TIME = ? WHERE ORDER_ID = ?", order.getOrderDateTime(), order.getOrderId());
        return order.getOrderId();
    }

    @Override
    public void deleteAllOrder() {
        jdbcTemplate.update("DELETE FROM ORDERS");
    }

    private RowMapper<Orders> ordersRowMapper() {
        return (rs, num) -> new Orders(rs.getInt("ORDER_ID"), rs.getString("ORDER_DATE_TIME"));
    }
}
