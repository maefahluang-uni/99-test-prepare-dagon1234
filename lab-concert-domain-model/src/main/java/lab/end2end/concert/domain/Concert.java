package lab.end2end.concert.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lab.end2end.concert.jackson.LocalDateTimeDeserializer;
import lab.end2end.concert.jackson.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Class to represent a Concert. A Concert is characterised by an unique ID,
 * title, date and time, and a featuring Performer.
 */
@Entity
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;

    private LocalDateTime date;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "performer_id", referencedColumnName = "id")
    private Performer performer;

    public Concert(Long id, String title, LocalDateTime date, Performer performer) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.performer = performer;
    }

    public Concert(String title, LocalDateTime date, Performer performer) {
        this(null, title, date, performer);
    }

    public Concert() {
    }

    public Concert(Performer performer) {
        this.performer = performer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Performer getPerformer() {
        return performer;
    }

}
