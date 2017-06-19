package ru.job4j.calculator;
/**
* Класс калькулятор.
*/
public class Calculator {
	/**
	*   @param result store result calculation
	*/
	private double result;
	/**
	*	@param first first variable
	*	@param second second variable
	*/
	public void add(double first, double second) {
		this.result = first + second;
	}
	/**
	*	@param first first variable
	*	@param second second variable
	*/
	public void substruct(double first, double second) {
		this.result = first - second;
	}
	/**
	*	@param first first variable
	*	@param second second variable
	*/
	public void div(double first, double second) {
		this.result = first / second;
	}
	/**
	*	@param first first variable
	*	@param second second variable
	*/
	public void multiply(double first, double second) {
		this.result = first * second;
	}
	/**
	*	@return result Returns result of calculation
	*/
	public double getResult() {
		return this.result;
	}
}