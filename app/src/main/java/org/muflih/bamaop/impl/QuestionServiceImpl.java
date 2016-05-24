package org.muflih.bamaop.impl;

import android.content.res.Resources;

import org.muflih.bamaop.pojo.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
public class QuestionServiceImpl implements QuestionService{
    private List<Question> questions;

    public QuestionServiceImpl() {
        questions = this.loadQuestions();
    }

    @Override
    public Question getQuestion(int index) {
        return questions.get(index);
    }

    @Override
    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public boolean checkAnswers(int index, String answers) {
        return (questions.get(index).getCorrectAnswer().trim().equals(answers));
    }

    private List<Question> loadQuestions() {
        List<Question> questions = new ArrayList<>();

        for (int i = 0; i <= 10; i ++) {
            questions.add(this.generateQuestion());
        }
        return questions;
    }

    private Question generateQuestion() {
        Question q = new Question();
        Random ran = new Random();
        Random ran2 = new Random();
        int bil1 = Math.round(ran.nextInt(100));
        int bil2 = Math.round(ran.nextInt(100));
        int operation = ran2.nextInt(4);
        float hasil = 0;
        int correctAnswer = 0;
        switch (operation) {
            case 0:
                hasil = bil1 + bil2;
                correctAnswer = ran2.nextInt(4);
                q.setQuestion(bil1 + " + " + bil2 + " = ?");
                q.setDuration(5);
                break;
            case 1:
                hasil = bil1 - bil2;
                correctAnswer = ran2.nextInt(4);
                q.setQuestion(bil1 + " - " + bil2 + " = ?");
                q.setDuration(5);
                break;
            case 2:
                hasil = bil1 * bil2;
                correctAnswer = ran2.nextInt(4);
                q.setQuestion(bil1 + " x " + bil2 + " = ?");
                q.setDuration(10);
                break;
            case 3:
                hasil = bil1 / bil2;
                correctAnswer = ran2.nextInt(4);
                q.setQuestion(bil1 + " ÷ " + bil2 + " = ?");
                q.setDuration(10);
                break;
        }

        switch (correctAnswer) {
            case 0:
                q.setAnswer1(hasil + "");
                q.setAnswer2((hasil + 1) + "");
                q.setAnswer3((hasil + 2) + "");
                q.setAnswer4((hasil - 1) + "");
                q.setCorrectAnswer(hasil + "");
                break;
            case 1:
                q.setAnswer1((hasil + 1) + "");
                q.setAnswer2(hasil + "");
                q.setAnswer3((hasil + 2) + "");
                q.setAnswer4((hasil - 1) + "");
                q.setCorrectAnswer(hasil + "");
                break;
            case 2:
                q.setAnswer1((hasil + 2) + "");
                q.setAnswer2((hasil + 1) + "");
                q.setAnswer3(hasil + "");
                q.setAnswer4((hasil - 1) + "");
                q.setCorrectAnswer(hasil + "");
                break;
            case 3:
                q.setAnswer1((hasil + 2) + "");
                q.setAnswer2((hasil + 1) + "");
                q.setAnswer3((hasil - 1) + "");
                q.setAnswer4(hasil + "");
                q.setCorrectAnswer(hasil + "");
                break;
        }
        return q;
    }
}
