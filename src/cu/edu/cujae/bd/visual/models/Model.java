package cu.edu.cujae.bd.visual.models;

import cu.edu.cujae.bd.visual.views.ViewFactory;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;

    private Model(){
        this.viewFactory = new ViewFactory();
    }

    public static synchronized Model getInstanse(){
        if(model == null){
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory(){
        return this.viewFactory;
    }
}
