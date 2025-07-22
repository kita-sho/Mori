package src.main.jp.ac.ksu.mori.mvc;

public class Node {

    private NodeModel nodeModel;

    public Node() {
        this.nodeModel = new NodeModel();
    }

    public void setNodeModel(NodeModel nodeModel) {
        this.nodeModel = nodeModel;
    }

    public NodeModel getNodeModel() {
        return this.nodeModel;
    }

    public Integer getId(){
        return this.nodeModel.getId();
    }

    public String getName(){
        return this.nodeModel.getName();
    }

    @Override
    public String toString() {
        return "Node{name=" + getNodeModel().getName() +
               ", id=" + getNodeModel().getId() + 
               ", depth=" + getNodeModel().getDepth() + 
               ",children="+getNodeModel().getChildren().toString() +
               "}";
    }
    
}
