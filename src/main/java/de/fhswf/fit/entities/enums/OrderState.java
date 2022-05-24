package de.fhswf.fit.entities.enums;

public enum OrderState {

    OFFEN("Offen"),
    BESTELLT("Bestellt");

    public final String label;

    OrderState(String label) {
        this.label = label;
    }
}
