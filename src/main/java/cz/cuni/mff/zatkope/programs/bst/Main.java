package cz.cuni.mff.zatkope.programs.bst;
import cz.cuni.mff.zatkope.toolkit.BinarySearchTree;

public class Main {

    public static void main(String[] args){
        BinarySearchTree tree = new BinarySearchTree();

        try {
            for (String arg : args) {
                tree.insert(Integer.parseInt(arg));
            }

            for (Integer data : tree){
                System.out.println(data);
            }
        } catch (NumberFormatException e) {
            System.out.println("INPUT ERROR");
        }
    }

}
