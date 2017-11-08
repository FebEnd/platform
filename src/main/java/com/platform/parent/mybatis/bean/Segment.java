package com.platform.parent.mybatis.bean;

import java.sql.Timestamp;

/**
 * Created by dengb.
 * 时间段
 */
public class Segment implements Comparable<Segment>{
    private Timestamp start;
    private Timestamp end;

    public Segment(Timestamp start, Timestamp end)
    {
        this.start = start;
        this.end = end;
    }

    public Timestamp getStart() {
        return start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public boolean hasOverlap(Segment segment)
    {
        if (segment == null)
        {
            return false;
        }

        if (this.start.compareTo(segment.getEnd()) >= 0 ||
                segment.getStart().compareTo(this.end) >= 0)
        {
            return false;
        }

        return true;
    }

    public String toString()
    {
        return "Start: " + start + ", End: " + end + "\n";
    }

    public boolean equals(Object o)
    {
        if (!(o instanceof Segment))
        {
            return false;
        }

        Segment seg = (Segment)o;
        return this.start == seg.start && this.end == seg.end;
    }

    public int compareTo(Segment segment)
    {
        if (segment == null)
        {
            return 1;
        }

        int startCompare = this.getStart().compareTo(segment.getStart());
        int endCompare = this.getEnd().compareTo(segment.getEnd());
        if (startCompare == 0 && endCompare == 0)
        {
            return 0;
        }

        if (startCompare == 0)
        {
            return endCompare;
        }

        return startCompare;
    }

    public boolean contains(Segment segment)
    {
        if (segment == null)
        {
            return false;
        }

        return (this.getStart().compareTo(segment.getStart()) <= 0 && this.getEnd().compareTo(segment.getEnd()) >= 0);
    }

    public long days()
    {
        return (end.getTime() - start.getTime())/3600/24/1000;
    }
}
