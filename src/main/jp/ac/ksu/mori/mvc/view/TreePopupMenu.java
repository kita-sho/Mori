package src.main.jp.ac.ksu.mori.mvc.view;

import javax.swing.JPopupMenu;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import src.main.jp.ac.ksu.mori.mvc.model.TreeLoader;
import src.main.jp.ac.ksu.mori.mvc.model.Model;
import src.main.jp.ac.ksu.mori.tree.Forest;
import src.main.jp.ac.ksu.mori.utility.TreeTimer;

/*
 * Popupメニューを管理するクラスです
 */
public class TreePopupMenu extends JPopupMenu {

    private Model model;
    private View view;

	public TreePopupMenu(Model model,View view) {
		super();
		this.model = model;
        this.view = view;
		this.initialize();
	}

	private final void initialize() {
        JMenuItem openFile = new JMenuItem("ファイルを選択");
        openFile.addActionListener( _ -> {

            this.view.getTreeJPanel().removeAll();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(this);


            try (TreeLoader treeLoader = new TreeLoader(fileChooser.getSelectedFile())) {
                this.model.setForest(treeLoader.load(new Forest()));
            } catch (IOException e) {
                System.out.println(e);
            }

            this.view.paintInitialNode();
        });
        addMenuItem(openFile);

        JMenuItem aligin = new JMenuItem("整列");
        aligin.addActionListener( _ -> {

            this.view.aliginTree();
            TreeTimer treeTimer = new TreeTimer();
            this.model.getForest().getForest().forEach(tree ->{
                treeTimer.getTasks().addAll(tree.getTreeTimer().getTasks());
            });
            treeTimer.timerStart();
        });
        addMenuItem(aligin);

    }

	public void addMenuItem(JMenuItem jMenuItem) {
		this.add(jMenuItem);
	}

}
