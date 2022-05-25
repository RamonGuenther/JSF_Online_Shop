package de.fhswf.fit;

import de.fhswf.fit.entities.*;
import de.fhswf.fit.entities.enums.*;
import de.fhswf.fit.stores.*;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

/**
 * TODO: - Bilder speichern
 */
@Startup
@Singleton
public class Bootstrap {

    @Inject
    private ProductStore productStore;

    @Inject
    private CategoryStore categoryStore;

    @Inject
    private UserStore userStore;

    @Inject
    private OrderStore orderStore;


    /**
     * TODO: - Für den Warenkorb wieder immer sagen die letzte Bestellung is der Warenkorb alles davor is die Bestellhistorie
     */
    @PostConstruct
    public void init() {
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


        Product smartphone1 = new Product("Samsung Galaxy S21 Ultra 5G", 1299.00, 10, "Krasses Handy", CategoryType.SMARTPHONES);
        smartphone1.addCategory(categorySmartphone);
        smartphone1.addCategory(categoryTechnik);
        Image imageSmartphone1_1 = new Image("01_s21ultra.jpeg", ImageType.JPEG);
        imageSmartphone1_1.setData("01_s21ultra.jpeg");

        smartphone1.addImage(imageSmartphone1_1);
        Image imageSmartphone1_2 = new Image("02_s21ultra.jpeg", ImageType.JPEG);
        imageSmartphone1_2.setData("02_s21ultra.jpeg");
        smartphone1.addImage(imageSmartphone1_2);

        productStore.save(smartphone1);

        Product smartphone2 = new Product("Iphone 13 ProMax", 1829.00, 8, "1TB purer Spaß", CategoryType.SMARTPHONES);
        smartphone2.addCategory(categorySmartphone);
        smartphone2.addCategory(categoryTechnik);
        Image imageSmartphone2_1 = new Image("03_iphone13promax.png", ImageType.JPEG);
        imageSmartphone2_1.setData("03_iphone13promax.png");
        smartphone2.addImage(imageSmartphone2_1);
        Image imageSmartphone2_2 = new Image("04_iphone13promax.png", ImageType.JPEG);
        imageSmartphone2_2.setData("04_iphone13promax.png");
        smartphone2.addImage(imageSmartphone2_2);
        productStore.save(smartphone2);
//
        Product playstation = new Product("Playstation 5", 879.00, 1, "Standard Edition, 825 GB interner Speicher", CategoryType.KONSOLEN);
        playstation.addCategory(categoryKonsolen);
        playstation.addCategory(categoryTechnik);
        Image imagePlaystation_1 = new Image("05_playstation5.jpeg", ImageType.JPEG);
        imagePlaystation_1.setData("05_playstation5.jpeg");
        playstation.addImage(imagePlaystation_1);
        Image imagePlaystation_2 = new Image("06_playstation5.jpeg", ImageType.JPEG);
        imagePlaystation_2.setData("06_playstation5.jpeg");
        playstation.addImage(imagePlaystation_2);
        productStore.save(playstation);

        Product game1 = new Product("Resident Evil Village", 49.99, 25, "Für Playstation 5", CategoryType.GAMES);
        game1.addCategory(categoryGames);
        game1.addCategory(categoryTechnik);
        Image imageGame1 = new Image("07_residentevil.jpeg", ImageType.JPEG);
        imageGame1.setData("07_residentevil.jpeg");
        game1.addImage(imageGame1);
        productStore.save(game1);

        Product game2 = new Product("Crusader Kings III", 18.99, 12, "Für PC", CategoryType.GAMES);
        game2.addCategory(categoryGames);
        game2.addCategory(categoryTechnik);
        Image imageGame2 = new Image("08_crusaderkings.jpeg", ImageType.JPEG);
        imageGame2.setData("08_crusaderkings.jpeg");
        game2.addImage(imageGame2);
        productStore.save(game2);

        Product game3 = new Product("Pokemon: Strahlender Diamant", 49.99, 9, "Für Nintendo-Switch", CategoryType.GAMES);
        game3.addCategory(categoryGames);
        game3.addCategory(categoryTechnik);
        Image imageGame3 = new Image("09_pokemon.jpeg", ImageType.JPEG);
        imageGame3.setData("09_pokemon.jpeg");
        game3.addImage(imageGame3);
        productStore.save(game3);

        Product pc1 = new Product("ASUS Zenbook Pro 15,6\"", 1105.00, 5, "ASUS Zenbook Pro 15,6\" FHD i7-10870H 16GB/512GB SSD GTX1650 Win10 UX535LH-BN150T", CategoryType.GAMES);
        pc1.addCategory(categoryComputer);
        pc1.addCategory(categoryTechnik);
        Image imagePc1_1 = new Image("10_asuszenbook.jpeg", ImageType.JPEG);
        imagePc1_1.setData("10_asuszenbook.jpeg");
        pc1.addImage(imagePc1_1);
        Image imagePc1_2 = new Image("11_asuszenbook.jpeg", ImageType.JPEG);
        imagePc1_2.setData("11_asuszenbook.jpeg");
        pc1.addImage(imagePc1_2);
        Image imagePc1_3 = new Image("12_asuszenbook.jpeg", ImageType.JPEG);
        imagePc1_3.setData("12_asuszenbook.jpeg");
        pc1.addImage(imagePc1_3);
        Image imagePc1_4 = new Image("13_asuszenbook.jpeg", ImageType.JPEG);
        imagePc1_4.setData("13_asuszenbook.jpeg");
        pc1.addImage(imagePc1_4);
        productStore.save(pc1);

        Product pc2 = new Product("Surface Pro 8", 1538.00, 3, "Microsoft Surface Pro 8, 13 Zoll 2-in-1 Tablet (Intel Core i5, 8GB RAM, 128GB SSD, Win 11 Home) Platin Grau", CategoryType.COMPUTER);
        pc2.addCategory(categoryComputer);
        pc2.addCategory(categoryTechnik);
        Image imagePc2_1 = new Image("14_surface8.jpeg", ImageType.JPEG);
        imagePc2_1.setData("14_surface8.jpeg");
        pc2.addImage(imagePc2_1);
        Image imagePc2_2 = new Image("15_surface8.jpeg", ImageType.JPEG);
        imagePc2_2.setData("15_surface8.jpeg");
        pc2.addImage(imagePc2_2);
        Image imagePc2_3 = new Image("16_surface8.jpeg", ImageType.JPEG);
        imagePc2_3.setData("16_surface8.jpeg");
        pc2.addImage(imagePc2_3);
        Image imagePc2_4 = new Image("17_surface8.jpeg", ImageType.JPEG);
        imagePc2_4.setData("17_surface8.jpeg");
        pc2.addImage(imagePc2_4);
        productStore.save(pc2);

        Product pc3 = new Product("HP Chromebook x2", 499.00, 7, "Mit seinem ultraflachen Format ist das HP Chromebook x2 der ideale Begleiter, wohin es dich auch zieht. Mit Qualcomm Snapdragon 7c 4 GB RAM, 64 GB Speicher", CategoryType.COMPUTER);
        pc3.addCategory(categoryComputer);
        pc3.addCategory(categoryTechnik);
        Image imagePc3_1 = new Image("18_chromebook.jpeg", ImageType.JPEG);
        imagePc3_1.setData("18_chromebook.jpeg");
        pc3.addImage(imagePc3_1);
        Image imagePc3_2 = new Image("19_chromebook.jpeg", ImageType.JPEG);
        imagePc3_2.setData("19_chromebook.jpeg");
        pc3.addImage(imagePc3_2);
        Image imagePc3_3 = new Image("20_chromebook.jpeg", ImageType.JPEG);
        imagePc3_3.setData("20_chromebook.jpeg");
        pc3.addImage(imagePc3_3);
        productStore.save(pc3);

        Product tv = new Product("LG signature OLED88Z19LA OLED TV", 29999.00, 1, "LG signature OLED88Z19LA OLED TV (Flat, 88 Zoll / 222 cm, UHD 8K, SMART TV, webOS 6.0 mit LG ThinQ)", CategoryType.FERNSEHER_UND_HEIMKINO);
        tv.addCategory(categoryFernseherHeimkino);
        tv.addCategory(categoryTechnik);
        Image imageTv_1 = new Image("21_lgfernseher.png", ImageType.JPEG);
        imageTv_1.setData("21_lgfernseher.png");
        tv.addImage(imageTv_1);
        Image imageTv_2 = new Image("22_lgfernseher.png", ImageType.JPEG);
        imageTv_2.setData("22_lgfernseher.png");
        tv.addImage(imageTv_2);
        productStore.save(tv);

        System.out.println("Produkte hinzugefügt und in der Datenbank gespeichert");


        User user = new User("user");
        userStore.save(user);

        Address address1 = new Address(
                "Mustermann",
                "Max",
                "Apfelstraße 12",
                "Birnenhausen",
                "12345",
                "Deutschland",
                "01234 123456789",
                "mustermann.max@mail.com"
        );
        Address address2 = new Address(
                "Mustermann",
                "Matthilda",
                "Birnengasse 33",
                "Birnenhausen",
                "12345",
                "Deutschland",
                "01234 987654321",
                "mustermann.matthilda@nachrichten.de"
        );
        user.addAddress(address1);
        user.addAddress(address2);
        userStore.update(user);

        System.out.println("Testuser wurden in der Datenbank gespeichert");

        Ordering order1 = new Ordering(
                user.getAddressList().get(0),
                user.getAddressList().get(1),
                "Erste Bestellung :)");

        user.addOrder(order1);
        orderStore.save(order1);


        order1.addOrderedProduct(new OrderedProduct(order1, smartphone1,1));

        order1.addOrderedProduct( new OrderedProduct(order1, smartphone1,1));

//        order1.addOrderedProduct(new OrderedProduct(order1, smartphone2,1));

//        order1.setOrderStateToOrder();
//
//        order1.addOrderedProduct( new OrderedProduct(order1, tv,1));
//
//        order1.addOrderedProduct(new OrderedProduct(order1, pc2,1));


        orderStore.update(order1);

        System.out.println(        user.getOrderingList().get(0).getOrderedProductList().size());






//        //Ein leerer "Warenkorb"
//        Orders order2 = new Orders();
//        ordersService.saveOrder(order1);
//        ordersService.saveOrder(order2);
//
//        System.out.println("Beispielbestellung angelegt");
//
//        user.addOrder(order1);
//        user.addOrder(order2);
//        userService.saveUser(user);
//
//        System.out.println("Testuser Bestellung zugeordnet und User aktualisiert");


    }
}
