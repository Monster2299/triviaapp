package com.mehedi.triviaapp.model;

public class Questions {
    private String answer;
    private boolean answerStatus;

    public Questions(){

    }

    public Questions(String answer, boolean answerStatus) {
        this.answer = answer;
        this.answerStatus = answerStatus;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(boolean answerStatus) {
        this.answerStatus = answerStatus;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "answer='" + answer + '\'' +
                ", answerStatus=" + answerStatus +
                '}';
    }
}
