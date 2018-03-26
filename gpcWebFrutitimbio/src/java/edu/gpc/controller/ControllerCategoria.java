package edu.gpc.controller;

//package entidadesJSF;
//
//import controlador.CategoriaFacade;
//import entidades.Categoria;
//import java.io.Serializable;
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
//import entidadesJSF.LazyDataModelBase;
//
//@ManagedBean(name = "controllerCat")
//@ViewScoped
//
//public class ControllerCategoria extends abstractController <Categoria> implements Serializable {
//
//    @EJB
//    private CategoriaFacade ejbFacade;
//    
//    public ControllerCategoria() {
//        super(Categoria.class);
//    }
//    
//    @PostConstruct
//    public void init() {
//        super.setFacade(ejbFacade);
//    }
//    
//    private LazyDataModelBase<Categoria> lazyModel = new LazyDataModelBase<Categoria>(ejbFacade);
// 
//    public void setLazyModel(LazyDataModelBase<Categoria> lazyModel) {
//        this.lazyModel = lazyModel;
//    }
//
//    public LazyDataModelBase<Categoria> getLazyModel() {
//        lazyModel.setFacade(ejbFacade);
//        return lazyModel;
//    }
//
//    public CategoriaFacade getAtorFacade() {
//        return ejbFacade;
//    }
//
//    public void setAtorFacade(CategoriaFacade ejbFacade) {
//        this.ejbFacade = ejbFacade;
//        lazyModel.setFacade(ejbFacade);
//    }
//}
