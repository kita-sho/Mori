package src.main.jp.ac.ksu.mori.tree;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.swing.Timer;
import src.main.jp.ac.ksu.mori.utility.TailCallUtil;
import src.main.jp.ac.ksu.mori.utility.TailCall;


public class Tree {

    private List<Node> tree;
    private Node startNode;
    private Map<Integer, Node> nodeNumberMap;
    private Map<String,Node> nodeNameMap;
    private TailCallUtil tailCallUtil;

    public Tree(){
        this.tree = new ArrayList<>();
        this.startNode = null;
        this.nodeNumberMap = new HashMap<>();
        this.nodeNameMap = new HashMap<>();
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

    public void treeTravel(Node startNode) {

        List<Integer> children = startNode.getNodeModel().sortChildren();
        AtomicInteger accWidth = new AtomicInteger(0);
        AtomicInteger accHeight = new AtomicInteger(0);

        children.forEach(child -> {
            Node childNode = this.getNodeNumberMap().get(child);
            Node parentNode = childNode.getNodeModel().getParent();
            int parentX = parentNode.getNodeView().getX();
            int parentWidth = parentNode.getNodeView().getWidth();
            int parentHeight = parentNode.getNodeView().getHeight();
            childNode.getNodeView().updateNodeView(parentX + parentWidth + 25 ,accWidth.get());
            accWidth.getAndAdd(childNode.getNodeView().getHeight() + 2);

            if (childNode.getNodeModel().getChildren() != null){
                treeTravel(childNode);
            }

        });
    }

    public Integer getInitialHeight(Integer n){
        return this.calculateHight(n,n,0).call();
    }

    public TailCall<Integer> calculateHight(int currentIndex,int maxIndex,int sum){
        if(currentIndex < 0){ return this.tailCallUtil.complete(sum);}
        return tailCallUtil.nextCall(() -> calculateHight(currentIndex-1,sum + getNode(maxIndex - currentIndex).getNodeView().getHeight(),maxIndex));
    }

}
