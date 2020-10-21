package com.card.enu;

public enum CardState {
    Not_Downloaded(0), // 未售出
    Downloaded(1);// 已售出

    private final int value;

    // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
    CardState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
