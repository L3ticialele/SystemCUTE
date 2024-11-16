package gui3d;
import javafx.scene.shape.TriangleMesh;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class OBJLoader {

    public static TriangleMesh load(String objFilePath) throws IOException {
        TriangleMesh mesh = new TriangleMesh();

        List<Float> vertices = new ArrayList<>();
        List<Integer> faces = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(objFilePath));
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.startsWith("v ")) {
                // Vertices
                String[] parts = line.split("\\s+");
                float x = Float.parseFloat(parts[1]);
                float y = Float.parseFloat(parts[2]);
                float z = Float.parseFloat(parts[3]);
                vertices.add(x);
                vertices.add(y);
                vertices.add(z);
            } else if (line.startsWith("f ")) {
                // Faces (triangular faces only, ignoring texture and normal indices)
                String[] parts = line.split("\\s+");
                for (int i = 1; i <= 3; i++) {
                    String[] vertexData = parts[i].split("/");
                    int vertexIndex = Integer.parseInt(vertexData[0]) - 1; // vertex index
                    faces.add(vertexIndex);
                    faces.add(0);  // texture coordinate index (not used)
                }
            }
        }

        reader.close();

        // Convertendo listas para arrays primitivos
        float[] verticesArray = new float[vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            verticesArray[i] = vertices.get(i);
        }

        int[] facesArray = new int[faces.size()];
        for (int i = 0; i < faces.size(); i++) {
            facesArray[i] = faces.get(i);
        }

        // Definindo os pontos, coordenadas de textura e faces do TriangleMesh
        mesh.getPoints().setAll(verticesArray);
        mesh.getTexCoords().addAll(0, 0); // Definindo uma coordenada de textura padrão

        mesh.getFaces().setAll(facesArray);

        return mesh;
    }

}


