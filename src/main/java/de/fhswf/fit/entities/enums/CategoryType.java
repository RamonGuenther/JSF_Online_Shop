package de.fhswf.fit.entities.enums;

public enum CategoryType {
    ALLE_KATEGORIEN ("Alle Kategorien"),
    TECHNIK ("Technik"),
    COMPUTER ("Computer"),
    SMARTPHONES ("Smartphones"),
    KONSOLEN ("Konsolen"),
    GAMES ("Games"),
    HAUSHALTSGERAETE ("Haushaltsgeräte"),
    MUSIKANLAGEN ("Musikanlagen"),
    CDS_UND_VINYL ("CD's und Vynil"),
    LAUTSPRECHER ("Lautsprecher"),
    DVD_UND_BLUERAY ("DVD und BlueRay"),
    FERNSEHER_UND_HEIMKINO ("Fernseher und Heimkino"),
    SPEICHERMEDIEN ("Speichermedien");

    public final String label;

    CategoryType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}