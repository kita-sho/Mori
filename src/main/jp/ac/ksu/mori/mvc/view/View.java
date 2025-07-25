package src.main.jp.ac.ksu.mori.mvc.view;

import java.awt.Component;
import java.awt.Point;
import java.util.function.Consumer;
import java.util.function.Function;
import src.main.jp.ac.ksu.mori.mvc.controller.Controller;
import src.main.jp.ac.ksu.mori.mvc.model.Model;
import src.main.jp.ac.ksu.mori.tree.Branch;
import src.main.jp.ac.ksu.mori.tree.Forest;
import src.main.jp.ac.ksu.mori.tree.Node;

public class View  {

    private TreeFrame frame;
    private Controller controller;
    private Model model;
    private TreeJPanel treeJpanel;

    public View(){
        this.treeJpanel = new TreeJPanel();
        this.frame = new TreeFrame();
        this.frame.setPanel(this.treeJpanel);
        this.controller = new Controller(frame);
        this.treeJpanel.addMouseListener(controller);
        this.treeJpanel.addMouseMotionListener(controller);

    }
    
    public TreeFrame getTreeFrame(){
        return this.frame;
    }

    public Controller getController(){
        return this.controller;
    }

    public void setModel(Forest forest){
        this.model = new Model(forest);
    }

    public Model getModel(){
        return this.model;
    }

    public void setComponent(Component component){
        this.treeJpanel.addComponent(component);
    }

    public void paintInitialNode(){
      this.model.getForest().initForestTravel(plusHeight);
      this.frame.repaint();
    }

    public void aliginTree(){

    }

    private Function<Integer,Consumer<Node>> plusHeight = y ->  {
        return node -> {
            node.setNodeView(new NodeView(node.getNodeModel().getName(),0,y));

            if(node.getNodeModel().getParent() != null){
                Point childPoint = node.getNodeView().getLeftMidPoint();
                Point parentPoint = node.getNodeModel().getParent().getNodeView().getRightMidPoint();
                Branch branch = new Branch();
                branch.setBranchView((int)childPoint.getX(), (int)childPoint.getY(), (int)parentPoint.getX(), (int)parentPoint.getY());
                branch.getBranchView().addMouseListener(this.controller);
                this.treeJpanel.addComponent(branch.getBranchView());
            }
          
            node.getNodeView().addMouseListener(this.controller);
            this.treeJpanel.addComponent(node.getNodeView());
        };
    };

       
    
    
}
