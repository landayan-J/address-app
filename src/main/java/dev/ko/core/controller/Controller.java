package dev.ko.core.controller;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import dev.ko.App;

public abstract class Controller {
    
    protected App app;
    protected List<Serializable> params;

    public void load(App app){
        load(app, new LinkedList<>());
    }

    public void load(App app, List<Serializable> params) {
        this.app = app;
        this.params = params;

        load_fields();
        load_bindings();
        load_listeners();
    }
    protected Serializable getParameter(int index){
        return params.get(index);
    }
    protected abstract void load_fields();

    protected abstract void load_bindings();

    protected abstract void load_listeners();
}
