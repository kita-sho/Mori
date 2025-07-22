package src.main.jp.ac.ksu.mori.mvc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TreeLoader implements AutoCloseable {

    private BufferedReader reader;
    private String line;

    public TreeLoader(String path) throws IOException{
        this.reader = new BufferedReader(new FileReader(path));
    }

    public Forest load(Forest forest) throws IOException{

        while((this.line = this.readLine()) != null){
    
            if(this.line.matches("trees:$")){
                forest = loadForest(forest);
            }
            if (this.line.matches("nodes:")){
                forest = loadNodes(forest);
            }
            if (this.line.matches("branches:")){
                forest = loadBranches(forest);
            }
        }
        return forest;
    }

    @Override
    public void close() throws IOException{
        this.reader.close();
    }

    private String readLine() throws IOException{ 
        return this.reader.readLine();
    }

    private Forest loadForest(Forest forest) throws IOException{
        
        Tree tree = null;
        while(!(this.line = this.readLine()).matches("nodes:$")){
            
            if (this.line.equals( "Object")){
                if (tree != null){
                    forest.addTree(tree);
                }
                tree = new Tree();
            }

            Node node = new Node();
            String[] word  = this.line.split("\\|--");

            int lastIndex = word.length - 1;
            node.getNodeModel().setDepth(lastIndex);
            node.getNodeModel().setName(word[lastIndex].trim());

            if (tree != null){
                tree.addNodeByName(node.getNodeModel().getName(),node);
            }
            if (word.length == 1){
                tree.setStartpoint(node);
            }

        }

        if (tree != null){
            forest.addTree(tree);
        }

        return forest;
    }

    private Forest loadNodes(Forest forest) throws IOException{

        while(!(this.line = this.readLine()).matches("branches:$")){
            String[] word = this.line.split(",");
            Integer number = Integer.parseInt(word[0]);
            String name = word[1].trim();

            
            for(Tree tree:forest.getForest()){
                if (tree.getNodeNameMap().containsKey(name)){
                    if (tree.getNodeNameMap().get(name).getId() == null){
                        Node node = tree.getNodeNameMap().get(name);
                        node.getNodeModel().setId(number);
                        tree.addNodeByNumber(number,node);
                        break;
                    }
                }
            }
        }
        return forest;
    }

    private Forest loadBranches(Forest forest) throws IOException{

        while((this.line = this.readLine()) != null) {

            String[] word = this.line.split(",");
            Integer parent = Integer.parseInt(word[0]);
            Integer child = Integer.parseInt(word[1].trim());

            forest.getForest().forEach(tree -> {
                if (tree.getNodeNumberMap().containsKey(parent)){
                    Node node = tree.getNodeNumberMap().get(parent);
                    node.getNodeModel().addChild(child);
                }
            });
        }
        return forest;
    }

    public static void main(String[] args){
        Forest forest = new Forest();

        try (TreeLoader treeLoader = new TreeLoader("forest.txt")) {
            forest = treeLoader.load(forest);
        } catch (IOException e) {
            System.out.println(e);
        }

        forest.forestTravel();

        // forest.getForest().forEach(tree -> {
        //     Map<Integer, Node> mapInt = tree.getNodeNumberMap();
        //     Map<String, Node> mapString = tree.getNodeNameMap();
        //     Node startNode = tree.getStartNode();
        
        //     System.out.println("mapInt:");
        //     mapInt.forEach((k, v) -> System.out.println("  key=" + k + ", value=" + v));
        
        //     System.out.println("mapString:");
        //     mapString.forEach((k, v) -> System.out.println("  key=" + k + ", value=" + v));
        
        //     System.out.println("startNode:");
        //     System.out.println("  " + startNode);
        // });
        
    }

}

    

