package kr.hs.sdh.workbook1.entity;

public class Role {

    private final String id;
    private final String code;


    public Role(String id, String code) {
        this.id = id;
        this.code = code;
    }

    public String getId() { return id;}
    public String getCode() { return code;}
}
