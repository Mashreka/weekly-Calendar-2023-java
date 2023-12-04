import java.time.LocalDate;
//Class defination
public class CalendarDay {
    private LocalDate date;
    private String activity;
    //constructor
    public CalendarDay(LocalDate date) {
        this.date = date;
        this.activity = "";
    }
    //getter and setter

    public LocalDate getDate() {
        return date;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

}
