package cz.cuni.mff.zatkope.toolkit;

import java.util.Iterator;

public class BinarySearchTree implements Iterable<Integer> {
    private BinaryTreeNode root;

    public BinarySearchTree(){}

    public BinarySearchTree(BinaryTreeNode root){
        this.root = root;
    }

    private void insertNode(BinaryTreeNode node){
        int nodeData = node.data;
        BinaryTreeNode current = root;

        if(current == null)
            this.root = node;

        while (current != null){
            if(nodeData <= current.data){
                if(current.left == null){
                    current.left = node;
                    node.parent = current;
                    break;
                } else {
                    current = current.left;
                }
            } else {
                if(current.right == null) {
                    current.right = node;
                    node.parent = current;
                    break;
                } else {
                    current = current.right;
                }
            }
        }
    }

    private void removeNode(){}

    private BinaryTreeNode findSuccessor(BinaryTreeNode node){
        if(node == null) return null;

        if(node.right != null){
            BinaryTreeNode current = node.right;
            while (current.left != null){
                current = current.left;
            }
            return current;
        }

        BinaryTreeNode current = node;
        while (current.parent != null && current.parent.right == current){
            current = current.parent;
        }

        return current.parent;
    }

    private BinaryTreeNode findMinimum(){
        BinaryTreeNode current = root;
        while (current.left != null)
            current = current.left;

        return current;
    }

    public void insert(int data){
        insertNode(new BinaryTreeNode(data));
    }

    public Iterator<Integer> iterator(){
        return new Iterator<Integer>() {
            BinaryTreeNode current = findMinimum();
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Integer next() {
                BinaryTreeNode last = current;
                current = findSuccessor(current);
                return last.data;
            }
        };
    }
}
