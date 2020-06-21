package observer.v1;

import java.util.ArrayList;
import java.util.List;

public class MyClass {
    public static void main(String[] args) {
        Child child = new Child();
        child.addListener(new Father());
        child.wakeup();
    }
}

class Child{
    List<Observer> observers = new ArrayList<>();

    public void addListener(Observer o) {
        observers.add(o);
    }

    WakeupEvent wakeupEvent = new WakeupEvent(System.currentTimeMillis(), "bed", this);

    public void wakeup() {
        for(Observer o : observers) {
            o.onWakeupAction(wakeupEvent);
        }
    }
}

interface Observer{
    void onWakeupAction(Event e);
}

class WakeupEvent extends Event<Child>{

    long time;
    String loc;
    Child source;

    public WakeupEvent(long time, String loc, Child source) {
        this.time = time;
        this.loc = loc;
        this.source = source;
    }

    @Override
    Child getSource() {
        return source;
    }
}

abstract class Event<T> {
    abstract T getSource();
}

class Father implements Observer{

    @Override
    public void onWakeupAction(Event e) {
        System.out.println("feed child");
    }
}


