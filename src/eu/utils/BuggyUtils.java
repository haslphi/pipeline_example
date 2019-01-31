package eu.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Each of these public methods and the inner classes are somehow flawed and
 * don't pass all of their unit tests in {@link BuggyUtilsTests}.
 * <p>
 * Can you spot the bugs?
 * <p>
 * Hints:
 * <ol>
 * <li>You mustn't change a method's signature.</li>
 * <li>Have a look at {@link BuggyUtilsTests}: The unit tests define how a
 * method should behave and include tips to get bonus points.</li>
 * <li>If all (unaltered) unit tests of a method succeed, the method is
 * considered bug-free.</li>
 * <li>Use your commit messages to explain why the methods are broken.</li>
 * <li>If you have major difficulties finding the bug, or if you think I've made
 * a mistake, please write me an email.</li>
 * </ol>
 * This class was tested with JRE 1.8.0_45
 *
 * @author Markus Weninger (markus.weninger@jku.at)
 *
 */
public final class BuggyUtils {
    /**
     * Maps plaintext English names of colors ("green") to their hexadecimal
     * representation ("#008000")
     */
    private static final Map<String, String> COLOR_NAME_TO_HEX_MAP = getColorNameToHexMap();

    /**
     * Compares two integer lists for equality.
     * <p>
     * The lists are considered equal if they have the same length and have
     * equal values in the same order.
     *
     * @param firstList
     *            compare this list with the {@code secondList}
     * @param secondList
     *            compare this list with the {@code firstList}
     * @return whether {@code firstList} and {@code secondList} are equal
     * @throws NullPointerException
     *             if any of the lists are null
     */
    public static boolean areEqual(final List<Integer> firstList,
            final List<Integer> secondList) {
        if (firstList == null || secondList == null) {
            throw new NullPointerException("Can't compare lists that are null");
        }
        boolean listsAreEqual = true;
        if (firstList.size() == secondList.size()) {
            final int nrOfInts = firstList.size();
            for (int i = 0; i < nrOfInts; i++) {
                final int intOfFirstList = firstList.get(i);
                final int intOfSecondList = secondList.get(i);
                // Compare integers for equality
                if (intOfFirstList != intOfSecondList) {
                    // These two values differ -> the lists are not equal
                    listsAreEqual = false;
                    break;
                }
            }
        } else {
            // The lists are of different size --> they are not equal
            listsAreEqual = false;

        }
        return listsAreEqual;
    }

    /**
     * Converts a plaintext {@code colorName} to its hexadecimal representation.
     * <p>
     * Is case-insensitive towards its input: "WHITE" and "white" should yield
     * the same output.
     * <p>
     * Returns {@code null} if {@code colorName} is unknown or null.
     * <p>
     * This method should behave the same on all machines, irrespective of their
     * locale.
     * <p>
     * Examples:
     *
     * <pre>
     * colorNameToHex("green") → "#008000"
     * colorNameToHex("WHITE") → "#ffffff"
     * colorNameToHex("white") → "#ffffff"
     * </pre>
     *
     * @param colorName
     *            the name of the color in plain English
     * @return the hexadecimal representation of the color or null, if the color
     *         is unknown
     */
    public static String colorNameToHex(final String colorName) {
        String hex;
        if (colorName == null) {
            hex = null;
        } else {
            // Color names are all lower case in the map
            final String colorNameLower = colorName.toLowerCase(Locale.ENGLISH);
            hex = COLOR_NAME_TO_HEX_MAP.get(colorNameLower);
        }
        return hex;
    }

