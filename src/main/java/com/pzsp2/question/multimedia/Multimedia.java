package com.pzsp2.question.multimedia;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pzsp2.question.Question;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Multimedia {
    private Long multimediaId;
    private String type;
    private String link;
    private byte[] content;
    private Question question;

    @Id
    @Column(name = "MULTIMEDIA_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getMultimediaId() {
        return multimediaId;
    }

    public void setMultimediaId(Long multimediaId) {
        this.multimediaId = multimediaId;
    }

    @Basic
    @Column(name = "TYPE", length = 1, columnDefinition = "char")
    public String getType() {
        return type;
    }

    @Basic
    @Column(name = "LINK")
    public String getLink() {
        return link;
    }

    @Basic
    @Column(name = "CONTENT")
    public byte[] getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Multimedia that = (Multimedia) o;
        return Objects.equals(multimediaId, that.multimediaId)
                && Objects.equals(type, that.type)
                && Objects.equals(link, that.link)
                && Arrays.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(multimediaId, type, link);
        result = 31 * result + Arrays.hashCode(content);
        return result;
    }

    @JsonIgnore
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "QUESTION_ID", referencedColumnName = "QUESTION_ID", nullable = false)
    public Question getQuestion() {
        return question;
    }
}
