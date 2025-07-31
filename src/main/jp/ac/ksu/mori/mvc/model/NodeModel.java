package src.main.jp.ac.ksu.mori.mvc.model;

import java.util.List;
import java.util.ArrayList;
import src.main.jp.ac.ksu.mori.tree.Node;

/*
 * 葉のデータを管理するクラスです
 */
public class NodeModel {

    private String name;
    private Integer id;
    private List<Node> parent;
    private List<Integer> children;
    private Integer depth;

    public NodeModel(){
        this.name = new String();
        this.id = null;
        this.parent = new ArrayList<Node>();
        this.children = new ArrayList<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void addParent(Node parent){
        this.parent.add(parent);
    }

    public void setChildren(List<Integer> children) {
        this.children = children;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public String getName(){
        return this.name;
    }

    public Integer getId(){
        return this.id;
    }

    public List<Node> getParent(){
        return this.parent;
    }

    public List<Integer> getChildren(){
        return this.children;
    }

    public Integer getDepth() {
        return this.depth;
    }

    public void addChild(Integer childId) {
        this.children.add(childId);
    }

    public List<Integer> sortChildren() {
        this.children.sort((a,b)->a.compareTo(b));
        return this.children;
    }

}
