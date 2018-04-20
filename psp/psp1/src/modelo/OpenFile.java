/**
 * @author: Gerson Morera Restrepo
 * @version: 17/04/2018
 * @Descripcion: Clase modelo que permite controlar el componente de abrir
 * directorios
 */
package modelo;

import java.io.File;
import javax.swing.JFileChooser;

public class OpenFile {

    private static OpenFile instance = null;

    /**
     * @param aInstance the instance to set
     */
    public static void setInstance(OpenFile aInstance) {
        instance = aInstance;
    }
    private JFileChooser fileChooser = new JFileChooser();
    private StringBuilder sb = new StringBuilder();
    private File file;

    /**
     * @Descripcion: constuctor que informa que se van a leer directorios
     * solamente
     */
    protected OpenFile() {
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    public static OpenFile getInstance() {
        if (instance == null) {
            instance = new OpenFile();
        }
        return instance;
    }

    /**
     * @Descripcion: metodo encargo de guardar el file seleccionado
     */
    public void PickMe() throws Exception {
        if (getFileChooser().showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            setFile(getFileChooser().getSelectedFile());
        } else {
            getSb().append("no se encontro");
        }
    }

    /**
     * @return the fileChooser
     */
    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    /**
     * @param fileChooser the fileChooser to set
     */
    public void setFileChooser(JFileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }

    /**
     * @return the sb
     */
    public StringBuilder getSb() {
        return sb;
    }

    /**
     * @param sb the sb to set
     */
    public void setSb(StringBuilder sb) {
        this.sb = sb;
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(File file) {
        this.file = file;
    }
}
