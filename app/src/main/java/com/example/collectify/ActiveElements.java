package com.example.collectify;

import androidx.lifecycle.ViewModel;

public class ActiveElements extends ViewModel {
    private Box activeBox;

    public Box getActiveBox() {
        return this.activeBox;
    }

    public void setActiveBox(Box box) {
        this.activeBox = box;
    }
}
