package toolBox;

import graphics.interfaces.TimeBasedObject;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

    public class Animation implements TimeBasedObject {

                //Attribute
            private double time;
            private double duration;

            private int currentAnimation;

            private boolean stopped;

                //Referenzen
            private SpriteSheet sprite;
            private ArrayList<BufferedImage> frames;

        public Animation(SpriteSheet sprite, double duration) {

            this(sprite, duration, true);
        }

        public Animation(SpriteSheet sprite, double duration, boolean stopped) {

            this.sprite = sprite;
            this.stopped = stopped;
            this.duration = duration;

            initImpl();
        }

        private void initImpl() {

            if(frames == null) {

                frames = new ArrayList<>();

                for(int col = 0; col < sprite.getTilesAcross(); col++) {

                    for(int row = 0; row < sprite.getTilesDown(); row++) {

                        frames.add(sprite.getSubImage(col, row));
                    }
                }
            }
        }

        @Override
        public void update(double delta) {

            if(!stopped) {

                time += delta;
                if (time >= duration) {

                    time = 0;
                    if (!(currentAnimation + 1 == frames.size())) currentAnimation++;
                    else currentAnimation = 0;
                }
            }
        }

        public void start() {

            if(stopped) stopped = !stopped;
        }

        public void restart() {

            if(stopped) {

                currentAnimation = 0;
                stopped = !stopped;
            }
        }

        public void stop() {

            if(!stopped) stopped = !stopped;
        }

        public BufferedImage getCurrentAnimation() {

            return frames.get(currentAnimation);
        }
    }
