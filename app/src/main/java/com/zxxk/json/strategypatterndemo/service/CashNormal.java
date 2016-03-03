package com.zxxk.json.strategypatterndemo.service;

/**
 * Description: 正常收费子类
 * Created by: json
 * Created time: 2016/3/1
 */
public class CashNormal extends CashSuper{


    @Override
    public double acceptCash(double money) {

        return money;
    }
}
