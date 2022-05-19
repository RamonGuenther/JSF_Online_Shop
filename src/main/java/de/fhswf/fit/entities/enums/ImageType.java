package de.fhswf.fit.entities.enums;

public enum ImageType {
    JPEG("jpeg"),
    JPG("jpg"),
    PNG("png"),
    GIF("gif");


    public final String label;

    ImageType(String label) {
        this.label = label;
    }

}