package srmicrosystems.cnote;

/**
 * Created by saman_000 on 24-09-2016.
 */
 public class  Counter {
    public static class  NotificationCounter{
    public static int NotificationCounter;
    public static Integer GetNotificationCounter(){
        Integer c = NotificationCounter;
        NotificationCounter++;
            return  c;

    }
    }
}


