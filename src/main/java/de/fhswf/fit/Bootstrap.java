package de.fhswf.fit;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Startup
@Singleton
public class Bootstrap {

//    @Inject
//    private RecipeStore recipeStore;

    @PostConstruct
    public void init(){

    }
}
