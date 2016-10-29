package application;

import java.math.BigDecimal;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Calculator {

	private DoubleProperty num1 = new SimpleDoubleProperty(0);
	private DoubleProperty result = new SimpleDoubleProperty(0);
	private StringProperty signS = new SimpleStringProperty("");
	private static Sign sign = null;
	private String symbol = ""; 
	public boolean equals = false;
	private boolean isDecimal = false;
	private int decimalNo = 1;

	public Calculator() {

	}

	public DoubleProperty getDPNum1() {
		return this.num1;
	}

	public Double getNum1() {
		return this.num1.get();
	}

	public void setNum1(double next) {

		if (equals) {
			clearDecimal();
			num1.set(next);
			System.out.println(num1.toString());
			this.equals = false;
		} else if (this.isDecimal) {
			Double curr = getNum1() + next / (Math.pow(10, this.getDecimalNo()));
			System.out.println(next / (Math.pow(10, this.getDecimalNo())));
			curr = round(curr, getDecimalNo());
			System.out.println(curr);
			this.num1.set(curr);
			setDecimalNo(getDecimalNo() + 1);

			System.out.println(curr);

		} else {
			Double curr = getNum1() * 10 + next;
			this.num1.set(curr);
		}
	}

	public int getDecimalNo() {
		return decimalNo;
	}

	public void setDecimalNo(int decimalNo) {
		this.decimalNo = decimalNo;
	}

	public DoubleProperty getDPResult() {
		return this.result;
	}

	public Double getResult() {
		return this.result.get();
	}

	public void setResult(Double result) {
		this.result.set(result);
	}

	public Sign getSign() {
		return sign;
	}

	public void setSign(Sign sign) {
		this.equals = false;
		clearDecimal();
		if (getSign() == null) {
			Calculator.sign = sign;
			setResult(getNum1());
			// Calculator.SignSymbol.set(getSign().name());
			// this.SignSymbol.set(this.sign.toString());
			// this.SignSymbol.set(this.sign.toString());
			System.out.println(Calculator.sign);
			callCE();
		} else {
			calculate();
			Calculator.sign = sign;
			System.out.println(Calculator.sign);
			this.num1.set(0);
		}
		if (Calculator.sign == null) {
			this.signS.set("");
		} else {
			switch(Calculator.sign){
			case ADD:
				symbol = " + ";
				break;
			case DEDUCT:
				symbol = " - ";
				break;
			case DIVIDE:
				symbol = " / ";
				break;
			case MULTIPLY:
				symbol = " * ";
				break;
			default:
				break;
			
			}
			
			this.signS.set(getResult().toString() + this.symbol.toString());
		}
	}

	public StringProperty getSignS() {
		return this.signS;
	}

	public void clearSign() {
		this.setSign(null);
	}

	public void calculate() {
		clearDecimal();
		if (getResult() == 0) {
			setResult(num1.get());
			this.num1.set(0);
		}
		switch (Calculator.sign) {
		case ADD:
			setResult(getResult() + getNum1());
			break;
		case DEDUCT:
			setResult(getResult() - getNum1());
			break;
		case DIVIDE:
			if (getNum1() == 0) {
				setResult(getResult());
			} else {
				setResult(getResult() / getNum1());
			}
			break;
		case MULTIPLY:
			setResult(this.getResult() * getNum1());
			break;
		default:
			break;

		}

	}

	public void setDecimal(boolean isDecimal) {
		this.isDecimal = isDecimal;
	}

	public void equals() {

		if (!(Calculator.sign == null)) {
			calculate();
			
			// clearTask();
			Calculator.sign = null;
			this.num1.set(getResult());
			this.signS.set("");
			this.equals = true;
		}
	}

	public void clearDecimal() {
		setDecimal(false);
		setDecimalNo(1);
	}

	public void callC() {
		this.result.set(0);
		this.num1.set(0);

		clearSign();
		clearDecimal();
	}

	public void callCE() {
		this.num1.set(0);
		clearDecimal();
	}

	public void multiplyByMinus() {
		Double curr = getNum1() * (-1);
		this.num1.set(curr);
		System.out.println(getNum1());
	}

	public double round(double numberToRound, int numberOfDecimals) {

		return new BigDecimal(numberToRound).setScale(numberOfDecimals, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
