package com.slin.camera_transfer.receiver;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author: slin
 * date: 2019-07-05
 * description:
 */
public class ReceiverManagerImpl implements ReceiverManager {

    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public void post(Receiver receiver) {
        executorService.execute(receiver);
    }

    @Override
    public void destroy() {
        executorService.shutdown();
    }
}
