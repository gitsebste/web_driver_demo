package utils;

import java.util.function.Supplier;

public class Waiting {

    private static final int DELTA_TIME_REPEATING_CALLING_MILLISECONDS = 10;
    private static RuntimeException lastException;

    public static void runUntilSuccessOrTimeout(Runnable run) {
        runUntilStableStateOrTimeout(()->tryToRun(run)?1:0,1);
    }
    private static boolean tryToRun(Runnable run) {
        try {
            run.run();
            return true;
        }catch(RuntimeException e){
            lastException = e;
        }
        return false;
    }
    private static void sleep(int milis){
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void runUntilStableStateOrTimeout(
            Supplier<Integer> stateSupplier,
            Integer goalState) {
        final int dt = DELTA_TIME_REPEATING_CALLING_MILLISECONDS;
        for (int milliseconds = 0; milliseconds < 1_000; milliseconds+=dt) {
            Integer state = stateSupplier.get();
            if (state.equals(goalState))
                return;
            sleep(dt);
        }
        throw lastException;
    }
}
