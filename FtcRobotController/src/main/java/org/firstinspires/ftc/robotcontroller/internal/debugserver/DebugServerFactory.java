package org.firstinspires.ftc.robotcontroller.internal.debugserver;

public class DebugServerFactory {

    private DebugServerFactory() {}

    private static DebugServer instance_;
    public static DebugServer getInstance() { return instance_; }

    public static DebugServer initialize() throws Exception {
        instance_ = new JSONDebugServer();
//        instance_ = new NTDebugServer();

        return instance_;
    }


}
