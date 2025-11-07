package com.notas.notas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(max = 100)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column(name = "creation_date", updatable = false)
    private Date creation_date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private AppUser owner;

    public Note() {
        this.creation_date = new Date();
    }
    public Note(String titulo, String descripcion) {
        this.title = titulo;
        this.content = descripcion;
        this.creation_date = new Date();
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getCreation_date() {
        return creation_date;
    }
}
