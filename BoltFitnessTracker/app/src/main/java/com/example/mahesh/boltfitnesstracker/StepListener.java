package com.example.mahesh.boltfitnesstracker;

/**
 * Created by Vidya on 10/22/15.
 */
public interface StepListener {

    /**
     * Called when a step has been detected.  Given the time in nanoseconds at
     * which the step was detected.
     */
    public void step(long timeNs);

}