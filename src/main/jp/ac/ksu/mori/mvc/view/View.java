package src.main.jp.ac.ksu.mori.mvc.view;

import java.awt.Component;
import java.util.function.Consumer;
import java.util.function.Function;
import src.main.jp.ac.ksu.mori.mvc.controller.Controller;
import src.main.jp.ac.ksu.mori.mvc.model.Model;
import src.main.jp.ac.ksu.mori.tree.Forest;
import src.main.jp.ac.ksu.mori.tree.Node;

/*
 * すべての画面を管理するクラスです
 */
public class View  {

    private TreeFrame frame;
    private Controller controller;
    private Model model;
    private TreeJPanel treeJpanel;
    private TreePopupMenu treePopupMenu;
    
    public View(){

        this.model = new Model();
        this.treeJpanel = new TreeJPanel();
        this.frame = new TreeFrame();
        this.frame.setPanel(this.treeJpanel);
        this.treePopupMenu = new TreePopupMenu(model, this);
        this.controller = new Controller(frame,treePopupMenu);
        this.treeJpanel.addMouseListener(controller);
        this.treeJpanel.addMouseMotionListener(controller);
        this.treePopupMenu.addMouseListener(controller);

    }

    public TreeFrame getTreeFrame(){
        return this.frame;
    }

    public Controller getController(){
        return this.controller;
    }

    public TreeJPanel getTreeJPanel(){
        return this.treeJpanel;
    }

    public TreePopupMenu getTreePopupMenu(){
        return this.treePopupMenu;
    }

    public void setForest(Forest forest){
        this.model.setForest(forest);
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
    
}
