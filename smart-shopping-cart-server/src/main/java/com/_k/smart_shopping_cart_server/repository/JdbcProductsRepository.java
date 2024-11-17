package com._k.smart_shopping_cart_server.repository;

import com._k.smart_shopping_cart_server.domain.Products;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class JdbcProductsRepository implements ProductsRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcProductsRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int saveProduct(Products product) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("PRODUCTS").usingGeneratedKeyColumns("PRODUCT_ID");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("BARCODE", product.getBarcode());
        parameters.put("NAME", product.getName());
        parameters.put("PRICE", product.getPrice());
        parameters.put("QUANTITY", product.getQuantity());
        parameters.put("location", product.getLocation());

        return simpleJdbcInsert.executeAndReturnKey(parameters).intValue();
    }

    @Override
    public Optional<Products> readProductById(int productId) {
        List<Products> rs = jdbcTemplate.query("SELECT * FROM PRODUCTS WHERE PRODUCT_ID = ?", productRowMapper(), productId);
        return rs.stream().findFirst();
    }

    @Override
    public Optional<Products> readProductByBarcode(String barcode) {
        List<Products> rs = jdbcTemplate.query("SELECT * FROM PRODUCTS WHERE BARCODE = ?", productRowMapper(), barcode);
        return rs.stream().findFirst();
    }

    @Override
    public int updateProductQuantity(Products product) {
        jdbcTemplate.update("update PRODUCTS set QUANTITY = ? WHERE PRODUCT_ID =?", product.getQuantity(), product.getProductId());
        return product.getProductId();
    }

    @Override
    public List<Products> readAllProduct() {
        return jdbcTemplate.query("select * from PRODUCTS", productRowMapper());
    }

    @Override
    public void deleteAllProduct() {
        jdbcTemplate.update("DELETE FROM PRODUCTS");
    }


    private RowMapper<Products> productRowMapper() {
        return (rs, rowNum) -> new Products(
                    rs.getInt("PRODUCT_ID"),
                    rs.getString("BARCODE"),
                    rs.getString("NAME"),
                    rs.getInt("PRICE"),
                    rs.getInt("QUANTITY"),
                    rs.getString("LOCATION")
        );
    }
}
