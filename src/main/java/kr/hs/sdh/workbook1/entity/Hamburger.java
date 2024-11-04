package kr.hs.sdh.workbook1.entity;

import java.beans.ConstructorProperties;

@SuppressWarnings(value = "all")
public final class Hamburger {

    // 햄버거 이름
    private final String name;

    // 햄버거 가격
    private final int price;

    // 신제품 여부
    private final boolean isNew;

    // 추천 여부
    private final boolean isRecommended;

    // 햄버거 이미지 경로
    private String imagePath;

    @ConstructorProperties(value = {
        "name",
        "price",
        "imagePath",
        "isNew",
        "isRecommended"
    })
    // "Hamburger" 클래스의 생성자
    public Hamburger(
        final String name,
        final int price,
        final String imagePath,
        final boolean isNew,
        final boolean isRecommended
    ) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
        this.isNew = isNew;
        this.isRecommended = isRecommended;
    }

    // 햄버거 이름을 반환하는 메소드
    public String getName() {
        return this.name;
    }

    // 햄버거 가격을 반환하는 메소드
    public int getPrice() {
        return this.price;
    }
    
    // 햄버거 이미지 경로를 반환하는 메소드
    public String getImagePath() {
        return this.imagePath;
    }

    // 신제품 여부를 반환하는 메소드
    public boolean isNew() {
        return this.isNew;
    }
    
    // 추천 여부를 반환하는 메소드
    public boolean isRecommended() {
        return this.isRecommended;
    }

    public void setImagePath(final String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Hamburger hamburger) {
            return this.name
                .contentEquals(hamburger.name);
        }

        return false;
    }

}