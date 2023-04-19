package stackproject;

import stackproject.structures.LinkedStack;
import stackproject.structures.Stack;
import stackproject.util.Reader;

/**
 *
 * @author Luigi Salcedo
 */
public class StackProject 
{
    private static final Reader read = new Reader();
    
    public static void main(String[] args) 
    {
        int op;
        do
        {
            System.out.println("Proyecto de pilas. Seleccione una función a ejecutar: ");
            System.out.println();
            System.out.println("[1]. Convertir de decimal a binario.");
            System.out.println("[2]. Verificar palíndromo.");
            System.out.println();
            System.out.println("[3]. Salir");
            System.out.println();
            System.out.print("Seleccionar: "); op = read.nextInt();
            switch(op)
            {
                case 1: 
                    decimalToBinary();
                    break;
                case 2: 
                    isPalindrome();
                    break;
                case 3: break;
                default:
                    System.out.println("\nOpción seleccionada no valida . . . \n");
            }
        }while(op != 3);
    }
    
    public static void decimalToBinary()
    {
        System.out.print("\nIngrese el número en base 10 a convertir: "); long n = read.nextLong();
        long temp = n;
        
        Stack<Byte> pila = new LinkedStack();
        
        while(n >= 2)
        {
            pila.push((byte)(n % 2));
            n /= 2;
        }
        pila.push((byte)(n));
        
        StringBuilder sb = new StringBuilder();
        
        while(!pila.isEmpty())
        {
            sb.append(pila.pop());
        }
        
        System.out.println("\nEl número " + temp + " en binario es: " + sb.toString() + "\n");
    }
    
    public static void isPalindrome()
    {
        System.out.println("\nIngrese la expresión a verificar, recuerde que esta debe estar dividida por un '&' en el momento que se hace espejo.");
        System.out.print("\nExpresión: "); String cadena = read.nextStringNotVoid().trim();
        
        if(!cadena.contains("&"))
        {
            System.out.println("\nLa expresión no cumple la condición especificada\n");
            return;
        }
        
        String [] subCadenas = cadena.split("&");
        
        Stack<Character> primeraMitad = new LinkedStack();
        Stack<Character> segundaMitad = new LinkedStack();
        
        for(Character c : subCadenas[0].toCharArray())
        {
            primeraMitad.push(c);
        }
        
        for(int i = subCadenas[1].toCharArray().length - 1; i >= 0; i--)
        {
            segundaMitad.push(subCadenas[1].charAt(i));
        }
        
        System.out.println("\nLa expresión " + verifyPalindrome(primeraMitad, segundaMitad) + " palindroma.\n");
        
    }
    
    public static String verifyPalindrome(Stack<Character> primeraMitad, Stack<Character> segundaMitad)
    {
        if(primeraMitad.size() != segundaMitad.size())
        {
            return "no es";
        }
        
        while(!primeraMitad.isEmpty())
        {
            if(!primeraMitad.pop().equals(segundaMitad.pop()))
            {
                return "no es";
            }
        }
        
        return "es";
    }
}
