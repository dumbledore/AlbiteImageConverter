/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.io.File;
import org.albite.image.converter.ExtensionFileFilter;
import org.albite.image.converter.ImageConverter;
import org.albite.image.converter.Printer;

/**
 *
 * @author Albus Dumbledore
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String workingDirectoryName = System.getProperty("user.dir");

        Printer.println("Converting images...", true);

        File workingDirectory = new File(workingDirectoryName);
        File[] files = workingDirectory.listFiles(
                new ExtensionFileFilter("png"));

        for (int fi=0; fi<files.length; fi++) {
            File fileIn = files[fi];
            String fileOutName = fileIn.getPath().substring(0,
                    fileIn.getPath().lastIndexOf('.')) + ImageConverter.FILE_EXTENSION;
            ImageConverter.fromPNG(fileIn, fileOutName, true);
        }
    }
}
