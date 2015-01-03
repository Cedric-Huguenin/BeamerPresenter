/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxbeamerpresenter.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ced
 */
public class PDFModel {

    private final List<PropertyChangeListener> listeners = new ArrayList<>();

    private String pdfPath;
    private String notesPath;
    private int page;

    public PDFModel(String pdfPath, String notesPath, int page) {
        this.pdfPath = pdfPath;
        this.notesPath = notesPath;
        this.page = page;
    }

    public PDFModel() {
        this.pdfPath = "D:\\Ced\\Documents\\Git-repo\\FxBeamerPresenter\\beamer.pdf";
        this.notesPath = "D:\\Ced\\Documents\\Git-repo\\FxBeamerPresenter\\notes.pdf";
        this.page = 1;
    }

    public void incrPage() {
        setPage(page + 1);
    }

    public void decrPage() {
        setPage(page - 1 >= 1 ? page - 1 : 1);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        listeners.add(listener);
    }

    private void firePropertyChange(String property, Object oldValue, Object newValue) {
        listeners.stream().forEach((l) -> {
            l.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
        });
    }

    /**
     * @return the pdfPath
     */
    public String getPdfPath() {
        return pdfPath;
    }

    /**
     * @param pdfPath the pdfPath to set
     */
    public void setPdfPath(String pdfPath) {
        String oldValue = this.pdfPath;
        this.pdfPath = pdfPath;
        firePropertyChange("pdfPath", oldValue, pdfPath);
    }

    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(int page) {
        int oldValue = this.page;
        this.page = page;
        firePropertyChange("page", oldValue, page);
    }

    /**
     * @return the notesPath
     */
    public String getNotesPath() {
        return notesPath;
    }

    /**
     * @param notesPath the notesPath to set
     */
    public void setNotesPath(String notesPath) {
        String oldValue = this.notesPath;
        this.notesPath = notesPath;
        firePropertyChange("notesPath", oldValue, notesPath);
    }
}
