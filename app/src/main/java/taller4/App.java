package taller4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {

    public static void main(String[] args) {
        try {
            // Llama a cada función
            System.out.println("Impares hasta 300: " + obtenerImpares(300));
            System.out.println("Fibonacci de 8 términos: " + obtenerFibonacci(8));
         
            Random aleatorio = new Random();
            int veces = aleatorio.nextInt(11) + 20; // Aleatorio entre 20 y 30
            System.out.println("Raíces cuadradas:");
            for (int i = 0; i < veces; i++) {
                System.out.println(obtenerRaizCuadradaAleatoria());
            }
            
            System.out.println("Números pares generados: " + generarNumerosAleatorios(10, 100));
            System.out.println("Suma de 5 números aleatorios: " + sumarNumerosAleatorios(5));
            System.out.println(sorteoLoteria());
            
        } catch (Exception e) {
            System.err.println("Error en el programa: " + e.getMessage());
        }
    }

    /* TODAS LAS FUNCIONES DEBEN LLEVAR CONTROL DE ERRORES, SI EL ENUNCIADO NO LO ESPECIFICA, LO DEBES PONER
     * DE ACUERDO CON TU CRITERIO
     */

    /* 
     * 1. Escriba una función que reciba un entero positivo entre 100 y 500 y retorne en un texto todos los números impares desde 1 
     * hasta ese número separados por comas en grupos de hasta 8 números.
     * 
    */

    public static String obtenerImpares(int limite) {
        if (limite < 100 || limite > 500) {
            throw new IllegalArgumentException("El número debe estar entre 100 y 500.");
        }
        StringBuilder resultado = new StringBuilder();
        int contador = 0;

        for (int i = 1; i <= limite; i += 2) {
            resultado.append(i).append(", ");
            contador++;
            if (contador == 8) {
                resultado.append("\n");
                contador = 0;
            }
        }
        return resultado.toString().trim();
    }

    /* 2. Escriba una función que reciba un entero N mayor de 2 y retorne un string con esos N términos de la 
    serie de Fibonacci (La sucesión de Fibonacci se trata de una serie infinita de números naturales que empieza con un 0 y un 1 
    y continúa añadiendo números que son la suma de los dos anteriores: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 
    987, 1597…)
     * 
     * 
    */
    public static String obtenerFibonacci(int n) {
        if (n <= 2) {
            throw new IllegalArgumentException("N debe ser mayor que 2.");
        }
        List<Integer> fibonacci = new ArrayList<>();
        fibonacci.add(0);
        fibonacci.add(1);
        
        for (int i = 2; i < n; i++) {
            int siguiente = fibonacci.get(i - 1) + fibonacci.get(i - 2);
            fibonacci.add(siguiente);
        }
        
        return fibonacci.toString();
    }

    /* 
     * 3. Diseñar y desarrollar una función que NO reciba datos de entrada, genere aleatoriamente un número entre 2 y 355, 
       le calcule su raíz cuadrada y retorne este valor. Para calcular las raíces usar la función Sqrt de la biblioteca Math.

       En el main, antes de invocar la función, se debe calcular un aleatorio entre 20 y 30 el cual establecerá la cantidad 
       de veces que va a llamar a la función y en un ciclo, mostrar los resultados.

    */
    public static double obtenerRaizCuadradaAleatoria() {
        Random aleatorio = new Random();
        int numero = aleatorio.nextInt(354) + 2; // Aleatorio entre 2 y 355
        return Math.sqrt(numero);
    }

    /* 4. Diseñar y desarrollar una función que reciba un valor inicial y un valor final, para generar 900 números aleatorios 
        en este rango y retorne un texto que diga cuántos números pares fueron generados. Controle que el nro inicial sea menor que 
        nro final para establecer el rango.

        Llame la función desde el main e imprimir el resultado arrojado.
    */
    public static int generarNumerosAleatorios(int inicio, int fin) {
        if (inicio >= fin) {
            throw new IllegalArgumentException("El número inicial debe ser menor que el número final.");
        }
        Random aleatorio = new Random();
        int cantidadPares = 0;

        for (int i = 0; i < 900; i++) {
            int numeroAleatorio = aleatorio.nextInt(fin - inicio + 1) + inicio;
            if (numeroAleatorio % 2 == 0) {
                cantidadPares++;
            }
        }
        return cantidadPares;
    }

    /* 5. Diseñar y desarrollar una función que calcule una cantidad de números aleatorios que viene como parámetro de entrada 
        y los sume.  La función deberá retornar el total de la suma. Usted defina los rangos que va a usar en el cálculo.

        Llame la función desde el main e imprimir el resultado arrojado.
    */
    public static int sumarNumerosAleatorios(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0.");
        }
        Random aleatorio = new Random();
        int suma = 0;

        for (int i = 0; i < cantidad; i++) {
            suma += aleatorio.nextInt(100); // Números aleatorios de 0 a 99
        }
        return suma;
    }

    /* 6. Se requiere una función para simular el sorteo de una lotería, de acuerdo con las siguientes condiciones:

            La lotería tiene 20 premios. 

            Cada premio que calcula el programa debe tener el número de 4 cifras entre 0000 y 9999 acompañado de la serie 
            que es un número entre 100 y 150.

            Ejemplo: 5698-101

            Cada premio lo debe imprimir el programa de la siguiente forma (n representa un número cualquiera):

            Sorteo # nn - Número Premiado nnnn - Serie nnn

            Ejm: Sorteo # 19  - Número Premiado 5698 - Serie 101

            Para tener en cuenta la forma en la cual se informan los 20 resultados: 

            Del premio 20 al 6 el programa imprime: 

            ======PREMIOS MENORES=======
            y la lista de los 15 premios 

            Del premio 5 al 2 el programa imprime:
            ======PREMIOS SECOS=========
            y la lista de los 4 premios secos

            AL llegar al premio 1, el programa imprime:
            ======Premio mayor==========
            Y el premio mayor

            Nota: para sacar el premio mayor calcule el random por cada número, como lo hacen en la realidad los sorteos.

            La función no recibe parámetros y devuelve un string con toda la lista de premios. El main, invoca la función 
            e imprime el resultado que esta arroje. 
    */
    public static String sorteoLoteria() {
        StringBuilder resultado = new StringBuilder();
        Random aleatorio = new Random();
        List<String> premios = new ArrayList<>();

        // Generar 20 números de lotería únicos
        while (premios.size() < 20) {
            int numero = aleatorio.nextInt(10000); // 0000 a 9999
            int serie = aleatorio.nextInt(51) + 100; // 100 a 150
            String premio = String.format("%04d-%d", numero, serie);
            if (!premios.contains(premio)) {
                premios.add(premio);
            }
        }

        // Imprimir premios
        for (int i = 0; i < premios.size(); i++) {
            if (i == 15) {
                resultado.append("======PREMIOS MENORES=======\n");
            } else if (i == 19) {
                resultado.append("======Premio mayor==========\n");
            } else if (i == 16) {
                resultado.append("======PREMIOS SECOS=========\n");
            }
            resultado.append(String.format("Sorteo # %d - Número Premiado %s\n", i + 1, premios.get(i)));
        }
        return resultado.toString();
    }
}
