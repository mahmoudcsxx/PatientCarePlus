package org.netbeans.lib.awtextra;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager2;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AbsoluteLayout implements LayoutManager2, Serializable {

    private final Map<Component, AbsoluteConstraints> constraints = new HashMap<>();

    @Override
    public void addLayoutComponent(String name, Component comp) {
        constraints.putIfAbsent(comp, new AbsoluteConstraints(0, 0));
    }

    @Override
    public void addLayoutComponent(Component comp, Object value) {
        if (value instanceof AbsoluteConstraints absoluteConstraints) {
            constraints.put(comp, absoluteConstraints);
        } else {
            constraints.put(comp, new AbsoluteConstraints(0, 0));
        }
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        constraints.remove(comp);
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return computeLayoutSize(parent, false);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return computeLayoutSize(parent, true);
    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    @Override
    public void layoutContainer(Container parent) {
        for (Component component : parent.getComponents()) {
            AbsoluteConstraints absoluteConstraints = constraints.get(component);
            if (absoluteConstraints == null) {
                continue;
            }

            Dimension preferred = component.getPreferredSize();
            int width = absoluteConstraints.width >= 0 ? absoluteConstraints.width : preferred.width;
            int height = absoluteConstraints.height >= 0 ? absoluteConstraints.height : preferred.height;

            component.setBounds(absoluteConstraints.x, absoluteConstraints.y, width, height);
        }
    }

    @Override
    public float getLayoutAlignmentX(Container target) {
        return 0.0f;
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0.0f;
    }

    @Override
    public void invalidateLayout(Container target) {
        // No cached layout state to invalidate.
    }

    private Dimension computeLayoutSize(Container parent, boolean minimum) {
        int maxWidth = 0;
        int maxHeight = 0;

        for (Component component : parent.getComponents()) {
            AbsoluteConstraints absoluteConstraints = constraints.get(component);
            if (absoluteConstraints == null) {
                continue;
            }

            Dimension size = minimum ? component.getMinimumSize() : component.getPreferredSize();
            int width = absoluteConstraints.width >= 0 ? absoluteConstraints.width : size.width;
            int height = absoluteConstraints.height >= 0 ? absoluteConstraints.height : size.height;

            maxWidth = Math.max(maxWidth, absoluteConstraints.x + width);
            maxHeight = Math.max(maxHeight, absoluteConstraints.y + height);
        }

        return new Dimension(maxWidth, maxHeight);
    }
}
