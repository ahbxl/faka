package com.card.enu;

public enum ExportFileState {
    Not_Downloaded(0),
    Downloaded(1),
    Downloading(2);

    private final int value;

    // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
    ExportFileState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}