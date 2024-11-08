package kr.hs.sdh.workbook1.entity;

public class Login {
    private final String id;
    private final String password;
    private final String name;


    public Login(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    public String getId() { return id; }
    public String getPassword() { return password; }
    public String getName() { return name; }
}
