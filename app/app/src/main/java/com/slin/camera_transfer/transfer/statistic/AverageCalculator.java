package com.slin.camera_transfer.transfer.statistic;

/**
 * author: slin
 * date: 2019-07-05
 * description: 传输帧率计算器
 */
public class AverageCalculator {

    private int completeSize = 0;
    private float taskAverage = 0;
    private long lastComputeAverageTime;

    private int mComputeDuration = 1000;

    public AverageCalculator() {

    }

    public AverageCalculator(int computeDuration) {
        this.mComputeDuration = computeDuration;
    }

    /**
     * 添加一个并计算帧率
     *
     * @return 帧率
     */
    public float addCalculateAverage() {
        long nowTime = System.currentTimeMillis();
        long duration = nowTime - lastComputeAverageTime;
        synchronized (this) {
            completeSize++;
            if (duration > mComputeDuration) {
                taskAverage = (float) completeSize / duration * 1000;
                completeSize = 0;
                lastComputeAverageTime = System.currentTimeMillis();
            }
        }
        return taskAverage;
    }

    public float getTaskAverage() {
        return taskAverage;
    }
}
