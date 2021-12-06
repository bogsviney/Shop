package com.nazarov.shop.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@ToString
@Getter
@Setter
@Builder

public class Product {

private int id;
private double price;
private String name;
private String description;
private LocalDateTime publishDate;


}
