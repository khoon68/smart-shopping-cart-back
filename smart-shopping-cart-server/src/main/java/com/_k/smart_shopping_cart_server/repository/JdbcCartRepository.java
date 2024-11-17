package com._k.smart_shopping_cart_server.repository;

import com._k.smart_shopping_cart_server.domain.Cart;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class JdbcCartRepository implements CartRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcCartRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public String saveCart(Cart cart) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("CARTS");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("CART_ID", cart.getCartId());
        simpleJdbcInsert.execute(parameters);

        return cart.getCartId();
    }

    @Override
    public Optional<Cart> readCartByCartId(String cartId) {
        List<Cart> result = jdbcTemplate.query("SELECT * FROM CARTS WHERE CART_ID = ?", cartRowMapper(), cartId);
        return result.stream().findFirst();
    }

    @Override
    public List<Cart> readAllCart() {
        return jdbcTemplate.query("SELECT * FROM CARTS", cartRowMapper());
    }

    @Override
    public String updateOrderId(Cart cart) {
        jdbcTemplate.update(
                "UPDATE CARTS SET ORDER_ID = ? WHERE CART_ID = ?",
                cart.getOrderId(), cart.getCartId()
        );
        return cart.getCartId();
    }

    @Override
    public void deleteAllCart() {
        jdbcTemplate.update("DELETE FROM CARTS");
    }

    private RowMapper<Cart> cartRowMapper() {
        return (rs, rowNum) -> new Cart(rs.getString("CART_ID"), rs.getInt("ORDER_ID"));
    }
}
