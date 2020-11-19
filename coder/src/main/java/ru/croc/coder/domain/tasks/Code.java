package ru.croc.coder.domain.tasks;

import javax.persistence.*;

@Embeddable
public class Code {

    @Lob
    @Column(name = "text", nullable = false)
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(name = "language", nullable = false)
    private ProgrammingLanguage language;

    public String getText() {
        return text;
    }

    public Code setText(String text) {
        this.text = text;
        return this;
    }

    public ProgrammingLanguage getLanguage() {
        return language;
    }

    public Code setLanguage(ProgrammingLanguage language) {
        this.language = language;
        return this;
    }
}
