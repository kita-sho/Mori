package src.main.jp.ac.ksu.mori.mvc.view;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.util.function.BiFunction;
import java.awt.Point;

public class BranchView extends JComponent {

    private int startX;
    private int startY;
    private int endX;
    private int endY;

    private BiFunction<Integer,Integer,Integer> calculateSize = 
        (start,end) -> (Math.abs(start - end) == 0)? 1 : Math.abs(end - start);
    
    public BranchView(int startX,int startY,int endX,int endY){
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        super.setBounds(Math.min(startX,endX),Math.min(startY,endY), calculateSize.apply(startX,endX), calculateSize.apply(startY,endY));
    }

    public void setBranch(int startX,int startY,int endX,int endY){
        super.setBounds(startX,startY, calculateSize.apply(startX,endX), calculateSize.apply(startY,endY));
        super.repaint();
    }

    public Point getStartPoint(){
        return new Point(getX(),getY());
    }

    public Point getEndPoint(){
        return new Point(getX()+getWidth(),getY()+getHeight());
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int x1 = (this.startX <= this.endX) ? 0 : getWidth();
        int y1 = (this.startY <= this.endY) ? 0 : getHeight();
        int x2 = (this.startX <= this.endX) ? getWidth() : 0;
        int y2 = (this.startY <= this.endY) ? getHeight() : 0;
        g.drawLine(x1,y1,x2,y2);
    }

    @Override
    public String toString() {
        return "class:Branch[" +
            "x=" + getX() +
            ", y=" + getY() +
            ", width=" + getWidth() +
            ", height=" + getHeight() +
            ", startPoint=" + getStartPoint().toString() +
            ", endPoint=" + getEndPoint().toString() +
            "]";
    }
    
}

