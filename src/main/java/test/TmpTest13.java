package test;

import javax.script.ScriptException;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class TmpTest13 {

	public static void main(String[] args) throws ScriptException {
		String exp1 = "9*9";
		Expression expression1 = new ExpressionBuilder(exp1).build();
		Double result1 = expression1.evaluate();
		System.out.println(result1.intValue());
	}
}
