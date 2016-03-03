package com.zxxk.json.strategypatterndemo.service;

/**
 * Description: 返利收费子类
 * Created by: json
 * Created time: 2016/3/1
 */
public class CashReturn extends CashSuper {

    private double moneyCondition = 0.0d;
    private double moneyReturn = 0.0d;

    public CashReturn(double moneyCondition, double moneyReturn) {

        this.moneyReturn = moneyReturn;
        this.moneyCondition = moneyCondition;
    }

    @Override
    public double acceptCash(double money) {

        double result = money;
        if (money >= moneyCondition) {
            result = money - Math.floor(money / moneyCondition) * moneyReturn;
        }
        return result;
    }
}
