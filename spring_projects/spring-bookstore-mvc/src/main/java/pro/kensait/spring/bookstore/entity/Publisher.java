package pro.kensait.spring.bookstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PUBLISHER")
public class Publisher {
    // 出版社ID
    @Id
    @Column(name = "PUBLISHER_ID")
    private int publisherId;

    // 出版社名
    @Column(name = "PUBLISHER_NAME")
    private String publisherName;

    //  引数なしのコンストラクタ
    public Publisher() {
    }

    // コンストラクタ
    public Publisher(int publisherId, String publisherName) {
        this.publisherId = publisherId;
        this.publisherName = publisherName;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    @Override
    public String toString() {
        return "Publisher [publisherId=" + publisherId + ", publisherName="
                + publisherName + "]";
    }
}