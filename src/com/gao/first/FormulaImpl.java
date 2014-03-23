package com.gao.first;

/**
 * User: wangchen
 * Date: 14-3-23
 * Time: 18:06
 */
public class FormulaImpl implements DefaultFormula {
    @Override
    public int calculate(int x) {
        return x * 2;
    }

    public static void main(String[] args) {
        FormulaImpl formula = new FormulaImpl();

        System.out.println(formula.calculate(3));
        System.out.println(formula.sqart(5));
    }
}
