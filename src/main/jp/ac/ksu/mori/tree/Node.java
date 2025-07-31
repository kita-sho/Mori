package src.main.jp.ac.ksu.mori.tree;

import src.main.jp.ac.ksu.mori.mvc.model.NodeModel;
import src.main.jp.ac.ksu.mori.mvc.view.NodeView;
import src.main.jp.ac.ksu.mori.mvc.view.BranchView;
import java.awt.Point;

/*
 * 葉のデータと画面を管理するクラスです
 */
public class Node {

    private NodeModel nodeModel;
    private NodeView nodeView;

    public Node() {
        this.nodeModel = new NodeModel();
    }

    public void setNodeModel(NodeModel nodeModel) {
        this.nodeModel = nodeModel;
    }

    public NodeModel getNodeModel() {
        return this.nodeModel;
    }

    public void setNodeView(NodeView nodeView){
        this.nodeView = nodeView;

        if(this.nodeModel.getParent() != null){
            Point childPoint = this.nodeView.getLeftMidPoint();
            this.nodeModel.getParent().forEach(parent ->{

                if (parent.getNodeView() != null){
                    Point parentPoint = parent.getNodeView().getRightMidPoint();
                    BranchView branchView = new BranchView((int)childPoint.getX(), (int)childPoint.getY(), (int)parentPoint.getX(), (int)parentPoint.getY());
                    this.nodeView.setBranchView(branchView);
                }else{
                    BranchView branchView = new BranchView((int)childPoint.getX(), (int)childPoint.getY(), 0, 0);
                    this.nodeView.setBranchView(branchView);
                }
            });
        }
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
