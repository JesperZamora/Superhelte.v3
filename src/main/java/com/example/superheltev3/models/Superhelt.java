package com.example.superheltev3.models;

public class Superhelt {
    private String heroName;
    private String civilName;
    private String heroPower;
    private int age;

    public Superhelt(String heroName, String civilName, String heroPower, int age) {
        this.heroName = heroName;
        this.civilName = civilName;
        this.heroPower = heroPower;
        this.age = age;
    }

    public String getHeroName() {
        return heroName;
    }

    public String getCivilName() {
        return civilName;
    }

    public String getHeroPower() {
        return heroPower;
    }

    public int getAge() {
        return age;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public void setCivilName(String civilName) {
        this.civilName = civilName;
    }

    public void setHeroPower(String heroPower) {
        this.heroPower = heroPower;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return String.format("""
                        Hero-name: %-10s Civil-name: %-10s Superpower: %-10s Hero-age: %-10d""",
                heroName, civilName, heroPower, age);
    }

}
