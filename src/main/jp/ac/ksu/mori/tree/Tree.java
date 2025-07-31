package src.main.jp.ac.ksu.mori.tree;

import java.awt.Point;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import src.main.jp.ac.ksu.mori.utility.TreeTimer;

/*
 * 木構造を管理すうクラスです
 */
public class Tree {

    private List<Node> tree;
    private Node startNode;
    private Map<Integer, Node> nodeNumberMap;
    private Map<String,Node> nodeNameMap;
    private TreeTimer treeTimer;

    private final int HEIGHT = 2;
    private final int WIDHT = 25;

    public Tree(){
        this.tree = new ArrayList<>();
        this.startNode = null;
        this.nodeNumberMap = new HashMap<>();
        this.nodeNameMap = new HashMap<>();
        this.treeTimer = new TreeTimer();
    }

    public void setTree(List<Node> tree) {
        this.tree = tree;
    }

    public void setStartpoint(Node startpoint) {
        this.startNode = startpoint;
    }

    public void setNodeNumberMap(Map<Integer, Node> nodeNumberMap) {
        this.nodeNumberMap = nodeNumberMap;
    }

    public void setNodeNameMap(Map<String, Node> nodeNameMap) {
        this.nodeNameMap = nodeNameMap;
    }

    public TreeTimer getTreeTimer(){
        return this.treeTimer;
    }

    public List<Node> getTree() {
        return this.tree;
    }

    public Node getNode(Integer index){
        return this.tree.get(index);
    }

    public Node getStartNode() {
        return this.startNode;
    }

    public Map<Integer, Node> getNodeNumberMap() {
        return this.nodeNumberMap;
    }

    public Map<String, Node> getNodeNameMap() {
        return this.nodeNameMap;
    }

    public void addNode(Node node) {
        this.tree.add(node);
    }

    public void addNodeByNumber(Integer number, Node node) {
        this.nodeNumberMap.put(number, node);
    }

    public void addNodeByName(String name, Node node) {
        this.nodeNameMap.put(name, node);
    }

    public void treeTravel(Consumer<Node> consumer){
        tree.forEach(node -> {
            consumer.accept(node);
        });
    }

    public int initialTreeTravel(Function<Integer,Consumer<Node>> plusHeight,int memo){
        AtomicInteger acc = new AtomicInteger(memo);
        this.tree.forEach(node ->{   
            plusHeight.apply(acc.get()).accept(node);
            int nodeHeight = node.getNodeView().getHeight();
            acc.addAndGet(nodeHeight);
        });
        return acc.get();
    }

    public Integer treeTravel(Node startNode, Integer accHeight) {

        if (!startNode.getNodeModel().getChildren().isEmpty()) {
            List<Integer> children = startNode.getNodeModel().sortChildren();
    
            int currentHeight = accHeight;
    
            for (Integer child : children) {
    
                Node childNode = this.getNodeNumberMap().get(child);
                int childY = currentHeight;
    
                this.treeTimer.add(() -> {
                    childNode.getNodeModel().getParent().forEach(parentNode ->{
                        int parentX = parentNode.getNodeView().getX();
                        int parentWidth = parentNode.getNodeView().getWidth();
                        int childX = parentX + parentWidth + WIDHT;
        
                        childNode.getNodeView().updateNodeView(childX, childY);
                        childNode.getNodeView().getBranchView().updateBranchView(
                            parentNode.getNodeView().getRightMidPoint(),
                            childNode.getNodeView().getLeftMidPoint()
                        );
                    });
                    
                   
                });
    
                currentHeight = treeTravel(childNode, childY + childNode.getNodeView().getHeight() + HEIGHT);
            }
    
            this.treeTimer.add(() -> {

               
                
                Node firstNode = this.getNodeNumberMap().get(children.get(0));
                Node lastNode = this.getNodeNumberMap().get(children.get(children.size() - 1));
                Point firstNodeCoordinate = firstNode.getNodeView().getTopLeftPoint();
                Point lastNodeCoordinate = lastNode.getNodeView().getButtomLeftPoint();
                int new_YCoordinate = (int) ((firstNodeCoordinate.getY() + lastNodeCoordinate.getY()) / 2);
                startNode.getNodeView().updateNodeView(
                    startNode.getNodeView().getX(),new_YCoordinate - (startNode.getNodeView().getHeight()/2)
                );
            });
    
            List<Integer> startNodeChildren = startNode.getNodeView().getNodeModel().sortChildren();
            startNodeChildren.forEach(child -> {
                Node childNode = this.getNodeNumberMap().get(child);
                this.treeTimer.add(() -> {
                    childNode.getNodeView().getBranchView().updateBranchView(
                        startNode.getNodeView().getRightMidPoint(),
                        childNode.getNodeView().getLeftMidPoint()
                    );
                });
            });
    
            return currentHeight;
    
        } else {
            this.treeTimer.add(() -> {
                int childY = accHeight;

                startNode.getNodeModel().getParent().forEach(parentNode ->{
                    int parentX = parentNode.getNodeView().getX();
                    int parentWidth = parentNode.getNodeView().getWidth();
                    int childX = parentX + parentWidth + WIDHT;
        
                    startNode.getNodeView().updateNodeView(childX, childY);
                    startNode.getNodeView().getBranchView().updateBranchView(
                        parentNode.getNodeView().getRightMidPoint(),
                        startNode.getNodeView().getLeftMidPoint()
                    );
                });
            });
            return accHeight + HEIGHT;
        }
    }
    

}
