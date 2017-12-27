package com.example.adnroid.icehockeyscoretracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Tracks score for Home team
    int scoreHome = 0;
    //Tracks score for Guest team
    int scoreGuest = 0;
    //Tracks the current period
    int currentPeriod = 1;
    //Keys for saving sate of important values when app is destroyed
    private static final String SCORE_HOME = "SavedStateOfScoreHome";
    private static final String SCORE_GUEST = "SavedStateOfScoreGuest";
    private static final String CURRENT_PERIOD = "SavedStateOfCurrentPeriod";
    private static final String CURRENT_PERIOD_TEXT = "SavedStateOfCurrentPeriodText";
    private static final String PERIOD_1_SCORE = "SavedStateOfPeriod1Score";
    private static final String PERIOD_2_SCORE = "SavedStateOfPeriod2Score";
    private static final String PERIOD_3_SCORE = "SavedStateOfPeriod3Score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        scoreHome = savedInstanceState.getInt(SCORE_HOME);
        scoreGuest = savedInstanceState.getInt(SCORE_GUEST);
        currentPeriod = savedInstanceState.getInt(CURRENT_PERIOD);
        if (currentPeriod > 3) {
            Button goalHome = (Button)findViewById(R.id.goal_home);
            goalHome.setEnabled(false);
            Button goalGuest = (Button)findViewById(R.id.goal_guest);
            goalGuest.setEnabled(false);
        }
        displayForHome(scoreHome);
        displayForGuest(scoreGuest);
        displayCurrentPeriod(savedInstanceState.getString(CURRENT_PERIOD_TEXT));
        displayScorePeriod1(savedInstanceState.getString(PERIOD_1_SCORE));
        displayScorePeriod2(savedInstanceState.getString(PERIOD_2_SCORE));
        displayScorePeriod3(savedInstanceState.getString(PERIOD_3_SCORE));
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt(SCORE_HOME, scoreHome);
        savedInstanceState.putInt(SCORE_GUEST, scoreGuest);
        savedInstanceState.putInt(CURRENT_PERIOD, currentPeriod);
        savedInstanceState.putString(CURRENT_PERIOD_TEXT, ((TextView)findViewById(R.id.current_period)).getText().toString());
        savedInstanceState.putString(PERIOD_1_SCORE, ((TextView)findViewById(R.id.period_1_score)).getText().toString());
        savedInstanceState.putString(PERIOD_2_SCORE, ((TextView)findViewById(R.id.period_2_score)).getText().toString());
        savedInstanceState.putString(PERIOD_3_SCORE, ((TextView)findViewById(R.id.period_3_score)).getText().toString());
        super.onSaveInstanceState(savedInstanceState);
    }
    /**
     * Displays the given score for Home team.
     */
    public void displayForHome(int score) {
        TextView scoreView = (TextView) findViewById(R.id.home_score);
        scoreView.setText(String.valueOf(score));
    }
    /**
     * Displays the given score for Guest team.
     */
    public void displayForGuest(int score) {
        TextView scoreView = (TextView) findViewById(R.id.guest_score);
        scoreView.setText(String.valueOf(score));
    }
    //Displays current period
    public void displayCurrentPeriod(String message) {
        TextView periodTextView = (TextView) findViewById(R.id.current_period);
        periodTextView.setText(message);
    }
    //Displays score for period 1
    public void displayScorePeriod1(String message) {
        TextView period1TextView = (TextView) findViewById(R.id.period_1_score);
        period1TextView.setText(message);
    }
    //Displays score for period 2
    public void displayScorePeriod2(String message) {
        TextView period1TextView = (TextView) findViewById(R.id.period_2_score);
        period1TextView.setText(message);
    }
    //Displays score for period 3
    public void displayScorePeriod3(String message) {
        TextView period1TextView = (TextView) findViewById(R.id.period_3_score);
        period1TextView.setText(message);
    }

    //Ads 1 goal to Home team
    public void goalHome(View v) {
        scoreHome = scoreHome + 1;
        displayForHome (scoreHome);
    }
    //Ads 1 goal to Guest team
    public void goalGuest(View v) {
        scoreGuest = scoreGuest + 1;
        displayForGuest(scoreGuest);
    }
    //Advances to next period
    public void nextPeriod(View v) {
        switch (currentPeriod) {
            case 1:
                currentPeriod = 2;;
                displayCurrentPeriod(getString(R.string.period_2));
                displayScorePeriod1(scoreHome + ":" + scoreGuest);
                scoreHome = 0;
                scoreGuest = 0;
                displayForHome(scoreHome);
                displayForGuest(scoreGuest);
                break;
            case 2:
                currentPeriod = 3;
                displayCurrentPeriod(getString(R.string.period_3));
                displayScorePeriod2(scoreHome + ":" + scoreGuest);
                scoreHome = 0;
                scoreGuest = 0;
                displayForHome(scoreHome);
                displayForGuest(scoreGuest);
                break;
            case 3:
                currentPeriod = 4;
                displayCurrentPeriod(getString(R.string.match_over));
                displayScorePeriod3(scoreHome + ":" + scoreGuest);
                scoreHome = 0;
                scoreGuest = 0;
                displayForHome(scoreHome);
                displayForGuest(scoreGuest);
                Button goalHome = (Button)findViewById(R.id.goal_home);
                goalHome.setEnabled(false);
                Button goalGuest = (Button)findViewById(R.id.goal_guest);
                goalGuest.setEnabled(false);
                break;
        }
    }
    //Resets the game
    public void resetScore(View v) {
        Button goalHome = (Button)findViewById(R.id.goal_home);
        goalHome.setEnabled(true);
        Button goalGuest = (Button)findViewById(R.id.goal_guest);
        goalGuest.setEnabled(true);
        scoreHome = 0;
        scoreGuest = 0;
        displayForHome(scoreHome);
        displayForGuest(scoreGuest);
        currentPeriod = 1;
        displayCurrentPeriod(getString(R.string.period_1));
        displayScorePeriod1(getString(R.string.no_score));
        displayScorePeriod2(getString(R.string.no_score));
        displayScorePeriod3(getString(R.string.no_score));
    }
}
