package src.main.jp.ac.ksu.mori.mvc.view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.FontMetrics;
import java.util.function.Function;
import java.awt.Dimension;


/*
 * ダイアログを管理するクラスです
 */
public class TreeDialog extends JDialog {

    private final Font FONT = new Font(Font.SERIF,Font.ITALIC,12);
    private final int PADDING = 25;
    private Function<Integer,Integer> addPadding = size -> size + (2 * PADDING);

    public TreeDialog(TreeFrame frame,String string){

        super(frame,"",true);
        super.setFont(this.FONT);
        super.setSize(this.calculateFontSize(string));
        super.add(new JLabel(string));
        super.pack();
        super.setLocationRelativeTo(frame);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private Dimension calculateFontSize(String string){
        char[] chras = string.toCharArray();
        FontMetrics metrics = super.getFontMetrics(super.getFont());
        int width = addPadding.apply(metrics.charsWidth(chras, 0, chras.length));
        int height = addPadding.apply(metrics.getMaxAscent() + metrics.getMaxDescent());
        System.out.println(width+":"+height);
        
        return new Dimension(width,height);
    }

    
}
