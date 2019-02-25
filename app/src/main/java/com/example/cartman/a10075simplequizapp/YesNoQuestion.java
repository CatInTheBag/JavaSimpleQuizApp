package com.example.cartman.a10075simplequizapp;

public class YesNoQuestion
{
   private int questionId;
   private boolean answer;

   public YesNoQuestion(int questionId,boolean correctAnswer)
   {
      this.questionId = questionId;
      answer = correctAnswer;
   }

   public int getQuestionId()
   {
      return questionId;
   }

   public void setQuestionId(int questionId)
   {
      this.questionId = questionId;
   }

   public boolean getAnswer()
   {
      return answer;
   }

   public void setAnswer(boolean answer)
   {
      this.answer = answer;
   }
}
