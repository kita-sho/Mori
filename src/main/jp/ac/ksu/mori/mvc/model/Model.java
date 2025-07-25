package src.main.jp.ac.ksu.mori.mvc.model;

import src.main.jp.ac.ksu.mori.tree.Forest;

public class Model {

    private Forest forest;

    public Model(Forest forest){
        this.forest = forest;
    }

    public Forest getForest(){
        return this.forest;
    }
    
}
