package edu.course.eventplanner.service;
//I used AI to guide me in creating this class
import edu.course.eventplanner.model.Guest;
import java.util.List;

public class BSTseating {
    class Node {
        int tableNum;
        List<Guest> guests;
        Node left, right;

        public Node(int tableNum, List<Guest> guests) {
            this.tableNum = tableNum;
            this.guests = guests;
            left = right = null;
        }
    }
    public class BinarySearchTree {
        Node root;

        public BinarySearchTree() {
            root = null;
        }

        // Insertion operation
        public void insert(int tableNum, List<Guest> guests) {
            root = insertRec(root, tableNum, guests);
        }
        Node insertRec(Node node, int tableNum, List<Guest> guests){
            if(node == null){
                return new Node(tableNum, guests);
            }
            if(tableNum < node.tableNum){
                node.left =  insertRec(node.left, tableNum, guests);
            } if(tableNum > node.tableNum){
                node.right =  insertRec(node.right, tableNum, guests);
            } if (tableNum == node.tableNum){
                node.guests.addAll(guests);
            }
            return node;
        }
    }
}