    /**
     * Rounds a double to a {@code decimalPlace}.
     * <p>
     * Examples:
     *
     * <pre>
     * round(1.46, 1) → 1.5
     * round(1.005, 2) → 1.01
     * round(123.0054, 3) → 123.005
     * </pre>
     *
     * @param roundMe
     *            double that is rounded
     * @param decimalPlace
     *            decimal place to which {@code roundMe} is rounded
     * @return the rounded value
     */
    public static double round(final double roundMe, final int decimalPlace) {
    	return BigDecimal.valueOf(roundMe).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * Adds up all {@code summands} and returns the sum as a {@link BigDecimal}.
     *
     * @param summands
     *            doubles that are added to each other
     * @return the sum of {@code summands} as a {@link BigDecimal}
     */
    public static BigDecimal sum(final double... summands) {
        BigDecimal sum = BigDecimal.ZERO;
        for (final double summand : summands) {
            sum = sum.add(BigDecimal.valueOf(summand));
        }
        return sum;
    }

    /**
     * Creates a (rather sparse) map from English plaintext colors ("black") to
     * their hexadecimal representation ("#000000").
     * <p>
     * <b>Note to students:</b> This helper method does not contain any bugs;
     * don't change it.
     *
     * @return a map from lower case color names to their hex value
     */
    private static Map<String, String> getColorNameToHexMap() {
        final Map<String, String> map = new HashMap<>();
        map.put("white", "#ffffff");
        map.put("black", "#000000");
        map.put("green", "#008000");
        return map;
    }

    /**
     * A generic key/value pair.
     *
     * @author Markus Weninger (markus.weninger@jku.at)
     *
     * @param <K>
     *            type of the key
     * @param <V>
     *            type of the value
     */
    public static class KeyValue<K, V> {
        private final K key;
        private final V value;

        public KeyValue(final K key, final V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return "key: " + key + " value: " + value;
        }
        @Override	
        public int hashCode() {	
        	final int prime = 31;
            int result = 1;
            result = prime * result + value.hashCode();
            result = prime * result + key.hashCode();
            return result;
        }	

        @SuppressWarnings("rawtypes")
		@Override	
        public boolean equals(Object obj) {
        	if (this == obj)	
        		return true;	
        	if (obj == null)
        		return false;
        	if (getClass() != obj.getClass())	
        		return false;	
			KeyValue other = (KeyValue) obj;	
        	if(this.key == other.key && this.value == other.value) 
        		return true;
        	return false;
        }
    }

    /**
     * Models a person.
     *
     * @author Markus Weninger (markus.weninger@jku.at)
     *
     */
    public static class Person {

        public static final String DEFAULT_NAME = "unknown";
        public static final int DEFAULT_AGE = -1;

        public String name = DEFAULT_NAME;
        public int age = DEFAULT_AGE;

        private Person() {
            this.ensureNewPersonHasDefaultValues();
        }

        /*
         * A static factory method grants more flexibility in object creation.
         * See "Effective Java" by Joshua Bloch in Chapter 2, Item 1
         */
        public static Person create() {
            final Person newPerson = new Person();
            return newPerson;
        }

        /**
         * Makes sure new persons have default values by throwing
         * {@link IllegalStateException}s if they don't.
         */
        protected void ensureNewPersonHasDefaultValues() {
            if (!DEFAULT_NAME.equals(name)) {
                throw new IllegalStateException(
                        "New persons should have the default name of: "
                                + DEFAULT_NAME);
            }
            if (DEFAULT_AGE != age) {
                throw new IllegalStateException(
                        "New persons should have the default age of: "
                                + DEFAULT_AGE);
            }
        }
    }

    /**
     * Models a student.
     *
     * @author Markus Weninger (markus.weninger@jku.at)
     *
     */
    public static class Student extends Person {

        public static List<String> subjects = new ArrayList<>();

        public static final String DEFAULT_MATRICULATION_NR = "Unknown matriculation number";
        public static String matriculationNr = DEFAULT_MATRICULATION_NR;

        private Student(final String name, final int age,
                final String matriculationNr) {
        	ensureNewPersonHasDefaultValues();
            this.name = name;
            this.age = age;
        }

        public static Student create(final String name, final int age,
                final String matriculationNr) {
        	
            final Student newStudent = new Student(name, age, matriculationNr);
            return newStudent;
        }

        @Override
        protected void ensureNewPersonHasDefaultValues() {
            // Check the inherited fields
            super.ensureNewPersonHasDefaultValues();
            // Check the student's fields
            if (!subjects.isEmpty()) {
                throw new IllegalStateException(
                        "New students shouldn't have any subjects, yet.");
            }
            if (!DEFAULT_MATRICULATION_NR.equals(matriculationNr)) {
                throw new IllegalStateException(
                        "New students should have the default matriculation number: "
                                + DEFAULT_MATRICULATION_NR);
            }
        }
    }
}