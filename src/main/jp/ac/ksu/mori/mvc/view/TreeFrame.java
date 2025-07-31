package src.main.jp.ac.ksu.mori.mvc.view;

import javax.swing.JFrame;
import java.awt.Color;


/*
 * 画面のフレームを管理するクラスです
 */
public class TreeFrame extends JFrame {

    private TreeJPanel treeJPanel;

    public TreeFrame(){
        super();
        super.setTitle("Mori");
        super.setLayout(null);
        super.setBounds(500,400,1000,800);
        super.getContentPane().setBackground(Color.WHITE);
        super.setBackground(Color.WHITE);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }

    public void setPanel(TreeJPanel treeJPanel){
        this.treeJPanel = treeJPanel;
        this.add(treeJPanel);
    }

    public TreeJPanel getPanel(){
        return this.treeJPanel;
    }
}
