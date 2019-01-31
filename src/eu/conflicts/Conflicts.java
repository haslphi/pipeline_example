package eu.conflicts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Implement these methods together with a partner. Use Git to commit your code.
 * Create and resolve at least one conflict with your partner. Use Gerrit to
 * review the commits.
 * <p>
 * Refer to {@link ConflictsTests} for the expected behavior of each method. If
 * all unit tests pass, the methods are considered successfully implemented.
 *
 * @author Markus Weninger (markus.weninger@jku.at)
 *
 */
public class Conflicts {

    /**
     * Calculates the <a
     * href="https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes">Sieve of
     * Eratosthenes</a> from two to {@code upperBound} inclusive.
     *
     */
	public static List<Integer> eratosthenes(final int upperBound) {
		List<Integer> list = new LinkedList<Integer>();
		for (int i = 2; i <= upperBound; i++) {
			if(isPrime(i)) {
				list.add(i);
			}
		}
		return list;
	}

	private static boolean isPrime(long num) {
		return num > 1 && LongStream.rangeClosed(2, (long)Math.sqrt(num)).noneMatch(div-> num % div== 0);
	}
    
   private static int fib(int n) {
        if (n <= 2) 
             return 1;

        return fib(n-1) + fib(n-2);
   } 

    /**
     * Calculates the product of the Fibonacci sequence from 1 to
     * {@code upperBound} inclusive.
     */
    public static int fibonacciProd(final int upperBound) {
    	int fibProd = 1;
    	for(int i = 1; i <= upperBound; i++) {
    		if (fib(i) <= upperBound) {
    			fibProd = fibProd * fib(i);
    		}
    		else {
    			return fibProd;
    		}
        }
    	return fibProd;
    }

    /**
     * Prints the numbers from 1 to {@code upperBound} inclusive to
     * {@link System#out}. For multiples of three prints "Fizz" instead of the
     * number and for multiples of five prints "Buzz". For numbers which are
     * multiples of both three and five prints "FizzBuzz".
     *
     */
    public static void fizzBuzz(final int upperBound) {    	
    	for (int i = 1; i <=upperBound; i++) {
    		if (i % 3 == 0 && i % 5 == 0) {
    			System.out.println("FizzBuzz");
    		}
    		else {
	    		if (i % 3 == 0) {
	    			System.out.println("Fizz");
	    		} 
	    		if (i % 5 == 0) {
	    				System.out.println("Buzz");
	    		}
	    		if (i % 3 != 0 && i % 5 != 0) {
    				System.out.println(i);
    			}
    		}
    	}
    }

    /**
     * Gets the 26 uppercase characters from {@code A} to {@code Z}.
     *
     * @return the English uppercase alphabet as a {@link List} of
     *         {@link Character}s
     *
     */
    public static List<Character> getAlphabet() {
    	int start = 'A';
    	return IntStream.range(0, 26).mapToObj(i -> new Character((char)(start+i))).collect(Collectors.toList());
    }

    /**
     * Gets all the integers from a {@code string}.
     *
     * @param string
     *            that might contain integers
     * @return all the integers inside the {@code string}
     */
    public static List<Integer> getInts(final String string) {     
        String str = string.replaceAll("[^0-9]+", " "); 
        String[] str2_arr = str.trim().split(" ");
        List<Integer> intList = new ArrayList<>();
        
        if (!str2_arr[0].isEmpty()) {
	        for(int i=0; i< str2_arr.length; i++)
	        {
	        	intList.add(Integer.parseInt(str2_arr[i]));
	        }
        }
        
        return intList;
    }

    /**
     * Determines whether a {@code string} is equal to itself when reversed.
     * Case insensitive.
     *
     * @param string
     *            which may be a palindrome
     *
     * @return whether the {@code string} is a palindrome
     */
    public static boolean isPalindrome(final String string) {
    	String str = new StringBuilder(string).reverse().toString();
    	return str.toLowerCase().equals(string.toLowerCase()) ? true: false; 
    }
}