package com.slin.camera_transfer.receiver;

/**
 * author: slin
 * date: 2019-07-05
 * description:
 */
public interface ReceiverManager {

    void post(Receiver receiver);

    void destroy();

}
