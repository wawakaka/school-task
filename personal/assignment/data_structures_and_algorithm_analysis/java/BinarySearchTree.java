public class BinarySearchTree {
    
    private static class Node {
        int data;
        Node left;
        Node right;
        
        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    
    private Node root;
    
    public BinarySearchTree() {
        this.root = null;
    }
    
    // ==================== INSERT OPERATIONS ====================
    
    /**
     * Public method to insert a value into the BST
     * Time Complexity: O(h) where h is the height of the tree
     * Average: O(log n), Worst: O(n) for skewed tree
     */
    public void insert(int data) {
        root = insertRecursive(root, data);
    }
    
    /**
     * Recursive helper method to insert a value
     */
    private Node insertRecursive(Node current, int data) {
        // Base case: found the position to insert
        if (current == null) {
            return new Node(data);
        }
        
        // Recursively insert in left or right subtree
        if (data < current.data) {
            current.left = insertRecursive(current.left, data);
        } else if (data > current.data) {
            current.right = insertRecursive(current.right, data);
        }
        // If data == current.data, we don't insert duplicates
        
        return current;
    }
    
    /**
     * Iterative insert method (alternative approach)
     */
    public void insertIterative(int data) {
        Node newNode = new Node(data);
        
        if (root == null) {
            root = newNode;
            return;
        }
        
        Node current = root;
        Node parent = null;
        
        while (current != null) {
            parent = current;
            if (data < current.data) {
                current = current.left;
            } else if (data > current.data) {
                current = current.right;
            } else {
                // Duplicate value, don't insert
                return;
            }
        }
        
        // Insert the new node
        if (data < parent.data) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }
    
    // ==================== SEARCH OPERATIONS ====================
    
    /**
     * Search for a value in the BST
     * Time Complexity: O(h) where h is the height
     */
    public boolean search(int data) {
        return searchRecursive(root, data);
    }
    
    private boolean searchRecursive(Node current, int data) {
        // Base case: reached null or found the value
        if (current == null) {
            return false;
        }
        
        if (data == current.data) {
            return true;
        }
        
        // Search in left or right subtree
        return data < current.data 
            ? searchRecursive(current.left, data)
            : searchRecursive(current.right, data);
    }
    
    /**
     * Iterative search (alternative approach)
     */
    public boolean searchIterative(int data) {
        Node current = root;
        
        while (current != null) {
            if (data == current.data) {
                return true;
            }
            current = data < current.data ? current.left : current.right;
        }
        
        return false;
    }
    
    // ==================== DELETE OPERATIONS ====================
    
    /**
     * Delete a value from the BST
     * Time Complexity: O(h)
     */
    public void delete(int data) {
        root = deleteRecursive(root, data);
    }
    
    private Node deleteRecursive(Node current, int data) {
        // Base case: value not found
        if (current == null) {
            return null;
        }
        
        // Find the node to delete
        if (data < current.data) {
            current.left = deleteRecursive(current.left, data);
        } else if (data > current.data) {
            current.right = deleteRecursive(current.right, data);
        } else {
            // Found the node to delete
            
            // Case 1: Node has no children (leaf node)
            if (current.left == null && current.right == null) {
                return null;
            }
            
            // Case 2: Node has one child
            if (current.left == null) {
                return current.right;
            }
            if (current.right == null) {
                return current.left;
            }
            
            // Case 3: Node has two children
            // Find the inorder successor (smallest in the right subtree)
            int smallestValue = findMin(current.right);
            current.data = smallestValue;
            // Delete the inorder successor
            current.right = deleteRecursive(current.right, smallestValue);
        }
        
        return current;
    }
    
    // ==================== UTILITY METHODS ====================
    
    /**
     * Find the minimum value in a subtree
     */
    private int findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.data;
    }
    
    /**
     * Find the maximum value in a subtree
     */
    private int findMax(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node.data;
    }
    
    /**
     * Get the minimum value in the tree
     */
    public Integer getMin() {
        if (root == null) {
            return null;
        }
        return findMin(root);
    }
    
    /**
     * Get the maximum value in the tree
     */
    public Integer getMax() {
        if (root == null) {
            return null;
        }
        return findMax(root);
    }
    
    /**
     * Calculate the height of the tree
     * Height is the number of edges in the longest path from root to leaf
     */
    public int height() {
        return heightRecursive(root);
    }
    
    private int heightRecursive(Node node) {
        if (node == null) {
            return -1; // Height of empty tree is -1
        }
        
        int leftHeight = heightRecursive(node.left);
        int rightHeight = heightRecursive(node.right);
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    /**
     * Count the number of nodes in the tree
     */
    public int size() {
        return sizeRecursive(root);
    }
    
    private int sizeRecursive(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + sizeRecursive(node.left) + sizeRecursive(node.right);
    }
    
    /**
     * Check if the tree is empty
     */
    public boolean isEmpty() {
        return root == null;
    }
    
    // ==================== TRAVERSAL METHODS ====================
    
    /**
     * Inorder Traversal (Left -> Root -> Right)
     * Produces sorted output for BST
     */
    public void inorderTraversal() {
        System.out.print("Inorder: ");
        inorderRecursive(root);
        System.out.println();
    }
    
    private void inorderRecursive(Node node) {
        if (node != null) {
            inorderRecursive(node.left);
            System.out.print(node.data + " ");
            inorderRecursive(node.right);
        }
    }
    
    /**
     * Preorder Traversal (Root -> Left -> Right)
     */
    public void preorderTraversal() {
        System.out.print("Preorder: ");
        preorderRecursive(root);
        System.out.println();
    }
    
    private void preorderRecursive(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorderRecursive(node.left);
            preorderRecursive(node.right);
        }
    }
    
    /**
     * Postorder Traversal (Left -> Right -> Root)
     */
    public void postorderTraversal() {
        System.out.print("Postorder: ");
        postorderRecursive(root);
        System.out.println();
    }
    
    private void postorderRecursive(Node node) {
        if (node != null) {
            postorderRecursive(node.left);
            postorderRecursive(node.right);
            System.out.print(node.data + " ");
        }
    }
    
    /**
     * Level Order Traversal (Breadth-First)
     */
    public void levelOrderTraversal() {
        if (root == null) {
            System.out.println("Level Order: (empty tree)");
            return;
        }
        
        System.out.print("Level Order: ");
        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.data + " ");
            
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        System.out.println();
    }
    
    // ==================== VALIDATION ====================
    
    /**
     * Check if the tree is a valid BST
     */
    public boolean isValidBST() {
        return isValidBSTRecursive(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private boolean isValidBSTRecursive(Node node, int min, int max) {
        if (node == null) {
            return true;
        }
        
        // Check if current node violates BST property
        if (node.data <= min || node.data >= max) {
            return false;
        }
        
        // Check left and right subtrees
        return isValidBSTRecursive(node.left, min, node.data) &&
               isValidBSTRecursive(node.right, node.data, max);
    }
    
    // ==================== MAIN METHOD - DEMONSTRATION ====================
    
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        
        System.out.println("=== Binary Search Tree Demo ===\n");
        
        // Insert values
        System.out.println("Inserting: 50, 30, 70, 20, 40, 60, 80");
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);
        
        // // Display tree info
        // System.out.println("\n--- Tree Information ---");
        // System.out.println("Size: " + bst.size());
        // System.out.println("Height: " + bst.height());
        // System.out.println("Min value: " + bst.getMin());
        // System.out.println("Max value: " + bst.getMax());
        // System.out.println("Is valid BST: " + bst.isValidBST());
        
        // Traversals
        System.out.println("\n--- Traversals ---");
        bst.inorderTraversal();      // Sorted order
        bst.preorderTraversal();
        bst.postorderTraversal();
        bst.levelOrderTraversal();
        
        // // Search operations
        // System.out.println("\n--- Search Operations ---");
        // System.out.println("Search 40: " + bst.search(40));
        // System.out.println("Search 25: " + bst.search(25));
        // System.out.println("Search 80: " + bst.search(80));
        
        // // Delete operations
        // System.out.println("\n--- Delete Operations ---");
        // System.out.println("Deleting 20 (leaf node)");
        // bst.delete(20);
        // bst.inorderTraversal();
        
        // System.out.println("\nDeleting 30 (node with two children)");
        // bst.delete(30);
        // bst.inorderTraversal();
        
        // System.out.println("\nDeleting 50 (root node)");
        // bst.delete(50);
        // bst.inorderTraversal();
        
        // System.out.println("\nFinal tree size: " + bst.size());
        // System.out.println("Final tree height: " + bst.height());
    }
}