package com.liaody.ssl.base.designpattern.adapater;

public class MyAdapter {

    private MyAdapterImpl adapterImpl;

    public MyAdapter(MyAdapterImpl myAdapterImpl) {
        this.adapterImpl = myAdapterImpl;
    }

    public void doSomething() {
        adapterImpl.doSomething();
    }

    public static void main(String args[]) {
        new MyAdapter(new MyAdapterImpl()).doSomething();
    }

}

class MyAdapterImpl {
    public void doSomething() {

    }
}
