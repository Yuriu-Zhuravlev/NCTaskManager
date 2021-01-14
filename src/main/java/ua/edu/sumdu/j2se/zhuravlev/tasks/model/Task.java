package ua.edu.sumdu.j2se.zhuravlev.tasks.model;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public class Task implements Cloneable, Serializable {
    /**
     * 11.10.2020
     * Title of the task
     */
    private String title;
    /**
     * 11.10.2020
     * Time of not periodic tasks
     */
    private LocalDateTime time;
    /**
     * 11.10.2020
     * Start time of periodic tasks
     */
    private LocalDateTime start;
    /**
     * 11.10.2020
     * End time of periodic tasks
     */
    private LocalDateTime end;
    /**
     * 11.10.2020
     * Repeat interval of periodic tasks
     */
    private int interval;
    /**
     * 11.10.2020
     * Activity parameter, may be true or false
     */
    private boolean active;

    /**
     * 11.10.2020
     * Set title and time for not periodic tasks and set parameter active false
     *
     * @param title string task title
     * @param time  int time of not periodic tasks
     */
    public Task(String title, LocalDateTime time) {
        setTitle(title);
        setTime(time);
        setActive(false);
    }

    /**
     * 11.10.2020
     * Set title and time for periodic tasks and set parameter active false
     *
     * @param title    string task title
     * @param start    int start time of periodic tasks
     * @param end      int end time of periodic tasks
     * @param interval int repeat interval of periodic tasks
     */
    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) {
        setTitle(title);
        setTime(start, end, interval);
        setActive(false);
    }

    /**
     * 11.10.2020
     * Return title of the task
     *
     * @return title - string title of the task
     */
    public String getTitle() {
        return title;
    }

    /**
     * 11.10.2020
     * Set title of the task, if title is incorrect shows message ERROR
     *
     * @param title string title of the task
     */
    public void setTitle(String title) {
        if (title.equals("")) {
            throw new IllegalArgumentException("Title is empty");
        } else {
            this.title = title;
        }
    }

    /**
     * 11.10.2020
     * Return:
     * Is task active or not?
     *
     * @return active - boolean activity parameter, may be true or false
     */
    public boolean isActive() {
        return active;
    }

    /**
     * 11.10.2020
     * Set task activity
     *
     * @param active boolean activity parameter, may be true or false
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (isRepeated())
            return "Task{" +
                    "title='" + title + '\'' +
                    ", start=" + start.format(formatter) +
                    ", end=" + end.format(formatter) +
                    ", interval=" + interval +
                    ", active=" + active +
                    '}';
        else
            return "Task{" +
                    "title='" + title + '\'' +
                    ", time=" + time.format(formatter) +
                    ", active=" + active +
                    '}';
    }

    /**
     * 11.10.2020
     * Set time for not periodic tasks, if time is incorrect shows message ERROR
     *
     * @param time int time of not periodic tasks
     */
    public void setTime(LocalDateTime time) {
        if (time == null) {
            throw new IllegalArgumentException("time is null");
        } else {
            this.time = time.plusHours(0);
            this.start = time.plusHours(0);
            this.end = time.plusHours(0);
            this.interval = 0;
        }
    }

    /**
     * 11.10.2020
     * Set time for periodic tasks, if something is incorrect shows message ERROR
     *
     * @param start    int start time of periodic tasks
     * @param end      int end time of periodic tasks
     * @param interval int repeat interval of periodic tasks
     */
    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
        if (start == null) {
            throw new IllegalArgumentException("start < 0");
        } else if (!end.isAfter(start)) {
            throw new IllegalArgumentException("end < start");
        } else if (interval <= 0) {
            throw new IllegalArgumentException("interval < 0");
        } else if (start.plusSeconds(interval).isAfter(end)) {
            throw new IllegalArgumentException("too big repeat interval");
        } else {
            this.start = start.plusHours(0);
            this.end = end.plusHours(0);
            this.interval = interval;
            this.time = start.plusHours(0);
        }
    }

    /**
     * 11.10.2020
     * Return time of not periodic tasks
     *
     * @return time - int time of not periodic tasks
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * 11.10.2020
     * Return start time of periodic tasks
     *
     * @return start - int start time of periodic tasks
     */
    public LocalDateTime getStartTime() {
        return start;
    }

    /**
     * 11.10.2020
     * Return end time of periodic tasks
     *
     * @return end - int end time of periodic tasks
     */
    public LocalDateTime getEndTime() {
        return end;
    }

    /**
     * 11.10.2020
     * Return repeat interval of periodic tasks
     *
     * @return repeat - int repeat interval of periodic tasks
     */
    public int getRepeatInterval() {
        return interval;
    }

    /**
     * 11.10.2020
     *
     * @return is task repeated or not?
     */
    public boolean isRepeated() {
        return interval != 0;
    }

    /**
     * 11.10.2020
     *
     * @param current time to get time of next iteration after it
     * @return time of the next iteration. if there no one return -1
     */
    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if (current == null){
            throw new IllegalArgumentException("current is null");
        }
        if (current.isBefore(getEndTime()) && isActive()) {
            if (current.isBefore(getStartTime())) {
                return getStartTime();
            } else {
                if (isRepeated()) {
                    LocalDateTime i = getStartTime();
                    do {
                        i = i.plusSeconds(getRepeatInterval());
                    } while (i.isBefore(current));
                    if (i.equals(current)) {
                        return current.plusSeconds(getRepeatInterval());
                    } else {
                        if (i.isAfter(getEndTime()))
                            return null;
                        else
                            return i;
                    }
                } else
                {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return time.equals(task.time) &&
                start.equals(task.start) &&
                end.equals(task.end) &&
                interval == task.interval &&
                active == task.active &&
                title.equals(task.title);
    }

    @Override
    public int hashCode() {
        int result = 7;
        result = 7 * result + time.hashCode();
        result = 7 * result + start.hashCode();
        result = 7 * result + end.hashCode();
        result = 7 * result + interval;
        result = 7 * result + (title == null ? 0 : title.hashCode());
        result = 7 * result + (active ? 1 : 0);
        return result;
    }

    public Object clone () throws CloneNotSupportedException {
        Task clone = (Task) super.clone();
        clone.title = this.title;
        clone.time = this.time.plusHours(0);
        clone.active = this.active;
        clone.start = this.start.plusHours(0);
        clone.end = this.end.plusHours(0);
        clone.interval = this.interval;
        return clone;
    }

}
