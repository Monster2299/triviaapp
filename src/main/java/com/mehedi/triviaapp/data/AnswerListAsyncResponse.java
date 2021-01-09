package com.mehedi.triviaapp.data;

import com.mehedi.triviaapp.model.Questions;

import java.util.ArrayList;

public interface AnswerListAsyncResponse {
    void processFinished(ArrayList<Questions> questionsArrayList);
}
