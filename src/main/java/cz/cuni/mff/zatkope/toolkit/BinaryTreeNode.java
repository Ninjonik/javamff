package cz.cuni.mff.zatkope.toolkit;

public class BinaryTreeNode {
    public int data;
    public BinaryTreeNode left;
    public BinaryTreeNode right;
    public BinaryTreeNode parent;

    public BinaryTreeNode(int data, BinaryTreeNode left, BinaryTreeNode right, BinaryTreeNode parent){
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public BinaryTreeNode(int data) {
        this.data = data;
    }
}
