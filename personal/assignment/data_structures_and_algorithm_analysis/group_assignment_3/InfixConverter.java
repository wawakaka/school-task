import java.util.*;

public class InfixConverter {
    
    // Cek urutan kekuatan operator
    static int precedence(char ch) {
        switch (ch) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
        }
        return -1;
    }
    
    // 1. Validasi infix
    public static boolean validateInfix(String expression) {
        // Hapus spasi untuk validasi
        String exp = expression.replaceAll("\\s+", "");
        
        // Validasi karakter yang diizinkan
        for (char c : exp.toCharArray()) {
            if (!isValidCharacter(c)) {
                System.out.println("Error: Karakter '" + c + "' tidak valid");
                return false;
            }
        }
        
        // Validasi kurung seimbang
        if (!checkBalancedParentheses(exp)) {
            System.out.println("Error: Kurung tidak seimbang");
            return false;
        }
        
        // Validasi posisi operator
        if (!checkOperatorPosition(exp)) {
            System.out.println("Error: Posisi operator tidak valid");
            return false;
        }
        
        return true;
    }
    
    // Cek karakter
    private static boolean isValidCharacter(char c) {
        return Character.isDigit(c) || 
        c == '+' || c == '-' || c == '*' || c == '/' || 
        c == '(' || c == ')';
    }
    
    // Cek kurung seimbang
    private static boolean checkBalancedParentheses(String exp) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : exp.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
    
    // Cek posisi operator
    private static boolean checkOperatorPosition(String exp) {
        char[] chars = exp.toCharArray();
        
        for (int i = 0; i < chars.length; i++) {
            char current = chars[i];
            
            if (isOperator(current)) {
                // Operator tidak boleh di awal atau akhir
                if (i == 0 || i == chars.length - 1) {
                    return false;
                }
                
                char prev = chars[i - 1];
                char next = chars[i + 1];
                
                // Tidak boleh dua operator berturut-turut
                if (isOperator(prev) || isOperator(next)) {
                    return false;
                }
                
                // Setelah '(' tidak boleh operator (kecuali '(' lagi)
                if (prev == '(' && current != '(') {
                    return false;
                }
                
                // Sebelum ')' tidak boleh operator
                if (next == ')' && isOperator(current)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    // Cek Operator
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    
    // Konversi ke Postfix
    static String toPostfix(String exp) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if (Character.isDigit(c)) result.append(c);
            else if ("+-*/".indexOf(c) != -1) {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) result.append(stack.pop());
        return result.toString();
    }
    
    // Konversi ke Prefix (Balik infix -> Postfix-kan -> Balik lagi)
    static String toPrefix(String exp) {
        StringBuilder rev = new StringBuilder(exp).reverse();
        String postfix = toPostfix(rev.toString());
        String revesedPostfix = new StringBuilder(postfix).reverse().toString();
        return revesedPostfix;
    }
    
    // Hitung hasil operasi (Evaluasi Postfix)
    static double evaluatePostfix(String exp) {
        Stack<Double> stack = new Stack<>();
        for (char c : exp.toCharArray()) {
            if (Character.isDigit(c)) stack.push((double) (c - '0'));
            else {
                double val2 = stack.pop();
                double val1 = stack.pop();
                switch (c) {
                    case '+': stack.push(val1 + val2); break;
                    case '-': stack.push(val1 - val2); break;
                    case '*': stack.push(val1 * val2); break;
                    case '/': stack.push(val1 / val2); break;
                }
            }
        }
        return stack.pop();
    }
    
    // Main program   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String currentInfix = "";
        
        System.out.print("\nMasukkan notasi infix: ");
        currentInfix = scanner.nextLine();
        
        System.out.println("\nExpression: " + currentInfix);
        if (validateInfix(currentInfix)) {
            System.out.println("Valid");
            String post = toPostfix(currentInfix);
            String pre = toPrefix(currentInfix);
            System.out.println("Postfix: " + post);
            System.out.println("Prefix: " + pre);
            System.out.println("Hasil Operasi: " + evaluatePostfix(post));
        } else {
            System.out.println("Invalid");
        }
        
        scanner.close();
    }
}