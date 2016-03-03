package com.zxxk.json.strategypatterndemo.factory;

import com.zxxk.json.strategypatterndemo.service.CashNormal;
import com.zxxk.json.strategypatterndemo.service.CashRebate;
import com.zxxk.json.strategypatterndemo.service.CashReturn;
import com.zxxk.json.strategypatterndemo.service.CashSuper;

/**
 * Description: 策略模式与简单工厂类
 * Created by: json
 * Created time: 2016/3/1
 */
public class CashContext {

    CashSuper cs = null;

    public CashContext(String index) {

        switch (index) {

            case "满300返100":
                CashReturn csReturn = new CashReturn(300, 100);
                cs = csReturn;
                break;
            case "打8折":
                CashRebate csRebate = new CashRebate(0.8);
                cs = csRebate;
                break;
            case "正常收费":
            default:
                CashNormal csNormal = new CashNormal();
                cs = csNormal;
                break;

        }
    }

    /**
     * 获取结果
     * @param money
     * @return
     */
    public double getResult(double money) {
        return cs.acceptCash(money);
    }
}
