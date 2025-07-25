package src.main.jp.ac.ksu.mori.tree;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;


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

    public void initForestTravel(Function<Integer,Consumer<Node>> plusHeight){
        AtomicInteger acc = new AtomicInteger(0);
        this.forest.forEach(tree -> {
            Integer memo = tree.initialTreeTravel(plusHeight,acc.get());
            acc.set(memo);
        });
    }

    public void forestTravel(){
        forest.forEach(tree -> {
            tree.treeTravel(tree.getStartNode());
        });
    }
    
}
