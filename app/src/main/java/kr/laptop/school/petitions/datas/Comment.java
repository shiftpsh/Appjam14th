package kr.laptop.school.petitions.datas;

/**
 * Created by devkg on 2017-12-16.
 */

public class Comment {
    public String uuid;         // 코멘트 uuid
    public String articleId;    // 작성된 글 번호
    public String author;       // 코멘트 작성자 uid
    public String content;      // 코멘트 내용
    public String type;         // 코멘트 타입

    public Long enrolledDate;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getEnrolledDate() {
        return enrolledDate;
    }

    public void setEnrolledDate(Long enrolledDate) {
        this.enrolledDate = enrolledDate;
    }
}
