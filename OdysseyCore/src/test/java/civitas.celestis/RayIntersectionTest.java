package civitas.celestis;

import civitas.celestis.geometry.vertex.ColoredVertex;
import civitas.celestis.geometry.ray.LightRay;
import civitas.celestis.geometry.ray.Ray;
import civitas.celestis.geometry.vertex.Vertex;
import civitas.celestis.number.Vector3;

import java.awt.*;

public class RayIntersectionTest {
    public static void main(String[] args) {
        final Vertex vertex = new ColoredVertex(
                new Vector3(0, 0, 0),
                new Vector3(100, 0, 0),
                new Vector3(0, 0, 100),
                Color.CYAN
        );

        final Ray ray = new LightRay(
                new Vector3(50, 1000, 50),
                Vector3.NEGATIVE_Y,
                100
        );

        System.out.println(ray.reflection(vertex));
    }
}
