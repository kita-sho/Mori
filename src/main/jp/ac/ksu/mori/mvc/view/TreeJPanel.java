package src.main.jp.ac.ksu.mori.mvc.view;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JPanel;


/*
 * Panelを管理するクラスです
 */
public class TreeJPanel extends JPanel {

    public TreeJPanel(){
        super();
        setLayout(null);
        setBackground(Color.WHITE);
        setBounds(0,0,2000,3000);
    }

    public void addComponent(Component component){
        add(component);
    }
    
}
