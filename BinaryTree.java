import java.util.Scanner;

public class BinaryTree {
    private Node root;


    private class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    public void fillTreeFromInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите числа для заполнения дерева (через пробел):");
        String input = scanner.nextLine();
        String[] numbers = input.split(" ");
        for (String number : numbers) {
            int value = Integer.parseInt(number);
            insert(value);
        }
    }

    public void insert(int value) {
        root = insertNode(root, value);
    }

    private Node insertNode(Node currentNode, int value) {
        if (currentNode == null) {
            return new Node(value);
        }
        if (value < currentNode.data) {
            currentNode.left = insertNode(currentNode.left, value);
        } else if (value > currentNode.data) {
            currentNode.right = insertNode(currentNode.right, value);
        }
        return currentNode;
    }

    public boolean search(int value) {
        return searchNode(root, value);
    }

    private boolean searchNode(Node currentNode, int value) {
        if (currentNode == null) {
            return false;
        }
        if (value == currentNode.data) {
            return true;
        } else if (value < currentNode.data) {
            return searchNode(currentNode.left, value);
        } else {
            return searchNode(currentNode.right, value);
        }
    }

    public void delete(int value) {
        root = deleteNode(root, value);
    }

    private Node deleteNode(Node currentNode, int value) {
        if (currentNode == null) {
            return null;
        }
        if (value == currentNode.data) {
            if (currentNode.left == null && currentNode.right == null) {
                return null;
            } else if (currentNode.left == null) {
                return currentNode.right;
            } else if (currentNode.right == null) {
                return currentNode.left;
            } else {
                int smallestValue = findSmallestValue(currentNode.right);
                currentNode.data = smallestValue;
                currentNode.right = deleteNode(currentNode.right, smallestValue);
                return currentNode;
            }
        } else if (value < currentNode.data) {
            currentNode.left = deleteNode(currentNode.left, value);
            return currentNode;
        } else {
            currentNode.right = deleteNode(currentNode.right, value);
            return currentNode;
        }
    }

    private int findSmallestValue(Node currentNode) {
        return currentNode.left == null ? currentNode.data : findSmallestValue(currentNode.left);
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.fillTreeFromInput();
        // далее можно использовать дерево для выполнения других операций
    }
}

