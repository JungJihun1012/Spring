package kr.hs.sdh.workbook1.entity;

import java.time.LocalDateTime;

public final class History {

    private final String name;

    private final int price;

    private final LocalDateTime saleDateTime;

    public History(String name, int price, LocalDateTime saleDateTime) {
        this.name = name;
        this.price = price;
        this.saleDateTime = saleDateTime;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public LocalDateTime getSaleDateTime() {
        return saleDateTime;
    }

}
