package config;

import toolBox.FileHelper;

import java.io.File;

public abstract class Config {

            //Attribute


            //Referenzen
        protected File file;

    public Config(File file) {

        this.file = file;
        if(FileHelper.isFileExisting(file)) setStandards();
        readConfig();
    }

    /**
     * In dieser Methode werden die Config-Werte gespeichert
     */
    public abstract void save();

    /**
     * In dieser Methode werden die Config-Werte ausgelesen
     */
    public abstract void readConfig();

    /**
     * Falls die Config nicht existiert werden in dieser Methode die Standarts festgelegt
     */
    public abstract void setStandards();
}
