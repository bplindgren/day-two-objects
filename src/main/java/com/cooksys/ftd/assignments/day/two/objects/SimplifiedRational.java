package com.cooksys.ftd.assignments.day.two.objects;

import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SimplifiedRational implements IRational {
	int numerator;
	int denominator;

	/**
	 * Determines the greatest common denominator for the given values
	 *
	 * @param a
	 *            the first value to consider
	 * @param b
	 *            the second value to consider
	 * @return the greatest common denominator, or shared factor, of `a` and `b`
	 * @throws IllegalArgumentException
	 *             if a <= 0 or b < 0
	 */
	public static int gcd(int a, int b) throws IllegalArgumentException {
		if (a <= 0 || b < 0)
			throw new IllegalArgumentException();

		// if b == 0, return a
		if (b == 0) {
			return a;
		} else {
			// if a % b != 0, call gcd using b and the remainder of a/b
			return gcd(b, a % b);
		}

	}

	/**
	 * Simplifies the numerator and denominator of a rational value.
	 * <p>
	 * For example: `simplify(10, 100) = [1, 10]` or: `simplify(0, 10) = [0, 1]`
	 *
	 * @param numerator
	 *            the numerator of the rational value to simplify
	 * @param denominator
	 *            the denominator of the rational value to simplify
	 * @return a two element array representation of the simplified numerator
	 *         and denominator
	 * @throws IllegalArgumentException
	 *             if the given denominator is 0
	 */
	public static int[] simplify(int numerator, int denominator) throws IllegalArgumentException {
		if (denominator == 0)
			throw new IllegalArgumentException();

		// Create an initial array of the initial parameters
		int[] simple = new int[] { numerator, denominator };

		// If the numerator is 0, it cannot be simplified any more -> return the
		// simple array
		if (numerator == 0)
			return simple;

		// Find the GCD
		int divider = gcd(Math.abs(numerator), Math.abs(denominator));

		// Update the values of the simplified array
		simple[0] = numerator / divider;
		simple[1] = denominator / divider;
		return simple;
	}

	/**
	 * Constructor for rational values of the type:
	 * <p>
	 * `numerator / denominator`
	 * <p>
	 * Simplification of numerator/denominator pair should occur in this method.
	 * If the numerator is zero, no further simplification can be performed
	 *
	 * @param numerator
	 *            the numerator of the rational value
	 * @param denominator
	 *            the denominator of the rational value
	 * @throws IllegalArgumentException
	 *             if the given denominator is 0
	 */
	public SimplifiedRational(int numerator, int denominator) throws IllegalArgumentException {
		if (denominator == 0)
			throw new IllegalArgumentException();

		// Create a simplified array with the input parameters
		int[] simplified = simplify(numerator, denominator);

		// Set this.numerator and this.denominator to the simplified variables,
		// rather than the original parameters
		this.numerator = simplified[0];
		this.denominator = simplified[1];
	}

	/**
	 * @return the numerator of this rational number
	 */
	@Override
	public int getNumerator() {
		return numerator;
	}

	/**
	 * @return the denominator of this rational number
	 */
	@Override
	public int getDenominator() {
		return denominator;
	}

	/**
	 * Specializable constructor to take advantage of shared code between
	 * Rational and SimplifiedRational
	 * <p>
	 * Essentially, this method allows us to implement most of IRational methods
	 * directly in the interface while preserving the underlying type
	 *
	 * @param numerator
	 *            the numerator of the rational value to construct
	 * @param denominator
	 *            the denominator of the rational value to construct
	 * @return the constructed rational value (specifically, a
	 *         SimplifiedRational value)
	 * @throws IllegalArgumentException
	 *             if the given denominator is 0
	 */
	@Override
	public SimplifiedRational construct(int numerator, int denominator) throws IllegalArgumentException {
		if (denominator == 0)
			throw new IllegalArgumentException();

		return new SimplifiedRational(numerator, denominator);
	}

	/**
	 * @param obj
	 *            the object to check this against for equality
	 * @return true if the given obj is a rational value and its numerator and
	 *         denominator are equal to this rational value's numerator and
	 *         denominator, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		// Check to make sure the paramter is a SimplifiedRational object
		if (!(obj instanceof SimplifiedRational))
			return false;

		// Check to make sure both are positive or negative
		if ((getNumerator() / getDenominator() > 0) == (((SimplifiedRational) obj).getNumerator()
				/ ((SimplifiedRational) obj).getDenominator()) > 0) {

			// Collect values
			int num = Math.abs(getNumerator());
			int denom = Math.abs(getDenominator());

			int checkNum = Math.abs(((SimplifiedRational) obj).getNumerator());
			int checkDenom = Math.abs(((SimplifiedRational) obj).getDenominator());

			// Return if the two fractions are the same
			return (num == checkNum && denom == checkDenom) ? true : false;

		} else {
			// If one is positive and one is negative, return false
			return false;
		}
	}

	/**
	 * If this is positive, the string should be of the form
	 * `numerator/denominator`
	 * <p>
	 * If this is negative, the string should be of the form
	 * `-numerator/denominator`
	 *
	 * @return a string representation of this rational value
	 */
	@Override
	public String toString() {
		return ((numerator > 0) == (denominator > 0)) ? Math.abs(numerator) + "/" + Math.abs(denominator)
				: "-" + Math.abs(numerator) + "/" + Math.abs(denominator);
	}

}
