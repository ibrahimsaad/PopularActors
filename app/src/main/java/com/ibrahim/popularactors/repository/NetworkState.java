package com.ibrahim.popularactors.repository;

public class NetworkState {
    public static final String SUCCESS_MESSAGE = "Success";
    public static final String SUCCESS_RUNNING = "Running";

    public enum Status {
        RUNNING,
        SUCCESS,
        FAILED
    }

    private final Status status;
    private final String msg;

    public static final NetworkState LOADED;
    public static final NetworkState LOADING;

    public NetworkState(Status status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    static {
        LOADED = new NetworkState(Status.SUCCESS, SUCCESS_MESSAGE);
        LOADING = new NetworkState(Status.RUNNING, SUCCESS_RUNNING);
    }

    public Status getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
