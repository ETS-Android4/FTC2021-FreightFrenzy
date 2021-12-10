package org.firstinspires.ftc.robotcontroller.internal.debugserver;

public interface DebugServer {
    public class Status {
        public String selectedOpmode = "";
        public String status = "Not Initialized";
    }

    void clear();

    void setOpMode(String name);
    void setOpModeStatus(String status);

    void putString(String key, String value);
    void putNumber(String key, double value);
    void putBoolean(String key, boolean value);

    String getString(String key, String defaultValue);
    double getNumber(String key, double defaultValue);
    boolean getBoolean(String key, boolean defaultValue);
}
