package ua.edu.sumdu.j2se.zhuravlev.tasks;

public class Task {
    /**
     * 11.10.2020
     * Title of the task
     */
    private String title;
    /**
     * 11.10.2020
     * Time of not periodic tasks
     */
    private int time;
    /**
     * 11.10.2020
     * Start time of periodic tasks
     */
    private int start;
    /**
     * 11.10.2020
     * End time of periodic tasks
     */
    private int end;
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
    public Task(String title, int time) {
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
    public Task(String title, int start, int end, int interval) {
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
            System.out.println("ERROR: no title");
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
        return "Task{" +
                "title='" + title + '\'' +
                ", time=" + time +
                ", start=" + start +
                ", end=" + end +
                ", interval=" + interval +
                ", active=" + active +
                '}';
    }

    /**
     * 11.10.2020
     * Set time for not periodic tasks, if time is incorrect shows message ERROR
     *
     * @param time int time of not periodic tasks
     */
    public void setTime(int time) {
        if (time < 0) {
            System.out.println("ERROR: incorrect time");
        } else {
            this.time = time;
            this.start = time;
            this.end = time;
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
    public void setTime(int start, int end, int interval) {
        if (start < 0) {
            System.out.println("ERROR: incorrect start time");
        } else if (end < start) {
            System.out.println("ERROR: incorrect end time");
        } else if (interval <= 0) {
            System.out.println("ERROR: is not repeated");
        } else if ((start + interval) > end) {
            System.out.println("ERROR: too big repeat interval");
        } else {
            this.start = start;
            this.end = end;
            this.interval = interval;
            this.time = start;
        }
    }

    /**
     * 11.10.2020
     * Return time of not periodic tasks
     *
     * @return time - int time of not periodic tasks
     */
    public int getTime() {
        return time;
    }

    /**
     * 11.10.2020
     * Return start time of periodic tasks
     *
     * @return start - int start time of periodic tasks
     */
    public int getStartTime() {
        return start;
    }

    /**
     * 11.10.2020
     * Return end time of periodic tasks
     *
     * @return end - int end time of periodic tasks
     */
    public int getEndTime() {
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
    public int nextTimeAfter(int current) {
        if ((current < getEndTime()) && isActive()
                && ((current + getRepeatInterval()) < getEndTime())) {
            if (current < getStartTime()) {
                return getStartTime();
            } else {
                int i = getStartTime();
                do {
                    i += getRepeatInterval();
                } while (i < (current));
                if (i == (current)) {
                    return current + getRepeatInterval();
                } else {
                    return i;
                }
            }
        } else {
            return -1;
        }
    }
}
