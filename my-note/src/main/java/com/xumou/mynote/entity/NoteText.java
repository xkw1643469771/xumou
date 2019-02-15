package com.xumou.mynote.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 */
@Entity
public class NoteText {

    @Id
    @GeneratedValue
    private Long id;
    private String text;
    private Long pid;

    @OneToOne
    @JoinColumn(name = "pid", referencedColumnName = "id")
    private NoteText parent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public NoteText getParent() {
        return parent;
    }

    public void setParent(NoteText parent) {
        this.parent = parent;
    }
}