package itis.socialtest.entities;

public class Author {

    private Long id;
    private String nickname;
    private String birthdayDate;

    public Author(Long id, String nickname, String birthdayDate) {
        this.id = id;
        this.nickname = nickname;
        this.birthdayDate = birthdayDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

}
