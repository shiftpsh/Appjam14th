package kr.laptop.school.petitions.datas;

/**
 * Created by devkg on 2017-12-16.
 */

public class User {
    private String uid;         // 사용자 uid
    private String email;       // 사용자 이메일
    private String name;        // 사용자 이름
    private Long joinedDate;      // 가입일

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Long joinedDate) {
        this.joinedDate = joinedDate;
    }
}