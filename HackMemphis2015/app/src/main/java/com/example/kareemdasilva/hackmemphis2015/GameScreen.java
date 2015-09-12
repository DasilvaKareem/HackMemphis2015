package com.example.kareemdasilva.hackmemphis2015;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class GameScreen extends ActionBarActivity {
    public Question createQuestion(){
        Question question = new Question("What is Naruto Signature Move","Rasengan","SHadow clone Jutsu","Chidori","RasenShiruken");
        player1ButtonA = (Button) findViewById(R.id.player1ButtonA);
        player2ButtonA = (Button) findViewById(R.id.player2ButtonA);
        player1ButtonB = (Button) findViewById(R.id.player1Button);
        player1ButtonC = (Button) findViewById(R.id.player1Button);
        player2ButtonB = (Button) findViewById(R.id.player2ButtonB);
        player2ButtonC = (Button) findViewById(R.id.player1Button);
        player1ButtonA.setBackgroundColor(Color.GREEN);
        player2ButtonA.setBackgroundColor(Color.BLUE);

        player1Question = (TextView) findViewById(R.id.player1Question);
        player2Question = (TextView) findViewById(R.id.player2Question);
        return

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



        player2Question.setRotation(180);
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
                        if (gameCounter == 0 ) {
                            gameTimer.cancel();


                        }
                        counter.setText(String.valueOf(gameCounter)); // you can set it to a textView to show it to the user to see the time passing while he is writing.
                        gameCounter--;




                        //checks if game counter hits zero

                    }
                });

            }
        }, 1000, 1000);
    }
    public  void player1Answer(View view){
        gameTimer.cancel();
        player2.lostLifePoints(1000);
        if (player2.lifePoints == 0) {
            Toast end = Toast.makeText(this,"Player 2 lost the game",Toast.LENGTH_SHORT);
            end.show();
        }
    }
    public void player2Answer(View view){
        player1.lostLifePoints(1000);
        gameTimer.cancel();
        if (player1.lifePoints == 0) {
            Toast end = Toast.makeText(this, "Player 1 lost the game", Toast.LENGTH_SHORT);
            end.show();

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



}
