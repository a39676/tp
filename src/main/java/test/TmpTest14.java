package test;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class TmpTest14 {

	public static void main(String[] args) {
		Expression expression = new ExpressionBuilder("(3+2)*9").build();
	    double result = expression.evaluate();
	    System.out.println(result);
	}
}
