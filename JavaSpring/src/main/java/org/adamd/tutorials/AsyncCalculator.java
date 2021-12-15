package org.adamd.tutorials;

import lombok.extern.java.Log;

import java.util.*;

/**
 * A server that runs complex and long running calculations.
 * User jobs will be pushed into a queue and popped when a result is required.
 */
@Log
public final class AsyncCalculator {
    private final Map<String, Deque<Integer> > usersCalculation = new HashMap<>();

    /**
     * Log a calculation.
     * @param num1
     * @param num2
     */
    public void requestAddition(String user, Integer num1, Integer num2){
        Deque<Integer> usersCalcs = usersCalculation.get(user);
        if (usersCalcs == null){
            usersCalcs = new ArrayDeque<Integer>();
        }
        usersCalcs.push(num1+num2);
        usersCalculation.put(user, usersCalcs);
    }

    /**
     * Request the last calculation and delete it from the state.
     * @return
     */
    public Integer getLastCalculation(String user){
        Deque<Integer> usersCalcs = usersCalculation.get(user);
        Integer results = null;
        if (usersCalcs != null && !usersCalcs.isEmpty()){
            results = usersCalcs.pop();
        }
        return results;
    }
}
