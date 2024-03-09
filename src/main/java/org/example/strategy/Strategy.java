package org.example.strategy;

import org.example.valueobject.Candle;

public interface Strategy extends Runnable{

    default String getStrategyName(){
        return this.getClass().getSimpleName();
    }
    void evaluate(Candle candle);
    void openPosition();
    void closePosition();

}
