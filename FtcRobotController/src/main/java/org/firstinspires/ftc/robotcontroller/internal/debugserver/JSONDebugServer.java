package org.firstinspires.ftc.robotcontroller.internal.debugserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import fi.iki.elonen.NanoHTTPD;

public class JSONDebugServer extends NanoHTTPD implements DebugServer {

    private final Gson gson_ = new GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithModifiers(Modifier.TRANSIENT)
            .create();

    private final Map<String, Object> store_ = new ConcurrentHashMap<>();
    private final Status status_ = new Status();

    public JSONDebugServer() throws IOException {
        super(15530);
        this.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
    }

    @Override
    public Response serve(IHTTPSession session) {
        String uri = session.getUri();
        Method method = session.getMethod();

        System.out.println("Request: " + method + " " + uri);

        switch(uri) {
            case "/":
                return handleRoot(session);
            case "/store":
                return handleStore(session);
        }

        // No resource
        return newFixedLengthResponse(Response.Status.NOT_FOUND, "application/json", "");
    }

    private Response handleRoot(IHTTPSession session) {
        switch (session.getMethod()) {
            case GET:
                String body = gson_.toJson(status_);
                return newFixedLengthResponse(Response.Status.OK, "application/json", body);
        }
        return newFixedLengthResponse(Response.Status.METHOD_NOT_ALLOWED, NanoHTTPD.MIME_PLAINTEXT, "Allowed Methods: GET");
    }

    private Response handleStore(IHTTPSession session) {
        String body;
        switch (session.getMethod()) {
            case GET:
                body = gson_.toJson(store_);
                return newFixedLengthResponse(Response.Status.OK, "application/json", body);
            case POST:
                // Read in the POST body.
                // This version of NanoHTTP (2.3.1) is horrible, does not support reading the request body
                // from a PATCH request
                String postBody;
                try {
                    Map<String, String> files = new HashMap<>();
                    session.parseBody(files);
                    postBody = files.get("postData");
                } catch (Exception e) {
                    e.printStackTrace();
                    return newFixedLengthResponse(Response.Status.INTERNAL_ERROR, NanoHTTPD.MIME_PLAINTEXT, e.toString());
                }

                // Merge changes
                Map<String, Object> requestBody = gson_.fromJson(postBody, new TypeToken<Map<String, Object>>(){}.getType());
                for (Map.Entry<String, Object> entry : requestBody.entrySet()) {
                    store_.put(entry.getKey(), entry.getValue());
                }

                // Return with the modified store value
                body = gson_.toJson(store_);
                return newFixedLengthResponse(Response.Status.OK, "application/json", body);
        }
        return newFixedLengthResponse(Response.Status.METHOD_NOT_ALLOWED, NanoHTTPD.MIME_PLAINTEXT, "Allowed Methods: GET, POST");
    }


    @Override
    public void clear() {
        store_.clear();
    }

    @Override
    public void setOpMode(String name) {
        status_.selectedOpmode = name;
    }

    @Override
    public void setOpModeStatus(String status) {
        status_.status = status;
    }

    @Override
    public void putString(String key, String value) {
        store_.put(key, value);
    }

    @Override
    public String getString(String key, String defaultValue) {
        Object raw = store_.getOrDefault(key, defaultValue);
        if (raw instanceof String) {
            return (String)raw;
        }
        return defaultValue;
    }

    @Override
    public void putNumber(String key, double value) {
        store_.put(key, value);
    }

    @Override
    public double getNumber(String key, double defaultValue) {
        Object raw = store_.getOrDefault(key, defaultValue);
        if (raw instanceof Double) {
            return (Double) raw;
        }
        return defaultValue;
    }


    @Override
    public void putBoolean(String key, boolean value) {
        store_.put(key, value);
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        Object raw = store_.getOrDefault(key, defaultValue);
        if (raw instanceof Boolean) {
            return (Boolean) raw;
        }
        return defaultValue;
    }
}
