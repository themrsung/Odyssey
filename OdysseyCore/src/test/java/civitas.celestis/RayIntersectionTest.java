package civitas.celestis;

import civitas.celestis.geometry.ColoredVertex;
import civitas.celestis.geometry.LightRay;
import civitas.celestis.geometry.Ray;
import civitas.celestis.geometry.Vertex;
import civitas.celestis.number.Vector;

import java.awt.*;

public class RayIntersectionTest {
    public static void main(String[] args) {
        final Vertex vertex = new ColoredVertex(
                new Vector(0, 0, 0),
                new Vector(100, 0, 0),
                new Vector(0, 0, 100),
                Color.CYAN
        );

        final Ray ray = new LightRay(
                new Vector(50, 1000, 50),
                Vector.NEGATIVE_Y,
                100
        );

        System.out.println(ray.reflection(vertex));
    }
}
