package org.muflih.bamaop;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.muflih.bamaop.impl.QuestionServiceImpl;
import org.muflih.bamaop.pojo.Question;

public class MainActivity extends AppCompatActivity {

    private QuestionServiceImpl questionService;
    private int index = 0;
    private TextView tvQuestions;
    private TextView tvCorrectionAnswer;
    private ImageView ivCorrectionAnswer;
    private TextView txtTimer;
    private TextView txtResult;
    private Button btnAnswer1;
    private Button btnAnswer3;
    private Button btnAnswer2;
    private Button btnAnswer4;
    private Drawable img;
    private int jumlahSoal;
    private int jawabanBenar = 0;
    private int jawabanSalah = 0;
    private boolean answered = false;
    private boolean isTrue = false;
    private int duration = 5;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initComponent();

        questionService = new QuestionServiceImpl();
        jumlahSoal = questionService.getQuestions().size();
        this.setQuestion(index, false);
        int duration = 0;
        for(Question q : questionService.getQuestions()) {
            duration += q.getDuration();
        }
        countDown(duration);

        btnAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(index, btnAnswer1.getText().toString());
            }
        });

        btnAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(index, btnAnswer2.getText().toString());
            }
        });

        btnAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(index, btnAnswer3.getText().toString());
            }
        });

        btnAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(index, btnAnswer4.getText().toString());
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://org.muflih.bamaop/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://org.muflih.bamaop/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    private void checkAnswer(int index, String answer) {
        try {
            String result;
            answered = true;
            if (questionService.checkAnswers(index, answer)) {
                result = "Benar";
                img = getResources().getDrawable(R.drawable.benar);
                jawabanBenar ++;
                isTrue = true;
            } else {
                result = "Salah";
                img = getResources().getDrawable(R.drawable.salah);
                jawabanSalah ++;
            }
            if (!this.countIndex()) {
                this.setQuestion(this.index, answered);
                answered = false;
            }
            tvCorrectionAnswer.setText(result);
            ivCorrectionAnswer.setImageDrawable(img);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void setQuestion(int index, boolean answered) {
        Question question = questionService.getQuestion(index);
        this.duration = question.getDuration();
        tvQuestions.setText("Berapakah Hasil " + question.getQuestion());
        btnAnswer1.setText(question.getAnswer1());
        btnAnswer2.setText(question.getAnswer2());
        btnAnswer3.setText(question.getAnswer3());
        btnAnswer4.setText(question.getAnswer4());
        if (!answered && this.index != 0) {
            jawabanSalah ++;
            String result = "Salah";
            img = getResources().getDrawable(R.drawable.salah);
            tvCorrectionAnswer.setText(result);
            ivCorrectionAnswer.setImageDrawable(img);
        }
        txtResult.setText("[Soal: " + (jumlahSoal - 1) + "] | [Benar: " + jawabanBenar + "] | [Salah: " + jawabanSalah + "]");
    }

    private void initComponent() {
        tvQuestions = (TextView) findViewById(R.id.question);
        txtTimer = (TextView) findViewById(R.id.timer);
        btnAnswer1 = (Button) findViewById(R.id.answer1);
        btnAnswer3 = (Button) findViewById(R.id.answer2);
        btnAnswer2 = (Button) findViewById(R.id.answer3);
        btnAnswer4 = (Button) findViewById(R.id.answer4);
        tvCorrectionAnswer = (TextView) findViewById(R.id.txtCorrectionAnswer);
        ivCorrectionAnswer = (ImageView) findViewById(R.id.imgCorrectionAnswer);
        img = getResources().getDrawable(R.drawable.kosong);
        txtResult = (TextView) findViewById(R.id.txtResult);
    }

    private boolean countIndex() {
        if (index >= questionService.getQuestions().size() - 1) {
            btnAnswer1.setEnabled(false);
            btnAnswer2.setEnabled(false);
            btnAnswer3.setEnabled(false);
            btnAnswer4.setEnabled(false);
            return true;
        } else {
            this.index++;
            return false;
        }
    }

    private int detik = 0;
    private void countDown(int d) {
        new CountDownTimer((d * 1000), 1000) {

            public void onTick(long millisUntilFinished) {
                if (detik == 0) {
                    detik = (int)(millisUntilFinished / 1000);
                }
                detik --;
                if (!txtTimer.getText().equals("Selesai") && btnAnswer1.isEnabled()) {
                    if (isTrue) {
                        int resetDetik = detik % duration;
                        int penambah = duration - resetDetik - 1;
                        detik += penambah;
                        isTrue = false;
                    }

                    txtTimer.setText("" + detik);
                    if (detik % duration == 0) {
                        if (!countIndex()) {
                            setQuestion(index, false);
                        }
                    }

                    if (detik % duration == duration - 1) {
                        tvCorrectionAnswer.setText("");
                        img = getResources().getDrawable(R.drawable.kosong);
                        ivCorrectionAnswer.setImageDrawable(img);
                    }
                } else {
                    txtTimer.setText("Selesai");
                    tvCorrectionAnswer.setText("Selesai");
                    img = getResources().getDrawable(R.drawable.terimakasih);
                    ivCorrectionAnswer.setImageDrawable(img);
                }
            }

            public void onFinish() {
                txtTimer.setText("Selesai");
            }
        }.start();
    }
}
