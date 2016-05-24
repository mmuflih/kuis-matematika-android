package org.muflih.bamaop.impl;

import org.muflih.bamaop.pojo.Question;

import java.util.List;

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
public interface QuestionService {
    public Question getQuestion(int index);
    public List<Question> getQuestions();
    public boolean checkAnswers(int index, String answers);
}
