package org.example.strategy;

public interface Strategy extends Runnable{

    default String getStrategyName(){
        return this.getClass().getSimpleName();
    }
    void evaluate();
    void openPosition();
    void closePosition();

}
