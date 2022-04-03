package com.pzsp2.multimedia;

import com.pzsp2.question.Question;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Multimedia {
    private Long multimediaId;
    private String type;
    private String link;
    private byte[] content;
    private Question question;

    @Id
    @Column(name = "MULTIMEDIA_ID")
    public Long getMultimediaId() {
        return multimediaId;
    }

    public void setMultimediaId(Long multimediaId) {
        this.multimediaId = multimediaId;
    }

    @Basic
    @Column(name = "TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "LINK")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Basic
    @Column(name = "CONTENT")
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Multimedia that = (Multimedia) o;
        return Objects.equals(multimediaId, that.multimediaId) && Objects.equals(type, that.type) && Objects.equals(link, that.link) && Arrays.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(multimediaId, type, link);
        result = 31 * result + Arrays.hashCode(content);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID", referencedColumnName = "QUESTION_ID", nullable = false)
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
