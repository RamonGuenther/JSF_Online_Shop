package de.fhswf.fit.entities;

import de.fhswf.fit.entities.enums.ImageType;
import jakarta.annotation.Resource;
import jakarta.persistence.*;
import org.primefaces.shaded.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.Objects;

@Entity
public class Image implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private byte[] data;

    private ImageType imageType;

    public Image(String name, ImageType imageType) {
        this.name = name;
        this.imageType = imageType;
    }

    public Image() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte[] getData() {
        return data;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageType(ImageType imageType) {
        this.imageType = imageType;
    }


    public void setData(String imageFile) {

    }
}
