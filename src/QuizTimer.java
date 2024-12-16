import java.util.TimerTask;

public class QuizTimer extends TimerTask {

    private static long startTime;
    private static boolean timeOver = false;
    private static int quizDurationInSeconds = 60;  // Set your desired time limit in seconds (e.g., 60 seconds)

    @Override
    public void run() {
        long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;  // Time in seconds
        if (elapsedTime >= quizDurationInSeconds) {
            timeOver = true;
        }
    }

    public static void start() {
        startTime = System.currentTimeMillis();
        timeOver = false;
    }

    public static boolean isTimeOver() {
        return timeOver;
    }

    public static void reset() {
        startTime = 0;
        timeOver = false;
    }

    public static void setQuizDuration(int seconds) {
        quizDurationInSeconds = seconds;
    }
}
