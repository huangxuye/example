package com.wxuy.example.designPatterns.creationMode.prototypeMode.test;


import com.wxuy.example.designPatterns.creationMode.prototypeMode.QuestionBankController;

public class ApiTest {

    public static void main(String[] args) throws CloneNotSupportedException {

        QuestionBankController questionBankController = new QuestionBankController();
        System.out.println(questionBankController.createPaper("花花", "1000001921032"));
        System.out.println(questionBankController.createPaper("豆豆", "1000001921051"));
        System.out.println(questionBankController.createPaper("大宝", "1000001921987"));
    }

}
