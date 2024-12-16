package br.cefetmg.space.view;

import gui3d.Model3D;
import gui3d.Updater;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;

public class Renderizador3D {

    public static MeshView loadOBJ(String filePath) throws IOException {
        TriangleMesh mesh = new TriangleMesh();
        List<Float> vertices = new ArrayList<>();
        List<Float> texCoords = new ArrayList<>();
        List<Float> normals = new ArrayList<>();
        List<Integer> faces = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                switch (parts[0]) {
                    case "v":
                        vertices.add(Float.parseFloat(parts[1]));
                        vertices.add(Float.parseFloat(parts[2]));
                        vertices.add(Float.parseFloat(parts[3]));
                        break;
                    case "vt":
                        texCoords.add(Float.parseFloat(parts[1]));
                        texCoords.add(Float.parseFloat(parts[2]));
                        break;
                    case "vn":
                        normals.add(Float.parseFloat(parts[1]));
                        normals.add(Float.parseFloat(parts[2]));
                        normals.add(Float.parseFloat(parts[3]));
                        break;
                    case "f":
                        for (int i = 1; i < parts.length; i++) {
                            String[] indices = parts[i].split("/");
                            faces.add(Integer.parseInt(indices[0]) - 1); // Vertex index
                        }
                        break;
                }
            }
        }

        float[] vertexArray = new float[vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            vertexArray[i] = vertices.get(i);
        }

        mesh.getPoints().addAll(vertexArray);

        MeshView meshView = new MeshView(mesh);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.BLUE); 
        meshView.setMaterial(material);

        return meshView;
    }

    public static void renderizar3D(Group root) {
        if (root == null) {
            throw new IllegalArgumentException("O grupo não pode ser nulo.");
        }

        Model3D model3D = new Model3D();
        MeshView meshView = model3D.getMeshView();

        root.getChildren().add(meshView);

        // Configuração da câmera
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setNearClip(1);
        camera.setFarClip(1000);
        camera.setTranslateY(0);
        camera.setTranslateX(0);
        camera.setTranslateZ(-465);

        Scene scene = new Scene(root, 800, 600, true);
        scene.setFill(Color.LIGHTGRAY);
        scene.setCamera(camera);

        // Configuração inicial do objeto
        meshView.setScaleX(2);
        meshView.setScaleY(2);
        meshView.setScaleZ(2);
        meshView.setTranslateX(210);
        meshView.setTranslateY(150);
        meshView.setTranslateZ(0);

        // Transformações de rotação
        Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
        Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);
        meshView.getTransforms().addAll(rotateX, rotateY);

        // Animação de rotação
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                rotateX.setAngle(rotateX.getAngle() + 0.5); // Rotação no eixo X
                rotateY.setAngle(rotateY.getAngle() + 0.7); // Rotação no eixo Y
            }
        };
        timer.start();
    }
}
