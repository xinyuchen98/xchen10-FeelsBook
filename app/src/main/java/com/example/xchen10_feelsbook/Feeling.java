package com.example.xchen10_feelsbook;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Feeling {
    protected String emotion;
    protected Date date;
    protected String comment;

    public Feeling(String emotion) {
        this.emotion = emotion;
        this.date = new Date();
    }

    public Feeling(String emotion, Date date) {
        this.emotion = emotion;
        this.date = date;
    }

    public Feeling(String emotion, Date date, String comment) {
        this.emotion = emotion;
        this.date = date;
        this.comment = comment;
    }

    public String getEmotion() {
        return this.emotion;
    }

    public String getDate() {
        DateFormat format = new SimpleDateFormat("MMM d, yyyy HH:mm:ss aaa", Locale.ENGLISH);
        return format.format(this.date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) throws TooLongCommentException {
        if (comment.length() > 100) {
            throw new TooLongCommentException("Comment should be less than 100 characters. ");
        }
        else {
            this.comment = comment;
        }
    }

    public String toString() {
        return this.emotion + " | " + this.date.toString() + " | " + this.comment;
    }
}
