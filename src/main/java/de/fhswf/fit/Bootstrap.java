package de.fhswf.fit;

import de.fhswf.fit.entities.Category;
import de.fhswf.fit.entities.Image;
import de.fhswf.fit.entities.Product;
import de.fhswf.fit.entities.enums.CategoryType;
import de.fhswf.fit.entities.enums.ImageType;
import de.fhswf.fit.stores.CategoryStore;
import de.fhswf.fit.stores.ProductStore;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Startup
@Singleton
public class Bootstrap {

    @Inject
    private ProductStore productStore;

    @Inject
    private CategoryStore categoryStore;

    @PostConstruct
    public void init(){
        System.out.println("POST CONSTRUCT");

        Category categoryTechnik = new Category(CategoryType.TECHNIK, "In der Kategorie Technik ist alles mögliche an Elektrogeräten zu finden.");
        Category categoryComputer = new Category(CategoryType.COMPUTER, "Stand-Pc, Laptops, Notebooks, Chromebooks und vieles mehr. Außerdem alles an Teilen, was du brauchst um die deinen eigenen PC zu bauen :)");
        Category categorySmartphone = new Category(CategoryType.SMARTPHONES, "Smartphones verschiedenster Marken.");
        Category categoryKonsolen = new Category(CategoryType.KONSOLEN, "Playstation, XBox, Nintendo etc.");
        Category categoryGames = new Category(CategoryType.GAMES, "Spiele für alle Plattformen");
        Category categoryHaushaltsgeraete = new Category(CategoryType.HAUSHALTSGERAETE, "Alle möglichen Arten von Haushaltsgeräten, wie diverse Küchengeräte, Staubsauger etc.");
        Category categoryMusikanlagen = new Category(CategoryType.MUSIKANLAGEN, "CD-Spieler, Plattenspieler, Radios etc.");
        Category categoryCDsUndVinyl = new Category(CategoryType.CDS_UND_VINYL, "Alles an Musik für dich :)");
        Category categoryLautsprecher = new Category(CategoryType.LAUTSPRECHER, "Irgendwo muss die geile Mukke ja rauskommen ;D");
        Category categoryDVDUndBlueRay = new Category(CategoryType.DVD_UND_BLUERAY, "Filme so weit das Auge reicht");
        Category categoryFernseherHeimkino = new Category(CategoryType.FERNSEHER_UND_HEIMKINO, "Filme gucken wie im Kino...und das zuhause");
        Category categorySpeichermedien = new Category(CategoryType.SPEICHERMEDIEN, "Sichere deine Daten zuverlässig!");

        System.out.println("Kategorien wurden angelegt");

        categoryStore.save(categoryTechnik);
        categoryStore.save(categoryComputer);
        categoryStore.save(categorySmartphone);
        categoryStore.save(categoryKonsolen);
        categoryStore.save(categoryGames);
        categoryStore.save(categoryHaushaltsgeraete);
        categoryStore.save(categoryMusikanlagen);
        categoryStore.save(categoryCDsUndVinyl);
        categoryStore.save(categoryLautsprecher);
        categoryStore.save(categoryDVDUndBlueRay);
        categoryStore.save(categoryFernseherHeimkino);
        categoryStore.save(categorySpeichermedien);

        System.out.println("Kategorien wurden in der Datenbank gespeichert");


        Product smartphone1 = new Product("Samsung Galaxy S21 Ultra 5G", 1299.00, 10, "Krasses Handy");
        smartphone1.addCategory(categorySmartphone);
        smartphone1.addCategory(categoryTechnik);
        Image imageSmartphone1_1 = new Image("01_s21ultra.jpeg", ImageType.JPEG);
        imageSmartphone1_1.setData("01_s21ultra.jpeg");
        smartphone1.addImage(imageSmartphone1_1);
        Image imageSmartphone1_2 = new Image("02_s21ultra.jpeg", ImageType.JPEG);
        imageSmartphone1_2.setData("02_s21ultra.jpeg");
        smartphone1.addImage(imageSmartphone1_2);

        productStore.save(smartphone1);


    }
}
