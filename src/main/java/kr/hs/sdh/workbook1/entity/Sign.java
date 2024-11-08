package kr.hs.sdh.workbook1.entity;

import java.beans.ConstructorProperties;

public final class Sign {

    private final String userId;
    private final String userPassword;


    @ConstructorProperties(value = {
            "userId",
            "userPassword"
    })

    public Sign(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
