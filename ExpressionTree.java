import java.util.Stack;

class Node {
    String value;
    Node left, right;
    
    public Node(String item) { 
        value = item; 
        left = right = null; 
    }
}

public class ExpressionTree {

  
    public static Node buildTree(String postfix) {
        Stack<Node> stack = new Stack<>();
        
        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);
            
         
            if (c == ' ') continue;
            
         
            if (Character.isLetterOrDigit(c)) {
                stack.push(new Node(String.valueOf(c)));
            } 
          
            else {
                Node newNode = new Node(String.valueOf(c));
                
             
                if (!stack.isEmpty()) newNode.right = stack.pop();
                if (!stack.isEmpty()) newNode.left = stack.pop();
                
                stack.push(newNode);
            }
        }
        
       
        return stack.isEmpty() ? null : stack.pop();
    }

    
    public static int evaluate(Node root) {
        if (root == null) return 0;
        
       
        if (root.left == null && root.right == null) {
            return Integer.parseInt(root.value);
        }
        
       
        int leftVal = evaluate(root.left);
        int rightVal = evaluate(root.right);
        
       
        switch (root.value) {
            case "+": return leftVal + rightVal;
            case "-": return leftVal - rightVal;
            case "*": return leftVal * rightVal;
            case "/": return leftVal / rightVal;
            default: return 0;
        }
    }

    
    public static void printInfix(Node root) {
        if (root != null) {
        
            if (root.left != null || root.right != null) {
                System.out.print("(");
            }
            
            printInfix(root.left);
            System.out.print(root.value);
            printInfix(root.right);
            
          
            if (root.left != null || root.right != null) {
                System.out.print(")");
            }
        }
    }

    public static void main(String[] args) {
        String postfix = "53+82-*";
        Node root = buildTree(postfix);
        
        printInfix(root);
        System.out.println("\nResult: " + evaluate(root));
    }
}

