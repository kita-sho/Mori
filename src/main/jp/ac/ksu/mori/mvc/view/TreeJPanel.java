package src.main.jp.ac.ksu.mori.mvc.view;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JPanel;

public class TreeJPanel extends JPanel {


    public TreeJPanel(){
        super();
        setLayout(null);
        setBackground(Color.WHITE);
        setBounds(0,0,1000,800);
    }

    public void addComponent(Component component){
        add(component);
    }
    
}
