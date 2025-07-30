package src.main.jp.ac.ksu.mori.tree;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class TreeTimer {


    private List<Runnable> tasks;

    public TreeTimer(){
        this.tasks = new ArrayList<Runnable>();
    }

    public void add(Runnable runnable){
        this.tasks.add(runnable);
    }

    public List<Runnable> getTasks(){
        return this.tasks;
    }

    public void timerStart(){
        Timer timer = new Timer(200,null);

        timer.addActionListener( _ -> {
            if (!tasks.isEmpty()) {
                Runnable task = tasks.remove(0);
                SwingUtilities.invokeLater(task); 
            } else {
                timer.stop();
            }
        });
        timer.start();
    }
    
}
