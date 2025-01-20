package com.alura.literalura.model;

public enum Lenguaje {
    es("Español"),
    en("Inglés"),
    fr("Francés"),
    pt("Portugués"),
    it("Italiano"),
    de("Alemán"),
    nl("Holandés");

    private String lenguajeCompleto;

    Lenguaje(String lenguajeCompleto){
        this.lenguajeCompleto=lenguajeCompleto;
    }

    public static Lenguaje fromString(String lenguaje){
        for(Lenguaje len : Lenguaje.values()){
            if(len.name().equalsIgnoreCase(lenguaje)){
                return len;
            }
        }
        throw new IllegalArgumentException("ERROR: Ningún lenguaje encontrado\n");
    }

    public static void listarLenguajes(){
        for (Lenguaje lenguaje : Lenguaje.values()){
            System.out.println("\t\t" + lenguaje.name()+" - "+ lenguaje.getLenguajeCompleto());
        }
    }

    public String getLenguajeCompleto() {
        return lenguajeCompleto;
    }
}
