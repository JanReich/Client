package toolBox;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

    public class ImageHelper {

                //Attribute

                //Referenzen

        public static BufferedImage getImage(String path) {

            BufferedImage image = null;

            try {

                image = ImageIO.read(new File(path));
            } catch (Exception e) {

                e.printStackTrace();
            }
            return image;
        }
    }
