package graphics.interfaces;

import toolBox.DrawHelper;

    public interface GraphicalObject {

        void update(double delta);

        void draw(DrawHelper draw);
    }
