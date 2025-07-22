package src.main.jp.ac.ksu.mori.mvc;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;



public class Tree {

    private List<Node> tree;
    private Node startNode;
    private Map<Integer, Node> nodeNumberMap;
    private Map<String,Node> nodeNameMap;

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

    public void treeTravel(Node startNode) {

        List<Integer> children = startNode.getNodeModel().sortChildren();
        
        children.forEach(child -> {
            Node node = nodeNumberMap.get(child);
            System.out.println(node.getName());
            treeTravel(node);
        });
    }




}
