package cz.czechitas.kockamyssyr;

import cz.czechitas.kockamyssyr.api.*;

import java.awt.*;
import java.util.Random;

/**
 * Hlaví třída pro hru Kočka–myš–sýr.
 */
public class HlavniProgram {
    private final Random random = new Random();

    private final int VELIKOST_PRVKU = 50;
    private final int SIRKA_OKNA = 1000 - VELIKOST_PRVKU;
    private final int VYSKA_OKNA = 600 - VELIKOST_PRVKU;

    private Cat tom;
    private Mouse jerry;

    /**
     * Spouštěcí metoda celé aplikace.
     *
     * @param args
     */
    public static void main(String[] args) {
        new HlavniProgram().run();
    }

    /**
     * Hlavní metoda obsahující výkonný kód.
     */
    public void run() {
        tom = vytvorKocku();
        tom.setBrain(new KeyboardBrain(KeyCode.W, KeyCode.A, KeyCode.S, KeyCode.D));

        jerry = vytvorMys();
        jerry.setBrain(new KeyboardBrain());

        vytvorVeci(4);
        chytMys();
    }

    public void chytMys() {
        // TODO: Sem vepište svůj program
        while (jerry.isAlive()) {
            jdiZaJerrym();
            vyhniSeStromu();
        }

    }


    public void vytvorVeci(int pocetStromu) {
        for (int i = 0; i < pocetStromu; i++) {
            vytvorStrom();
        }
        vytvorSyr();
        vytvorJitrnici();
    }

    public Tree vytvorStrom() {
        return new Tree(vytvorNahodnyBod());
    }

    public Cat vytvorKocku() {
        return new Cat(vytvorNahodnyBod());
    }

    public Mouse vytvorMys() {
        return new Mouse(vytvorNahodnyBod());
    }

    public Cheese vytvorSyr() {
        return new Cheese(vytvorNahodnyBod());
    }

    public Meat vytvorJitrnici() {
        return new Meat(vytvorNahodnyBod());
    }

    private Point vytvorNahodnyBod() {
        return new Point(random.nextInt(SIRKA_OKNA), random.nextInt(VYSKA_OKNA));
    }

    private void najdiPozici() {
        int jx = jerry.getX();
        int jy = jerry.getY();
        int tx = tom.getX();
        int ty = tom.getY();


    }

    private void otocSeVpravo() {
        if (tom.getOrientation() == PlayerOrientation.RIGHT) {
            tom.turnRight();
            tom.moveForward();
        }
        if (tom.getOrientation() == PlayerOrientation.UP){
            tom.turnRight();
        }
        if (tom.getOrientation() == PlayerOrientation.DOWN){
            tom.turnRight();
        }
    }

    private void otocSeVlevo() {
        if (tom.getOrientation() == PlayerOrientation.LEFT) {
            tom.turnLeft();
            tom.moveForward();
        }
        if (tom.getOrientation() == PlayerOrientation.UP){
            tom.turnLeft();
        }
        if (tom.getOrientation() == PlayerOrientation.DOWN){
            tom.turnLeft();
        }
    }

    private void otocSeDolu() {
        if (tom.getOrientation() == PlayerOrientation.DOWN) {
            tom.turnRight();
            tom.moveForward();
        }

        if (tom.getOrientation() == PlayerOrientation.LEFT){
            tom.turnLeft();
        }
        if (tom.getOrientation() == PlayerOrientation.RIGHT){
            tom.turnLeft();
        }

    }

    private void otocSeNahoru() {
        if (tom.getOrientation() == PlayerOrientation.UP) {
            tom.turnRight();
            tom.moveForward();
        }

        if (tom.getOrientation() == PlayerOrientation.LEFT){
            tom.turnLeft();
        }
        if (tom.getOrientation() == PlayerOrientation.RIGHT){
            tom.turnLeft();
        }

    }


    private void jdiZaJerrym() {
        int rozdilSouradniceX = tom.getX() - jerry.getX();
        {
            if (rozdilSouradniceX < 0) {
                otocSeVpravo();
                tom.moveForward(Math.abs(rozdilSouradniceX));

            } else if (rozdilSouradniceX > 0) {
                otocSeVlevo();
                tom.moveForward(Math.abs(rozdilSouradniceX));

            }
        }
        int rozdilSouradniceY = tom.getY() - jerry.getY();
        {
            if (rozdilSouradniceY < 0) {
                otocSeDolu();
                tom.moveForward(Math.abs(rozdilSouradniceY));

            } else if (rozdilSouradniceY > 0) {
                otocSeNahoru();
                tom.moveForward(Math.abs(rozdilSouradniceY));

            }
        }
    }

    public void vyhniSeStromu(){
        tom.isPossibleToMoveForward();
        tom.turnLeft();
        tom.moveForward();
        tom.turnRight();
    }
}


