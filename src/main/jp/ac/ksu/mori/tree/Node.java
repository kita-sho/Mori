package src.main.jp.ac.ksu.mori.tree;

import src.main.jp.ac.ksu.mori.mvc.model.NodeModel;
import src.main.jp.ac.ksu.mori.mvc.view.NodeView;

public class Node {

    private NodeModel nodeModel;
    private NodeView nodeView;
    private Branch branch;


    public Node() {
        this.nodeModel = new NodeModel();
    }

    public void setNodeModel(NodeModel nodeModel) {
        this.nodeModel = nodeModel;
    }

    public void setBranch(Branch branch){
        this.branch = branch;
        this.nodeView.setBranchView(this.branch.getBranchView());
    }

    public NodeModel getNodeModel() {
        return this.nodeModel;
    }

    public NodeView setNodeView(NodeView nodeView){
        return this.nodeView  = nodeView;
    }

    public NodeView getNodeView(){
        return this.nodeView;
    }

    public Integer getId(){
        return this.nodeModel.getId();
    }

    public String getName(){
        return this.nodeModel.getName();
    }

    public Branch getBranch(){
        return this.branch;
    }

    @Override
    public String toString() {
        return "Node{name=" + getNodeModel().getName() +
               ", id=" + getNodeModel().getId() + 
               ", depth=" + getNodeModel().getDepth() + 
               ",children="+getNodeModel().getChildren().toString() +
               ",parent="+getNodeModel().getParent().toString() +
               "}";
    }
    
}
