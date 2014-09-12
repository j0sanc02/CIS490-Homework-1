package com.example.jpapp_000.rockpaperscissors;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.view.MenuItem;


public abstract class RockPaperScissors extends Activity implements DialogInterface.OnClickListener, View.OnClickListener {


    public enum Choice {
        ROCK, PAPER, SCISSORS
    }

    public enum Result {
        WIN, LOSE, DRAW
    }

    private Choice userSelection;
    private Result gameResult;


    @Override
    public void onClick(View v) {

        ImageView imageView = (ImageView) findViewById(R.id.imageViewMe);
        boolean game = true;

        switch (v.getId()) {
            case R.id.buttonPaper:
                userSelection = Choice.PAPER;
                imageView.setImageResource(R.drawable.paper);
                break;
            case R.id.buttonRock:
                userSelection = Choice.ROCK;
                imageView.setImageResource(R.drawable.rock);
                break;
            case R.id.buttonScissors:
                userSelection = Choice.SCISSORS;
                imageView.setImageResource(R.drawable.scissors);
                break;

        }

        if (game) {
            game();
            showResults();
        }
    }


    private void showResults() {
        AlertDialog.Builder builder = new AlertDialog.Builder(RockPaperScissors.this);
        builder.setCancelable(false);
        builder.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        if (gameResult == Result.LOSE) {
            builder.setMessage("Loser!!!");
        } else if (gameResult == Result.WIN) {
            builder.setMessage("Win!");
        } else if (gameResult == Result.DRAW) {
            builder.setMessage("Draw!!");
        }
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void game() {
        int compRandom = ((int) (Math.random() * 10)) % 3;
        Choice compChoice = null;
        ImageView imageView = (ImageView) findViewById(R.id.imageViewComp);

        switch (compRandom) {
            case 0:
                compChoice = Choice.PAPER;
                imageView.setImageResource(R.drawable.paper);
                break;
            case 1:
                compChoice = Choice.SCISSORS;
                imageView.setImageResource(R.drawable.scissors);
                break;
            case 2:
                compChoice = Choice.ROCK;
                imageView.setImageResource(R.drawable.rock);
                break;
        }

        if (compChoice == userSelection) {
            gameResult = Result.DRAW;
        } else if (compChoice == Choice.PAPER && userSelection == Choice.ROCK) {
            gameResult = Result.LOSE;
        } else if (compChoice == Choice.SCISSORS && userSelection == Choice.PAPER) {
            gameResult = Result.LOSE;
        } else if (compChoice == Choice.ROCK && userSelection == Choice.SCISSORS) {
            gameResult = Result.LOSE;
        } else {
            gameResult = Result.WIN;
        }


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rock_paper_scissors);

        Button buttonRock = (Button) findViewById(R.id.buttonRock);
        Button buttonPaper = (Button) findViewById(R.id.buttonPaper);
        Button buttonScissors = (Button) findViewById(R.id.buttonScissors);

        buttonPaper.setOnClickListener(this);
        buttonRock.setOnClickListener(this);
        buttonScissors.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.rock_paper_scissors, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
