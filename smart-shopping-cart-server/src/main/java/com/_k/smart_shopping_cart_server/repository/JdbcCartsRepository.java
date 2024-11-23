package com._k.smart_shopping_cart_server.repository;

import com._k.smart_shopping_cart_server.domain.Carts;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class JdbcCartsRepository implements CartsRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcCartsRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public String saveCart(Carts cart) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("CARTS");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ID", cart.getId());
        simpleJdbcInsert.execute(parameters);

        return cart.getId();
    }

    @Override
    public Optional<Carts> readCartByCartId(String id) {
        List<Carts> result = jdbcTemplate.query("SELECT * FROM CARTS WHERE ID = ?", cartRowMapper(), id);
        return result.stream().findFirst();
    }

    @Override
    public List<Carts> readAllCart() {
        return jdbcTemplate.query("SELECT * FROM CARTS", cartRowMapper());
    }

    @Override
    public String updateOrderId(Carts cart) {
        jdbcTemplate.update(
                "UPDATE CARTS SET ORDER_ID = ? WHERE ID = ?",
                cart.getOrderId(), cart.getId()
        );
        return cart.getId();
    }

    @Override
    public void deleteAllCart() {
        jdbcTemplate.update("DELETE FROM CARTS");
    }

    private RowMapper<Carts> cartRowMapper() {
        return (rs, rowNum) -> new Carts(rs.getString("ID"), rs.getInt("ORDER_ID"));
    }
}
