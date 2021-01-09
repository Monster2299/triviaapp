package com.mehedi.triviaapp.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.mehedi.triviaapp.controller.AppController;
import com.mehedi.triviaapp.model.Questions;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class QuestionBank {
    ArrayList<Questions> questionsArrayList = new ArrayList<>();
   private String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";
   public List<Questions> getQuestions(final AnswerListAsyncResponse callBack){
       JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
               Request.Method.GET,
               url,
               (JSONArray) null,
               new Response.Listener<JSONArray>() {
                   @Override
                   public void onResponse(JSONArray response) {
                       for(int i=0;i<response.length();i++){
                           try {
                               Questions questions = new Questions();
                               questions.setAnswer(response.getJSONArray(i).get(0).toString());
                               questions.setAnswerStatus(response.getJSONArray(i).getBoolean(1));

                               //add questions to list
                               questionsArrayList.add(questions);

                              // Log.d("questions", "onResponse: "+questions.getAnswer());
                              // Log.d("json", "onResponse: "+response.getJSONArray(i).get(0));
                               //Log.d("json2", "onResponse: "+response.getJSONArray(i).getBoolean(1));

                           } catch (JSONException e) {
                               e.printStackTrace();
                           }
                       }
                       if(null != callBack) callBack.processFinished(questionsArrayList);
                   }
               },
               new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {

                   }
               });
       AppController.getInstance().addToRequestQueue(jsonArrayRequest);

       return questionsArrayList;
   }
}
