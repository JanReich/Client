package toolBox;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteSheet {

                //Attribute
            private int tileWidth;  //Width - der verschiedenen Tiles
            private int tileHeight; //Height - der verschiedenen Tiles

            private int margin;     //Margin - Abstand (vorne || hinten)
            private int spacing;    //Spaceing - Abstand (zwischen den verschiedenen Tiles)

                //Referenzen
            private BufferedImage target;
            private BufferedImage[][] subImages;
            private ArrayList<BufferedImage> images;

        private SpriteSheet(BufferedImage image, int tileWidth, int tileHeight) {

            this.margin = 0;
            this.target = image;
            this.tileWidth = tileWidth;
            this.tileHeight = tileHeight;

            initImpl();
        }

        public SpriteSheet(BufferedImage image, int tileWidth, int tileHeight, int spacing, int margin) {

            this.target = image;
            this.tileWidth = tileWidth;
            this.tileHeight = tileHeight;

            this.margin = margin;
            this.spacing = spacing;

            initImpl();
        }

        public SpriteSheet(BufferedImage image, int tileWidth, int tileHeight, int spacing) {

            this(image, tileWidth, tileHeight, spacing, 0);
        }

        public SpriteSheet(String ref, String image, int amountOfFrames) {

            initImpl(ref, image, amountOfFrames);
        }

        private void initImpl() {

            int tilesAcross = (target.getWidth() - this.margin * 2 - this.tileWidth) / (this.tileWidth + this.spacing) + 1;
            int tilesDown = (target.getHeight() - this.margin * 2 - this.tileHeight) / (this.tileHeight + this.spacing) + 1;

            if(this.subImages == null) {

                if((target.getHeight() - this.tileHeight) % (this.tileHeight + this.spacing) != 0) {

                    ++tilesDown;
                }

                this.subImages = new BufferedImage[tilesAcross][tilesDown];

                for (int x = 0; x < tilesAcross; ++x) {

                    for(int y = 0; y < tilesDown; ++y) {

                        this.subImages[x][y] = this.getSprite(x, y);
                    }
                }
            }

            if(this.images == null) {

                images = new ArrayList();

                for (int x = 0; x < tilesDown; ++x) {

                    for(int y = 0; y < tilesAcross; ++y) {

                        images.add(this.getSprite(y, x));
                    }
                }
            }
        }

        private void initImpl(String ref, String image, int amountOfFrames) {

            if(this.subImages == null) {

                subImages = new BufferedImage[amountOfFrames][1];

                for (int i = 0; i < amountOfFrames; i++) {

                    subImages[i][0] = ImageHelper.getImage(ref + "/" + image + i + ".png");
                }
            }
        }

        private BufferedImage getSprite(int x, int y) {

            if((x >= 0 && x < this.subImages.length) && (y >= 0 && y < this.subImages[0].length)) {

                return target.getSubimage(x * tileWidth + margin + x * spacing, y * tileHeight, tileWidth, tileHeight + y * spacing);
            } else {

                throw new IllegalArgumentException("Das ausgewählte Sprite befindet sich nicht im Spritesheet: " + x + ", " + y);
            }
        }

        public BufferedImage getSubImage(int x, int y) {

            if (x >= 0 && x < this.subImages.length && y >= 0 && y < this.subImages[0].length) {

                return this.subImages[x][y];
            } else {

                System.out.println(subImages.length + ", " + subImages[0].length);
                throw new IllegalArgumentException("Das ausgewählte Sprite befindet sich nicht im Spritesheet: " + x + ", " + y);
            }
        }

        public BufferedImage getSubImage(int index) {

            return images.get(index);
        }

        public int getTileWidth() {

            return tileWidth;
        }

        public int getTileHeight() {

            return tileHeight;
        }

        public int getTilesDown() {

            return subImages[0].length;
        }

        public int getTilesAcross() {

            return subImages.length;
        }
    }