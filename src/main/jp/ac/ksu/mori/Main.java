package src.main.jp.ac.ksu.mori;

import java.io.IOException;
import src.main.jp.ac.ksu.mori.mvc.model.TreeLoader;
import src.main.jp.ac.ksu.mori.mvc.view.View;
import src.main.jp.ac.ksu.mori.tree.Forest;

public class Main{

    public void run(){
     
        Forest forest = new Forest();

        try (TreeLoader treeLoader = new TreeLoader("../resource/data/forest.txt")) {
            forest = treeLoader.load(forest);
        } catch (IOException e) {
            System.out.println(e);
        }

        View view = new View();
        view.setModel(forest);
        view.paintInitialNode();
        view.aliginTree();
    
    }

    public static void main(String[] args){
        Main main = new Main();
        main.run();
    }

}

