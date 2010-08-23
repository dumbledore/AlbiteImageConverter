/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.albite.image.converter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author albus
 */
public class ImageConverter {
    public static final int MAGIC_NUMBER_ALBI = 1095516745;
    public static final String FILE_EXTENSION = ".ali";

    private static String IMAGE_ERROR_MSG = "ERROR. Cannot build image.\n";

    private ImageConverter() {}

    public static void fromPNG(File fileIn, String fileOutName,
            boolean printOutput) {

       Printer.println("Building file " + fileIn.getName(), printOutput);

        /* try loading the image */
        try {
            BufferedImage image = ImageIO.read(fileIn);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream data = new DataOutputStream(baos);

            /* writing magic number */
            data.writeInt(MAGIC_NUMBER_ALBI);

            /* loading the bitmap data */
            int[] rgbData = new int[image.getWidth() * image.getHeight()];
            image.getRGB(0, 0, image.getWidth(), image.getHeight(),
                    rgbData, 0, image.getWidth());

            /* writing file dimensions */
            data.writeShort(image.getWidth());
            data.writeShort(image.getHeight());

            /* processing alpha and writing as RAW */
            for (int i=0; i<rgbData.length; i++) {
                int alpha = rgbData[i] >> 24;
                data.writeByte(alpha);
            }

            /* opening output */
            FileOutputStream fo =
                    new FileOutputStream(fileOutName);
            try {
                DataOutputStream dataOut = new DataOutputStream(fo);
                dataOut.write(baos.toByteArray());
            } finally {
                fo.close();
            }
        } catch (IOException ioe) {
            Printer.println(IMAGE_ERROR_MSG, printOutput);
        }
    }
}
