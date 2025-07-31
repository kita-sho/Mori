package src.main.jp.ac.ksu.mori;

import src.main.jp.ac.ksu.mori.mvc.view.View;

/*
 * Mainクラスです。
 */
public class Main{

    public void run(){
        new View();
    }
    public static void main(String[] args){
        Main main = new Main();
        main.run();
    }

}

