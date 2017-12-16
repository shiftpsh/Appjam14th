package kr.laptop.school.petitions.datas;

/**
 * Created by devkg on 2017-12-16.
 */

public class Article {
    private String uuid;        // 게시글 uuid
    private String author;      // 게시글 작성자

    private String title;       // 게시물 제목
    private String content;     // 게시물 내용
    private String category;    // 카테고리

    private int commentCount;

    private Long openedDate;    // 게시일
    private Long closedDate;    // 종료일
    private int period;         // 게시기간

    public Article() {

    }

    public Article(String uuid, String author, String title, String content, String category, int commentCount, Long openedDate, Long closedDate, int period) {
        this.uuid = uuid;
        this.author = author;
        this.title = title;
        this.content = content;
        this.category = category;
        this.commentCount = commentCount;
        this.openedDate = openedDate;
        this.closedDate = closedDate;
        this.period = period;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getOpenedDate() {
        return openedDate;
    }

    public void setOpenedDate(Long openedDate) {
        this.openedDate = openedDate;
    }

    public Long getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Long closedDate) {
        this.closedDate = closedDate;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
