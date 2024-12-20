import java.util.TimerTask;

public class QuizTimer extends TimerTask {
    private static boolean timeOver = false;
    private static final int TIME_LIMIT = 60;  // Set the time limit in seconds
    private static int timeLeft = TIME_LIMIT;

    @Override
    public void run() {
        if (timeLeft > 0) {
            timeLeft--;
        } else {
            timeOver = true;
        }
    }

    public static boolean isTimeOver() {
        return timeOver;
    }

    public static void start() {
        timeLeft = TIME_LIMIT;
        timeOver = false;
    }
}
