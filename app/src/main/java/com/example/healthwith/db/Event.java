package com.example.healthwith.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Event {
    @PrimaryKey(autoGenerate = true)
    private int id;
    /*
    int 값을 통한 eventType 설정
    0 : 걸음 수 event
    1 : 칼로리 섭취
    2 : 칼로리 소모
    3 : 물 섭취
     */
    @NonNull
    @ColumnInfo(name = "event-type")
    private int eventType;
    private Date date;
    private double amount;

    public Event(int eventType, Date date, double amount) {
        this.eventType = eventType;
        this.date = date;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", eventType=" + eventType +
                ", date=" + date +
                ", amount=" + amount +
                '}';
    }
}
