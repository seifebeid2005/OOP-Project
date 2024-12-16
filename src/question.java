
public class Question {
    private long  questionId;
    private int quiz_id;
    private String question_text;
    private String A;
    private String B;
    private String C;
    private String D;
    private CorrectAnswer correct_answer; // Correct field type
    private String answer;

    public enum CorrectAnswer {
        A, B, C, D
    } 
    
    private static long generateRandomID() {
        return (long) (Math.random() * 10000000000L); // Generate a random 10-digit number
    }

    // Constructor with CorrectAnswer as a parameter type
    public Question( String question_text, String A, String B, String C, String D, CorrectAnswer correct_answer) {
        this.questionId = generateRandomID();
        this.question_text = question_text;
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
        this.correct_answer = correct_answer;
    }
         
    // Getter and setter methods
    public long getQuestion_id() {
        return questionId;
    }

    public void setQuestion_id(long questionId) {
        this.questionId = questionId;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public String getOption_a() {
        return A;
    }

    public void setOption_a(String A) {
        this.A = A;
    }

    public String getOption_b() {
        return B;
    }

    public void setOption_b(String B) {
        this.B = B;
    }

    public String getOption_c() {
        return C;
    }

    public void setOption_c(String C) {
        this.C = C;
    }

    public String getOption_d() {
        return D;
    }

    public void setOption_d(String D) {
        this.D = D;
    }

    public CorrectAnswer getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(CorrectAnswer correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    // validation if the answer is correct
    public boolean isCorrect() {
        return answer.equals(correct_answer.toString());
    }
    
    @Override
    public String toString() {
        return question_text + "\nA: " + A + " B: " + B + " C: " + C + " D: " + D;
    }
}
