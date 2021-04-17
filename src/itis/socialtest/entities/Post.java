package itis.socialtest.entities;

public class Post {

    private String date;
    private String content;
    private Long likesCount;
    private Author author;

    public Post(String date, String content, Long likesCount, Author author) {
        this.date = date;
        this.content = content;
        this.likesCount = likesCount;
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Long likesCount) {
        this.likesCount = likesCount;
    }

    public Author getAuthor() {

        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
