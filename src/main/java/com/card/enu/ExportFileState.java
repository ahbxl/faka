package com.card.enu;

public enum ExportFileState {
    Not_Downloaded(0), // 未下载
    Downloaded(1), // 已下载
    Downloading(-1); // 正在生成

    private final int value;

    // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
    ExportFileState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}