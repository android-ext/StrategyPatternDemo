package com.zxxk.json.strategypatterndemo.service;

/**
 * Description: 打折收费子类
 * Created by: json
 * Created time: 2016/3/1
 */
public class CashRebate extends CashSuper {

    private double moneyRebate = 1d;

    public CashRebate(double moneyRebate) {

        this.moneyRebate = moneyRebate;
    }

    @Override
    public double acceptCash(double money) {

        return money * moneyRebate;
    }
}
