package civitas.celestis;

import civitas.celestis.geometry.ColoredVertex;
import civitas.celestis.geometry.LightRay;
import civitas.celestis.graphics.Scene;
import civitas.celestis.number.Vector;

import java.awt.*;

public final class RaytracingTest {
    public static void main(String[] args) {
        final ColoredVertex vertex = new ColoredVertex(
                new Vector(0, 0, 0),
                new Vector(100, 50, 100),
                new Vector(0, 50, 100),
                Color.RED
        );

        final LightRay ray = new LightRay(new Vector(50, 1000, 50), Vector.NEGATIVE_Y, 10);

        final Scene scene = new Scene();

        scene.addVertex(vertex);

        System.out.println(vertex.color());
        scene.shootLightRay(ray, 10);
        System.out.println(vertex.color());
    }
}
