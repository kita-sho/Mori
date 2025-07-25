package src.main.jp.ac.ksu.mori.tree;

import src.main.jp.ac.ksu.mori.mvc.view.BranchView;


public class Branch {

    private BranchView branchView;

    public void setBranchView(int startX,int startY,int endX,int endY){
        this.branchView = new BranchView(startX, startY, endX, endY);
    }

    public BranchView getBranchView(){
        return this.branchView;
    }

}

   