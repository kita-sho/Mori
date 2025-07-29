package src.main.jp.ac.ksu.mori.mvc.view;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JComponent;
import java.util.function.Function;
import java.util.List;

import src.main.jp.ac.ksu.mori.mvc.model.NodeModel;

public class NodeView extends JComponent{

    private final Font FONT = new Font(Font.SERIF,Font.PLAIN,12);
    private final int PADDING = 3;
    private final int START = 0;
    private String name;
    private Function<Integer,Integer> addPadding = x -> x + (2 * PADDING);
    private BranchView branchView;
    private NodeModel nodeModel;

    public NodeView(){
        super.setFont(FONT);
    }

    public NodeView(String name,int startX,int startY,NodeModel nodeModel){
        super.setFont(FONT);
        this.name = name;
        this.nodeModel = nodeModel;
        super.setBounds(startX,startY,addPadding.apply(getFontWidth()),addPadding.apply(getFontHeight()));
    }
    
    private int getFontHeight(){
        return getFontMetrics(getFont()).getAscent() + getFontMetrics(getFont()).getDescent();
    }

    private int getFontWidth(){
        char[] chars = this.name.toCharArray();
        return  getFontMetrics(getFont()).charsWidth(this.name.toCharArray(),START,chars.length);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawString(this.name,PADDING,getFontHeight());	
        g.drawRect(START,START,getWidth(),getHeight());
    }

    public void updateNodeView(int startX,int startY){
        super.setLocation(startX,startY);
        super.repaint();

        // if(this.branchView != null){
        //     Point childPoint = this.getLeftMidPoint();
        //     Point parentPoint = this.nodeModel.getParent().getNodeView().getRightMidPoint();
        //     this.branchView.updateBranchView(
        //         (int)parentPoint.getX(), (int)parentPoint.getY(),
        //         (int)childPoint.getX(), (int)childPoint.getY()
        //     );
        // }
    }

    public void setBranchView(BranchView branchView){
        this.branchView = branchView;
    }

    public BranchView getBranchView(){
        return this.branchView;
    }

    public NodeModel getNodeModel(){
        return this.nodeModel;
    }


    public Point getTopLeftPoint(){
        return  new Point(getX(),getY());
    }

    public Point getTopRightPoint(){
        return  new Point(getX() + getWidth(),getY());
    }

    public Point getButtomLeftPoint(){
        return  new Point(getX(),getY() + getHeight());
    }

    public Point getButtomRightPoint(){
        return  new Point(getX() + getWidth(),getY() + getHeight());
    }
    
    public Point getTopMidPoint(){
        return  new Point(getX() + getWidth()/2,getY());
    }

    public Point getButtomMidPoint(){
        return  new Point(getX() + getWidth()/2,getY() + getHeight());
    }

    public Point getLeftMidPoint(){
        return  new Point(getX() ,getY() + getHeight()/2);
    }

    public Point getRightMidPoint(){
        return  new Point(getX() + getWidth() ,getY() + getHeight()/2);
    }

    @Override
    public String toString() {
        return "Class:NodeView[name=" + this.name +
           ", x=" + getX() +
           ", y=" + getY() +
           ", width=" + getWidth() +
           ", height=" + getHeight() + "]";
    }
}
