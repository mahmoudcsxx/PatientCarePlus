package org.netbeans.lib.awtextra;

import java.awt.Dimension;
import java.awt.Point;
import java.io.Serializable;

public class AbsoluteConstraints implements Serializable {

    public final int x;
    public final int y;
    public final int width;
    public final int height;

    public AbsoluteConstraints(Point position) {
        this(position.x, position.y, -1, -1);
    }

    public AbsoluteConstraints(int x, int y) {
        this(x, y, -1, -1);
    }

    public AbsoluteConstraints(Point position, Dimension size) {
        this(position.x, position.y, size.width, size.height);
    }

    public AbsoluteConstraints(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
