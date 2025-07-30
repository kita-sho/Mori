package src.main.jp.ac.ksu.mori.mvc.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;
import src.main.jp.ac.ksu.mori.mvc.view.TreeDialog;
import src.main.jp.ac.ksu.mori.mvc.view.TreeFrame;
import src.main.jp.ac.ksu.mori.mvc.view.TreeJPanel;
import src.main.jp.ac.ksu.mori.mvc.view.TreePopupMenu;
import src.main.jp.ac.ksu.mori.mvc.view.BranchView;
import src.main.jp.ac.ksu.mori.mvc.view.NodeView;


public class Controller extends MouseAdapter {

    private TreeFrame frame;
    private TreePopupMenu popupMenu;
    private Point originalPoint;
    private Point offset;

    public Controller (TreeFrame frame,TreePopupMenu popupMenu){
        this.frame = frame;
        this.popupMenu = popupMenu;
    }

    @Override
    public void mouseClicked(MouseEvent e){

        switch(e.getButton()){
            case MouseEvent.BUTTON1:
                Object source = e.getSource();

                if (source instanceof BranchView){
                    BranchView branch = (BranchView) source;
                    new TreeDialog(this.frame,branch.toString());
                }
                if (source instanceof NodeView){
                    NodeView nodeView = (NodeView) source;
                    new TreeDialog(this.frame,nodeView.toString());
                }
                break;
                         
            case MouseEvent.BUTTON3:
                this.popupMenu.show(e.getComponent(),e.getX(),e.getY());
                break;
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e){
        offset = e.getPoint();
        originalPoint = frame.getPanel().getLocation();
    }

    @Override
    public void mouseDragged(MouseEvent e){
        Point currentPoint = e.getPoint();

        int deltaX = currentPoint.x - offset.x;
        int deltaY = currentPoint.y - offset.y;

        int newX = originalPoint.x + deltaX;
        int newY = originalPoint.y + deltaY;
        System.out.println("newx="+newX + ",new=Y" + newY);

        TreeJPanel panel = this.frame.getPanel();
        panel.setLocation(panel.getX() + deltaX, panel.getY() + deltaY);
        this.frame.repaint();

    }


    
}

