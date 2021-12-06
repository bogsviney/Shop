package com.nazarov.shop.dao.jdbc.mapper;

import com.nazarov.shop.entity.Product;
import org.junit.jupiter.api.Test;


import java.sql.*;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductRowMapperTest {
    @Test
    public void tetstMapRow() throws SQLException {
        ProductRowMapper productRowMapper = new ProductRowMapper();
        LocalDateTime localDateTime = LocalDateTime.of(2021, 12, 6, 14, 57);
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.getString("name")).thenReturn("NAME");
        when(resultSetMock.getString("description")).thenReturn("DESCRIPTION");
        when(resultSetMock.getDouble("price")).thenReturn(777.0);
        when(resultSetMock.getInt("id")).thenReturn(1);
        when(resultSetMock.getTimestamp("date")).thenReturn(timestamp);

        Product actual = productRowMapper.mapRow(resultSetMock);

        assertEquals(1,actual.getId());
        assertEquals(777,actual.getPrice());
        assertEquals("NAME",actual.getName());
        assertEquals("DESCRIPTION",actual.getDescription());
        assertEquals(localDateTime, actual.getPublishDate());
    }

}