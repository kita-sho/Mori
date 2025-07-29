package src.main.jp.ac.ksu.mori.mvc.view;

import java.awt.Component;
import java.awt.Point;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.concurrent.atomic.AtomicInteger;
import src.main.jp.ac.ksu.mori.mvc.controller.Controller;
import src.main.jp.ac.ksu.mori.mvc.model.Model;
import src.main.jp.ac.ksu.mori.tree.Forest;
import src.main.jp.ac.ksu.mori.tree.Node;

public class View  {

    private TreeFrame frame;
    private Controller controller;
    private Model model;
    private TreeJPanel treeJpanel;
    private final int  NodeWidth = 25;
    private final int  NodeHeight = 2;


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
      this.model.getForest().initForestTravel(travelInitialTree);
      this.frame.repaint();
    }

    public void aliginTree(){
        this.model.getForest().forestTravel();
    }

    private Function<Integer,Consumer<Node>> travelInitialTree = y ->  {
        return node -> {
            node.setNodeView(new NodeView(node.getNodeModel().getName(),0,y,node.getNodeModel()));

            node.getNodeView().addMouseListener(this.controller);
            this.treeJpanel.addComponent(node.getNodeView()); 
            
            if (node.getNodeView().getBranchView() != null){
                node.getNodeView().getBranchView().addMouseListener(this.controller);
                this.treeJpanel.addComponent(node.getNodeView().getBranchView());
            }
            this.treeJpanel.repaint();
        };
    };


    // private Consumer<Node> AliignTree = child ->{
    //     Node parentNode = child.getNodeModel().getParent();
    //     int parentWidth = parentNode.getNodeView().getWidth();
    //     child.getNodeModel().getParent().getNodeView().setNodeView(NodeWidth + parentWidth ,0);
    // };

       
    
    
}
