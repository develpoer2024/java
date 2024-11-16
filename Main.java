import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Main Group to hold all shapes
        Group root = new Group();

        // Create the central sphere (red)
        Sphere centralSphere = new Sphere(50);
        PhongMaterial redMaterial = new PhongMaterial(Color.RED);
        centralSphere.setMaterial(redMaterial);
        centralSphere.setTranslateX(250);
        centralSphere.setTranslateY(250);

        // Array to hold the outer spheres and connecting cylinders
        Sphere[] outerSpheres = new Sphere[4];
        Cylinder[] connectors = new Cylinder[4];

        // Materials for outer spheres and connectors
        PhongMaterial blueMaterial = new PhongMaterial(Color.BLUE);
        PhongMaterial greenMaterial = new PhongMaterial(Color.GREEN);

        // Position and create outer spheres and connectors
        for (int i = 0; i < 4; i++) {
            // Create outer spheres
            outerSpheres[i] = new Sphere(25);
            outerSpheres[i].setMaterial(blueMaterial);

            // Set the position of the outer spheres
            double xOffset = (i % 2 == 0) ? 150 : -150;
            double yOffset = (i < 2) ? 150 : -150;

            outerSpheres[i].setTranslateX(250 + xOffset);
            outerSpheres[i].setTranslateY(250 + yOffset);

            // Create connectors
            connectors[i] = new Cylinder(10, 150);
            connectors[i].setMaterial(greenMaterial);

            // Set position and rotation of connectors
            connectors[i].setTranslateX(250 + xOffset / 2);
            connectors[i].setTranslateY(250 + yOffset / 2);
            connectors[i].setRotationAxis(javafx.geometry.Point3D.ZERO.add(xOffset, yOffset, 0));
            connectors[i].setRotate(45 * (i + 1));

            // Add to root
            root.getChildren().addAll(outerSpheres[i], connectors[i]);
        }

        // Add the central sphere to root
        root.getChildren().add(centralSphere);

        // Set up a light source
        PointLight light = new PointLight();
        light.setTranslateX(250);
        light.setTranslateY(250);
        light.setTranslateZ(-300);
        root.getChildren().add(light);

        // Set up camera
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-500);

        // Create the scene and add root
        Scene scene = new Scene(root, 500, 500, true);
        scene.setFill(Color.LIGHTGRAY);
        scene.setCamera(camera);

        // Set up the stage
        primaryStage.setTitle("3D Transformations");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
