package kr.laptop.school.petitions.datas;

/**
 * Created by devkg on 2017-12-16.
 */

public class Article {
    private String uuid;        // 게시글 uuid
    private String author;      // 게시글 작성자

    private String title;       // 게시물 제목
    private String content;     // 게시물 내용

    private Long openedDate;    // 게시일
    private Long closedDate;    // 종료일
    private int period;         // 게시기간

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
