package com.company;

public class User {
    private int id;
    private String login;
    private String password;
    private String secret_question;
    private String answer;
    private int money;
    public User() {
    }

    public User(int id, String login, String password, String secret_question, String answer, int money) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.secret_question = secret_question;
        this.answer = answer;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecret_question() {
        return secret_question;
    }

    public void setSecret_question(String secret_question) {
        this.secret_question = secret_question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }


}
