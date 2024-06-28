package com.aerospace.gui3d;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.geometry.Bounds;

public class Model3D {

    private Group root;
    private PerspectiveCamera camera;
    private MeshView meshView;

    public MeshView getMeshView() {
        return meshView;
    }

    public void setMeshView(MeshView meshView) {
        this.meshView = meshView;
    }
    private final double cameraSpeed = 5.0;
    private final double minDistance = 30.0;
    private double rotateX = 0;
    private double rotateY = 0;
    private double rotateZ = 0;

    public Model3D() {
        root = new Group();
        initCamera();
        initScene();
        initMesh();
    }

    private void initCamera() {
        camera = new PerspectiveCamera(true);
        camera.setNearClip(1);
        camera.setFarClip(1000);
        camera.setTranslateY(0); // Ajuste para posicionar a câmera como desejado
        camera.setTranslateX(0); // Ajuste para posicionar a câmera como desejado
        camera.setTranslateZ(-465); // Afastando a câmera para trás
    }

    private void initScene() {
        Scene scene = new Scene(root, 600, 600, true);
        scene.setFill(Color.BLACK);
        scene.setCamera(camera);

        scene.setOnKeyPressed(event -> handleKeyPressed(event));
    }

    private void initMesh() {
        TriangleMesh mesh = null;
        try {
            Path currentDir = Paths.get(System.getProperty("user.dir"));
            Path filePath = currentDir.resolve("src/main/resources/com/aerospace/gui3d/assets/obj/CubesatSTL.obj");
            mesh = OBJLoader.load(filePath.toString());

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        meshView = new MeshView(mesh);
        adjustPositionToCenter(meshView);

        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.WHITE);
        meshView.setMaterial(material);

        root.getChildren().add(meshView);
    }

    private void adjustPositionToCenter(MeshView meshView) {
        Bounds bounds = meshView.getBoundsInLocal();
        double centerX = (bounds.getMaxX() + bounds.getMinX()) / 2;
        double centerY = (bounds.getMaxY() + bounds.getMinY()) / 2;
        double centerZ = (bounds.getMaxZ() + bounds.getMinZ()) / 2;

        meshView.setTranslateX(meshView.getTranslateX() - centerX);
        meshView.setTranslateY(meshView.getTranslateY() - centerY);
        meshView.setTranslateZ(meshView.getTranslateZ() - centerZ);
    }

    public void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                moveCameraForward();
                break;
            case S:
                moveCameraBackward();
                break;
            case A:
                moveCameraLeft();
                break;
            case D:
                moveCameraRight();
                break;
            case Q:
                moveCameraUp();
                break;
            case E:
                moveCameraDown();
                break;
            case NUMPAD8:
                rotateModel(5.0, 0, 0);
                break;
            case NUMPAD2:
                rotateModel(-5.0, 0, 0);
                break;
            case NUMPAD4:
                rotateModel(0, 5.0, 0);
                break;
            case NUMPAD6:
                rotateModel(0, -5.0, 0);
                break;
            case NUMPAD7:
                rotateModel(0, 0, 5.0);
                break;
            case NUMPAD9:
                rotateModel(0, 0, -5.0);
                break;
            default:
                break;
        }

        System.out.println("Camera Position: X=" + camera.getTranslateX()
                + ", Y=" + camera.getTranslateY()
                + ", Z=" + camera.getTranslateZ());
        System.out.println("Model Rotation: X=" + rotateX
                + ", Y=" + rotateY
                + ", Z=" + rotateZ);
    }

    public void moveCameraForward() {
        camera.setTranslateZ(camera.getTranslateZ() + cameraSpeed);
        limitCameraDistance();
    }

    public void moveCameraBackward() {
        camera.setTranslateZ(camera.getTranslateZ() - cameraSpeed);
        limitCameraDistance();
    }

    public void moveCameraLeft() {
        camera.setTranslateX(camera.getTranslateX() - cameraSpeed);
        limitCameraDistance();
    }

    public void moveCameraRight() {
        camera.setTranslateX(camera.getTranslateX() + cameraSpeed);
        limitCameraDistance();
    }

    public void moveCameraUp() {
        camera.setTranslateY(camera.getTranslateY() - cameraSpeed);
        limitCameraDistance();
    }

    public void moveCameraDown() {
        camera.setTranslateY(camera.getTranslateY() + cameraSpeed);
        limitCameraDistance();
    }

    private void limitCameraDistance() {
        double distanceToModel = Math.sqrt(
                camera.getTranslateX() * camera.getTranslateX()
                + camera.getTranslateY() * camera.getTranslateY()
                + camera.getTranslateZ() * camera.getTranslateZ());

        if (distanceToModel < minDistance) {
            camera.setTranslateX(camera.getTranslateX() * minDistance / distanceToModel);
            camera.setTranslateY(camera.getTranslateY() * minDistance / distanceToModel);
            camera.setTranslateZ(camera.getTranslateZ() * minDistance / distanceToModel);
        }
    }

    public void rotateModel(double angleX, double angleY, double angleZ) {
        rotateX += angleX;
        rotateY += angleY;
        rotateZ += angleZ;

        meshView.getTransforms().clear();
        meshView.getTransforms().addAll(
                new Rotate(rotateX, meshView.getBoundsInLocal().getCenterX(), meshView.getBoundsInLocal().getCenterY(), meshView.getBoundsInLocal().getCenterZ(), Rotate.X_AXIS),
                new Rotate(rotateY, meshView.getBoundsInLocal().getCenterX(), meshView.getBoundsInLocal().getCenterY(), meshView.getBoundsInLocal().getCenterZ(), Rotate.Y_AXIS),
                new Rotate(rotateZ, meshView.getBoundsInLocal().getCenterX(), meshView.getBoundsInLocal().getCenterY(), meshView.getBoundsInLocal().getCenterZ(), Rotate.Z_AXIS)
        );
    }

    public Group getRoot() {
        return root;
    }

    public PerspectiveCamera getCamera() {
        return camera;
    }
}
