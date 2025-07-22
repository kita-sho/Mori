package src.main.jp.ac.ksu.mori.mvc;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BranchModel {

    private Map<Integer,List<Integer>>  parentAndChildren;

    public void branchModel(){
        this.parentAndChildren = new HashMap<Integer,List<Integer>>();
    }

    public void addBranch(Integer parentId, Integer childId) {
        this.parentAndChildren.computeIfAbsent(parentId, k -> new ArrayList<>()).add(childId);
    }
    
}
