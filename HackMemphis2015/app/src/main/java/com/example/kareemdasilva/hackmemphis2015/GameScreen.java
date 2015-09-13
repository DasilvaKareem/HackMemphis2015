package com.example.kareemdasilva.hackmemphis2015;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class GameScreen extends AppCompatActivity {
    public Question currentQuestion;
    public Question createQuestion(String questionAnswer, String questionQuestion, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3){
        currentQuestion = new Question(questionAnswer,questionQuestion,wrongAnswer1,wrongAnswer2,wrongAnswer3);
        player1ButtonA = (Button) findViewById(R.id.player1ButtonA);
        player2ButtonA = (Button) findViewById(R.id.player2ButtonA);
        player1ButtonB = (Button) findViewById(R.id.player1ButtonB);
        player1ButtonC = (Button) findViewById(R.id.player1ButtonC);
        player2ButtonB = (Button) findViewById(R.id.player2ButtonB);
        player2ButtonC = (Button) findViewById(R.id.player2ButtonC);

        player1ButtonA.setText(currentQuestion.wrongAnswers.get(0));
        player1ButtonB.setText(currentQuestion.wrongAnswers.get(1));
        player1ButtonC.setText(currentQuestion.wrongAnswers.get(2));


        player2ButtonA.setText(currentQuestion.wrongAnswers.get(0));
        player2ButtonB.setText(currentQuestion.wrongAnswers.get(1));
        player2ButtonC.setText(currentQuestion.wrongAnswers.get(2));
        player1ButtonA.setRotation(180);
        player1ButtonB.setRotation(180);
        player1ButtonC.setRotation(180);




        //Set text to buttons

        player1ButtonA.setBackgroundColor(Color.GREEN);
        player2ButtonA.setBackgroundColor(Color.BLUE);

        player1Question = (TextView) findViewById(R.id.player1Question);
        player2Question = (TextView) findViewById(R.id.player2Question);

        player1Question.setText(currentQuestion.question);
        player2Question.setText(currentQuestion.question);

        player2Question.setRotation(180);
        return currentQuestion;

    }
    public  class Question {
        String question;
        String answer;
        ArrayList<String> wrongAnswers = new ArrayList<String>(2);
        public Question(String currentAnswer, String currentQuestion, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3){
            question = currentQuestion;
            answer = currentAnswer;
            wrongAnswers.add(wrongAnswer1);
            wrongAnswers.add(wrongAnswer2);
            wrongAnswers.add(wrongAnswer3);
        }
        public boolean checkAnswer(String playerAnswer){
            if (playerAnswer == answer) {
                return true;

            } else {
                return  false;
            }
        }
    }
    public class Player {
        int lifePoints;
        int answers;
        boolean won;

        public  Player(int playerLifepoints, int playerAnswers) {
            lifePoints = playerLifepoints;
            answers = playerAnswers;
        }

        public void lostLifePoints(int currentPoints){
           lifePoints -= currentPoints;

        }
        public void correct(){
            answers++;
        }


    }

    private Timer gameTimer;
    private int gameCounter = 10;
    private TextView counter;
    private  TextView player1Question;
    private TextView player2Question;
    //Players
    public Player player1;
    public Player player2;

    private Button player1ButtonA;
    private Button player2ButtonA;
    private Button player1ButtonB;
    private Button player2ButtonB;
    private Button player1ButtonC;
    private Button player2ButtonC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);


        createQuestion("poop","what do people do to a barthoom","pee","poop","dfef");

        counter = (TextView) findViewById(R.id.timer); // Text for GameTimer
        gameTimer = new Timer();
        player1 = new Player(8000,0);
        player2 = new Player(8000,0);
        gameTimer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                runOnUiThread(new Runnable() {
                    public void run() {
                        if (gameCounter == 0) {
                            player1.lostLifePoints(1000);
                            player2.lostLifePoints(1000);
                            nextQuestion();


                        }
                        counter.setText(String.valueOf(gameCounter)); // you can set it to a textView to show it to the user to see the time passing while he is writing.
                        gameCounter--;


                        //checks if game counter hits zero

                    }
                });

            }
        }, 1000, 1000);
    }
    public  void player1AnswerB(View view){

        if (currentQuestion.checkAnswer(player1ButtonB.getText().toString()) == true) {
            player2.lostLifePoints(1000);
            if (player2.lifePoints == 0) {
                gameOver();
            }
            nextQuestion();
        }

    }
    public  void player1AnswerA(View view){

        if (currentQuestion.checkAnswer(player1ButtonA.getText().toString()) == true) {
            player2.lostLifePoints(1000);
            if (player2.lifePoints == 0) {
                gameOver();
            }
            nextQuestion();
        }

    }
    public  void player1AnswerC(View view){


        if (currentQuestion.checkAnswer(player1ButtonC.getText().toString()) == true) {
            player2.lostLifePoints(1000);
            if (player2.lifePoints == 0) {
                gameOver();
            }
            nextQuestion();
        }

    }
    public void player2AnswerB(View view){



        if (currentQuestion.checkAnswer(player1ButtonB.getText().toString())) {
            player1.lostLifePoints(1000);
            if (player1.lifePoints == 0) {
                gameOver();

            }
            nextQuestion();
        }
    }
    public void player2AnswerA(View view){



        if (currentQuestion.checkAnswer(player1ButtonA.getText().toString())) {
            player1.lostLifePoints(1000);
            if (player1.lifePoints == 0) {
                gameOver();

            }
            nextQuestion();
        }
    }
    public void player2AnswerC(View view){



        if (currentQuestion.checkAnswer(player1ButtonC.getText().toString())){
            player1.lostLifePoints(1000);
            if (player1.lifePoints == 0) {
                gameOver();

            }
            nextQuestion();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public int questionNumber = 0;
    public void nextQuestion(){
        if (player1.lifePoints == 0 || player2.lifePoints == 0) {

        } else {
            String[] questionSet = {"What is the biggest planet!","What is the capital of New York",
                    "Who created the Apple 1","Who was the first Super Saiyan",
                    "Who is the reincarnted form of the Ashura",
                    "What does Ram  stand for","What is Seiya's Constellaion","What is Jotaro's Stand is called"};
            String[] answerSet= {"Jupiter","Albany","Steve Wozinak","Goku","Naruto","Random Access Memory","Pegaseaus","Star Platinum"};
            String[] wrongAnswerSet1 = {"Sun","Albany","Steve Jobs","Broly","Naruto","Deez Nutz","The World"};
            String[] wrongAnswerSet2 = {"Jupiter","New York City","Steve Wozinak","Goku","Itachi", "Random offline Memory","Scorpio","Hermit Purple"};
            String[] wrongAnswerSet3 = {"Earth","Manhattan","Steve joestar","Bardock","Sasuke","Random Access Memory","Pegaseaus","Star Platinum"};
            gameCounter = 10;
            createQuestion(answerSet[questionNumber],questionSet[questionNumber],wrongAnswerSet1[questionNumber],wrongAnswerSet2[questionNumber],wrongAnswerSet3[questionNumber]);
            questionNumber++;
        }



    }

    public void gameOver(){
        Intent intent =  new Intent(this,Game_Over.class); //creats bridge to view
        startActivity(intent);
    }
}
