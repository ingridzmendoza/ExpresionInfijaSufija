/*
El siguiente algoritmo generara una string en orden de sufijo. Procesamos la expresion infija de izquierda
a derecha. Para cada token, pueden surgir cuatro casos:

>> Si el token actual es un paréntesis de apertura, '(', empújelo hacia la stack.
>> Si el token actual es un paréntesis de cierre, ')', extraiga elementos del  stack hasta que se elimine el
corchete de apertura correspondiente '('. Agregue cada operador al final de la expresión de posfijo.
>> Si el token actual es un operando, agréguelo al final de la expresión de posfijo.
>> Si el token actual es un operador, colóquelo en la parte superior del stack. Antes de hacer eso,
primero sacar del stack hasta que tengamos un operador de menor precedencia en la parte superior, o la stack
se vacíe. Agregue cada operador al final de la expresión sufijo.
>> Finalmente, agregue los operadores restantes en la stack al final de la expresión de posfijo y devuelva la expresión de posfijo
* */

import java.util.Scanner;
import java.util.Stack;

public class ExpresionInfijaASufija {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la Expresion Infija: ");
        String infixExpression = sc.nextLine();

        String postfixExpression = InfijaPostfija(infixExpression);
        System.out.println("La Expresión Posfija es: " + postfixExpression);
    }

    /*METODOS*/
    // metodo de conversion de expresion infija a sufija
    public static String InfijaPostfija(String Infija) {
        Stack<Character> stack = new Stack<>();
        StringBuilder Postfija = new StringBuilder();

        for (char token : Infija.toCharArray()) {
            if (token == '(' || token == '[' || token == '{') {
                stack.push(token);
            } else if (token == ')' || token == ']' || token == '}') {
                //eliminar los parentesis
                while (!stack.isEmpty() && (stack.peek() != '(' && stack.peek() != '[' && stack.peek() != '{')) {
                    Postfija.append(stack.pop());
                }
                stack.pop();
                //si encuentra una letra o digito
            } else if (Character.isLetterOrDigit(token)) {
                Postfija.append(token);
            } else if (Operador(token)) {
                while (!stack.isEmpty() && OrdenOperadores(token) <= OrdenOperadores(stack.peek())) {
                    Postfija.append(stack.pop());
                }
                stack.push(token);
            }
        }

        // agrega los operadores al stack
        for (; !stack.isEmpty(); ) {
            Postfija.append(stack.pop());
            break;
        }
        return Postfija.toString();
        }

        // metodo Operador verifica un operador
        private static boolean Operador(char c) {
            return c == '+' || c == '-' || c == '*' || c == '/';
        }

        // metodo OrdenOperadores verifica la precedencia
        private static int OrdenOperadores(char operador) {
            switch (operador) {
                case '-':
                case '+':
                    return 1;
                case '/':
                case '*':
                    return 2;
                default:
                    return 0;
            }
        }
}
