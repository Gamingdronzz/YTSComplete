package com.gamingdronzz.yts.Classes;

import com.gamingdronzz.yts.Interfaces.IURLProcessor;
import com.gamingdronzz.yts.Listeners.OnURLAvailabilityCheckedListener;

/**
 * Created by balpreet on 3/23/2018.
 */

public class DummyURLProcessor implements IURLProcessor {
    private OnURLAvailabilityCheckedListener onURLAvailabilityCheckedListener;

    public DummyURLProcessor(OnURLAvailabilityCheckedListener onURLAvailabilityCheckedListener)
    {
        this.onURLAvailabilityCheckedListener = onURLAvailabilityCheckedListener;
    }

//    @Override
//    public void setOnURLAvailabilityCheckedListener(OnURLAvailabilityCheckedListener onURLAvailabilityCheckedListener) {
//        this.onURLAvailabilityCheckedListener = onURLAvailabilityCheckedListener;
//    }

    @Override
    public void CheckURLAvailability() {
        if (onURLAvailabilityCheckedListener != null)
            onURLAvailabilityCheckedListener.OnUrlAvailable();
    }
}
