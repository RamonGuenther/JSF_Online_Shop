package de.fhswf.fit.entities;

import de.fhswf.fit.entities.enums.ImageType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.primefaces.shaded.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Image implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

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
//        try {
//            InputStream is = getClass().getResourceAsStream("/images/" + imageFile);
//            System.out.println("/images/"+imageFile);
//            data = IOUtils.toByteArray(is);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
