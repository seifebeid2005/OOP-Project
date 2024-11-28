package classes.Question;
public class question {
    private int question_id;
    private int quiz_id;
    private String question_text;
    private String option_a;
    private String option_b;
    private String option_c;
    private String option_d;
    private String correct_answer;
    
    public question(int question_id,int quiz_id,String question_text,String option_a,String option_b,String option_c,String option_d,String correct_answer) {
        this.question_id = question_id;
        this.quiz_id = quiz_id;
        this.question_text = question_text;
        this.option_a = option_a;
        this.option_b = option_b;
        this.option_c = option_c;
        this.option_d = option_d;
        this.correct_answer = correct_answer;


    }
    public int getQuestion_id() {
        return question_id;
    }
    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
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
        return option_a;
    }
    public void setOption_a(String option_a) {
        this.option_a = option_a;
    }
    public String getOption_b() {
        return option_b;
    }
    public void setOption_b(String option_b) {
        this.option_b = option_b;
    }
    public String getOption_c() {
        return option_c;
    }
    public void setOption_c(String option_c) {
        this.option_c = option_c;
    }
    public String getOption_d() {
        return option_d;
    }
    public void setOption_d(String option_d) {
        this.option_d = option_d;
    }
    public String getCorrect_answer() {
        return correct_answer;
    }
    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }
    public boolean validateAnswer(String answer) {
        return answer.equals(correct_answer);
    }
    public String toString() {
        return question_text + " " + option_a + " " + option_b + " " + option_c + " " + option_d;
    }


}