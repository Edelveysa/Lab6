package common;

import common.data.*;

import java.io.Serializable;
import java.util.Objects;

public class HumanBeingLite implements Serializable {
    private String name;
    private Coordinates coordinates;
    private Boolean realHero;
    private Boolean hasToothpick;
    private long impactSpeed;
    private String soundtrackName;
    private Integer minutesOfWaiting;
    private Mood mood;
    private Car car;

    public HumanBeingLite(String name, Coordinates coordinates, Boolean realHero, Boolean hasToothpick,
                          long impactSpeed, String soundtrackName, Integer minutesOfWaiting, Mood mood, Car car) {
        this.name = name;
        this.coordinates = coordinates;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.soundtrackName = soundtrackName;
        this.minutesOfWaiting = minutesOfWaiting;
        this.mood = mood;
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Boolean getRealHero() {
        return realHero;
    }

    public Boolean getHasToothpick() {
        return hasToothpick;
    }

    public long getImpactSpeed() {
        return impactSpeed;
    }

    public String getSoundtrackName() {
        return soundtrackName;
    }

    public Integer getMinutesOfWaiting() {
        return minutesOfWaiting;
    }

    public Mood getMood() {
        return mood;
    }

    public Car getCar() {
        return car;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HumanBeingLite that = (HumanBeingLite) o;
        return impactSpeed == that.impactSpeed && Objects.equals(name, that.name)
                && Objects.equals(coordinates, that.coordinates) && Objects.equals(realHero, that.realHero)
                && Objects.equals(hasToothpick, that.hasToothpick) && Objects.equals(soundtrackName, that.soundtrackName)
                && Objects.equals(minutesOfWaiting, that.minutesOfWaiting) && mood == that.mood && Objects.equals(car, that.car);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, coordinates, realHero, hasToothpick, impactSpeed, soundtrackName, minutesOfWaiting, mood, car);
    }

    @Override
    public String toString() {
        return "HumanBeingLite{" +
                "name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", realHero=" + realHero +
                ", hasToothpick=" + hasToothpick +
                ", impactSpeed=" + impactSpeed +
                ", soundtrackName='" + soundtrackName + '\'' +
                ", minutesOfWaiting=" + minutesOfWaiting +
                ", mood=" + mood +
                ", car=" + car +
                '}';
    }
}