package src.main.jp.ac.ksu.mori.mvc;

import java.util.List;
import java.util.ArrayList;

public class Forest {

    private List<Tree> forest;

    public Forest() {
        this.forest = new ArrayList<>();
    }

    public void setForest(List<Tree> forest) {
        this.forest = forest;
    }

    public List<Tree> getForest() {
        return this.forest;
    }

    public void addTree(Tree tree) {
        this.forest.add(tree);
    }

    public void forestTravel(){
        forest.forEach(tree -> {
            System.out.println(tree.getStartNode().getName());
            tree.treeTravel(tree.getStartNode());
        });
    }
    
}
