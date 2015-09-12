package com.example.kareemdasilva.hackmemphis2015;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;


public class GameScreen extends ActionBarActivity {
    private Timer gameTimer;
    private int gameCounter = 10;
    private TextView counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        counter = (TextView) findViewById(R.id.timer); // Text for GameTimer
        gameTimer = new Timer();
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
