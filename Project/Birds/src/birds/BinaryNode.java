/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birds;

/**
 *
 * @author James
 */

class BinaryNode < T > implements BinaryNodeInterface < T > {
    private T data;
    private BinaryNode <T> parent;
    private BinaryNode < T > left;
    private BinaryNode < T > right;
    
    public BinaryNode () {
        this (null); // call next constructor
    } // end default constructor
    public BinaryNode (T dataPortion) {
        this (dataPortion, null, null); // call next constructor
    } // end constructor
    public BinaryNode (T dataPortion, BinaryNode < T > leftChild, BinaryNode < T > rightChild) {
        data = dataPortion;
        left = leftChild;
        right = rightChild;
    } // end constructor
    public T getData () {
        return data;
    } // end getData
    public void setData (T newData) {
        data = newData;
    } // end setData
    
    //Parent
    public BinaryNodeInterface < T > getParent() {
        return parent;
    } // end getLeftChild
    public void setParent (BinaryNodeInterface < T > parentNode) {
        parent = (BinaryNode < T > ) parentNode;
    } // end setLeftChild
    public boolean hasParent() {
        return parent != null;
    } // end hasLeftChild
    
    
    public BinaryNodeInterface < T > getLeftChild () {
        return left;
    } // end getLeftChild
    public void setLeftChild (BinaryNodeInterface < T > leftChild) {
        left = (BinaryNode < T > ) leftChild;
    } // end setLeftChild
    public boolean hasLeftChild () {
        return left != null;
    } // end hasLeftChild
     public BinaryNodeInterface < T > getRightChild () {
        return right;
    } // end getLeftChild
    public void setRightChild (BinaryNodeInterface < T > rightChild) {
        right = (BinaryNode < T > ) rightChild;
    } // end setLeftChild
    public boolean hasRightChild () {
        return right != null;
    } // end hasLeftChild
    public boolean isLeaf () {
        return (left == null) && (right == null);
    } // end isLeaf
    // Implementations of getRightChild, setRightChild, and hasRightChild are analogous to
    // their left-child counterparts.
    public BinaryNodeInterface < T > copy () {
        BinaryNode < T > newRoot = new BinaryNode < T > (data);
        if (left != null)
            newRoot.left = (BinaryNode < T > ) left.copy ();
        if (right != null)
            newRoot.right = (BinaryNode < T > ) right.copy ();
        return newRoot;
    } // end copy
    public int getHeight () {
        return getHeight (this); // call private getHeight
    } // end getHeight
    private int getHeight (BinaryNode < T > node) {
        int height = 0;
        if (node != null)
            height = 1 + Math.max (getHeight (node.left), getHeight (node.right));
        return height;
    } // end getHeight
    public int getNumberOfNodes () {
        int leftNumber = 0;
        int rightNumber = 0;
        if (left != null)
            leftNumber = left.getNumberOfNodes ();
        if (right != null)
            rightNumber = right.getNumberOfNodes ();
        return 1 + leftNumber + rightNumber;
    } // end getNumberOfNodes
} // end BinaryNode

