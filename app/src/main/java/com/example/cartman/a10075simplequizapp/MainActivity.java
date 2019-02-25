package com.example.cartman.a10075simplequizapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
   //first we create button object
   Button trueButton;
   Button falseButton;
   TextView questionBox;
   TextView scoreTextView;
   ProgressBar progressBar;
   int index=0;
   int currentQuestion;
   int score;

   private YesNoQuestion[] allQuestions = new YesNoQuestion[] {
           new YesNoQuestion(R.string.question1,true),
           new YesNoQuestion(R.string.question2,true),
           new YesNoQuestion(R.string.question3,true),
           new YesNoQuestion(R.string.question4,true),
           new YesNoQuestion(R.string.question5,false)
   };

   final int PROGRESS_BAR_INCREMENT = (int)Math.ceil(100.0/allQuestions.length);

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      //we link our button object to xml code with findByViewId
      trueButton = findViewById(R.id.YesButton);
      falseButton = findViewById(R.id.FalseButton);
      questionBox = findViewById(R.id.QuestionTextView);
      scoreTextView = findViewById(R.id.ScoreTextView);
      progressBar = findViewById(R.id.progressBar);

      currentQuestion = allQuestions[index].getQuestionId();
      questionBox.setText(currentQuestion);


      //we must create listener for pressing the buttons
      //first way, to create listener variables
      View.OnClickListener listener = new View.OnClickListener()
      {

         @Override
         public void onClick(View v)
         {
            /*Log.d("Test","Button pressed!");
            Toast toastMes = Toast.makeText(getApplicationContext(),"Yes pressed!",Toast.LENGTH_SHORT);
            toastMes.show();*/
            checkAnswer(true);
            updateQuestion();
         }
      };

      //link the listener variable to our button
      trueButton.setOnClickListener(listener);

      //second way, to directly link the listner to our button
      falseButton.setOnClickListener(new View.OnClickListener()
      {
         @Override
         public void onClick(View v)
         {
            //Toast.makeText(getApplicationContext(),"No pressed!",Toast.LENGTH_SHORT).show();
            checkAnswer(false);
            updateQuestion();

         }
      });
   }

   private void updateQuestion()
   {
      index = (index+1) % allQuestions.length;

      if(index == 0 )
      {
         AlertDialog.Builder alert = new AlertDialog.Builder(this);
         alert.setTitle("Hope you ejoyed it :)");
         alert.setCancelable(false);
         alert.setMessage("You scored " +score + " out of "+ allQuestions.length);
         alert.setPositiveButton("Bye", new DialogInterface.OnClickListener()
         {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
               finish();
            }
         }).show();
      }
      currentQuestion = allQuestions[index].getQuestionId();
      questionBox.setText(currentQuestion);
      progressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
      scoreTextView.setText("Score " + score + "/" + allQuestions.length);
   }

   private void checkAnswer(boolean answer)
   {
      boolean rightAnswer = allQuestions[index].getAnswer();
      if (rightAnswer == answer)
      {
         Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show();
         score++;
      }
      else
      {
         Toast.makeText(getApplicationContext(),R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
      }

   }
}
