package org.muflih.bamaop.pojo;

/**
 * Created
 * on 5/5/16
 * by M. Muflikh Kholidin, S.Kom
 * Java(Scala), Android, PHP and Web Developer
 * <p/>
 * muflic.24@gmail.com
 * <p/>
 * https://github.com/mufl1h
 * https://bitbucket.org/mufl1h
 */
public class Question {

    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String correctAnswer;

    private int duration;

    public Question() {

    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Question(String question, String answer1, String answer2, String answer3, String answer4, String correctAnswer, int duration) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
        this.duration = duration;
    }

    public String toString() {
        return "questian=" + question
                + "answer1=" + answer1
                + "answer2=" + answer2
                + "answer3=" + answer3
                + "answer4=" + answer4
                + "correctAnswer=" + correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

}
