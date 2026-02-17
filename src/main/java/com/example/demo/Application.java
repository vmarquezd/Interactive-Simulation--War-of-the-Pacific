package com.example.demo;

//Imports
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Application extends javafx.application.Application {

    //declare variables
    private PerspectiveCamera camera;
    public AnchorPane anchorPaneMain;
    //private Image ShipImage = new Image(getClass().getResource("/Images/pirate_ship_00000.png").toExternalForm());
    private Double xPosM;
    private Double yPosM;
    private Double mBx = 0.0;
    private Double mBy = 0.0;
    Polygon markMovement = new Polygon();
    PlayerBoat playerOne = new PlayerBoat("peru", 30.0, 0.0,
            20.0, 50.0,
            40.0, 50.0);
    private Double theta;
    private double speed = 0;
    private boolean mouseIsJustClick;
    private double thetaD;
    private final int r = 75;
    public Scene scene;
    private final Rectangle limaPe = new Rectangle(100, 100, Color.YELLOW);
    private final Rectangle arica = new Rectangle(200, 200, Color.YELLOW);
    private final Rectangle santiagoCh = new Rectangle(200, 200, Color.YELLOW);
    private boolean canShoot = true;
    private boolean LeftShoot = true;
    private Circle bullet;
    private double thetaE = -Math.PI/2;
    private double thetaEstatic = -Math.PI/2;
    private double speedB = 20;
    private boolean chatUp = false;
    private final Text chat = new Text();
    private final Rectangle chatbox = new Rectangle(1000, 500, Color.WHITE);
    private Boolean canMove = true;
    private boolean oneTime = true;

    EnemyBoat chile1 = new EnemyBoat( 30.0, 0.0,
            20.0, 50.0,
            40.0, 50.0);
    EnemyBoat chile2 = new EnemyBoat(30.0, 0.0,
            20.0, 50.0,
            40.0, 50.0);
    EnemyBoat chile3 = new EnemyBoat(30.0, 0.0,
            20.0, 50.0,
            40.0, 50.0);
    private final Text healthBar = new Text();

    private final Text instructions = new Text("Explore the world: to move use your mouse and click, to shoot use \"z\" or \"x\"");

    private Boolean hitEnemyOneTime = false;

    private final String limaMes = "Here's a brief overview of Lima's role during the conflict:\n "+
            "\n"+
            "Initial Invasion: In 1881, Chile launched a full-scale invasion of Peru, which included an attack on Lima.\n" +
            " The Chilean forces, led by General Manuel Baquedano, quickly advanced southward, defeating Peruvian and Bolivian troops along the way.\n" +
            "\n" +
            "Defense of Lima: As the Chilean forces approached Lima, the Peruvian government and military authorities\n" +
            " made efforts to defend the city. They fortified strategic positions and mobilized troops to protect the capital.\n" +
            "However, due to the overwhelming superiority of the Chilean forces, the defense of Lima was ultimately unsuccessful.\n" +
            "\n" +
            "Occupation and Blockade: After the fall of Lima in January 1881, the city came under Chilean occupation.\n" +
            "Chile established a blockade on the city, cutting off supplies and isolating it from the rest of Peru.\n" +
            " This blockade had a severe impact on the population, leading to scarcity of food, medicine, and other essential resources.\n" +
            "\n" +
            "Seat of Government: Despite the occupation, Lima remained an important center for the Peruvian government.\n" +
            "Many government institutions, including the Congress and administrative bodies, continued their activities in exile within the city.\n" +
            "\n" +
            "Reconstruction and Resistance: After the war ended in 1884, Lima played a vital role in the post-war reconstruction efforts.\n" +
            "The city faced significant damage and destruction, and efforts were made to rebuild infrastructure, institutions, and the economy.\n" +
            "Lima also served as a focal point for post-war resistance movements against Chilean influence and control.\n" +
            "\n"+
            "Press F to quit...";

    private final String santiagoMes = "Here's an overview of Santiago's role in the conflict:\n" +
            "\n" +
            "Mobilization and Strategy: Santiago served as the political and military center of Chile during the war. The Chilean government, led by\n" +
            " President Aníbal Pinto, coordinated the mobilization of troops, resources, and strategic planning from the capital. \n" +
            "Santiago was instrumental in formulating military strategies and directing the overall campaign against Peru and Bolivia.\n" +
            "\n" +
            "Supply and Communication Hub: Santiago played a vital role as a supply and communication hub for the Chilean forces.\n" +
            " Troops, equipment, and provisions were gathered and dispatched from the capital to the frontlines. Santiago's transportation\n" +
            " infrastructure, such as railways, facilitated the movement of troops and supplies to the northern regions where the fighting took place.\n" +
            "\n" +
            "Economic Support: Santiago was the economic engine of Chile during the war. The city contributed significantly to financing \n" +
            "the conflict through taxation and fundraising efforts. The government relied on the economic resources generated in Santiago \n" +
            " sustain the war effort, including the funding of military operations, equipment acquisition, and payment of soldiers.\n" +
            "\n" +
            "War Propaganda and Media: Santiago housed many newspapers and media outlets, which played a critical role in shaping public opinion \n" +
            "and disseminating war propaganda. The media outlets in the capital conveyed Chile's perspective and sought to rally support for the war \n" +
            "effort among the population.\n" +
            "\n" +
            "Receiving War Spoils: As the conflict progressed, Santiago became a hub for receiving war spoils, including valuable resources and artifacts \n" +
            "captured from Peru and Bolivia. These spoils were often put on display in the city to showcase Chile's military successes and boost national pride.\n" +
            "\n" +
            "Post-War Reconstruction: After the war, Santiago played a central role in the post-war reconstruction efforts. The city saw significant growth\n" +
            " and development as it served as a focal point for rebuilding infrastructure, industries, and institutions that were damaged during the conflict.\n" +
            "\n" +
            "Press F to quit...";
    private final String aricaMes = "During the War of the Pacific, there were several significant maritime battle campaigns, including:\n" +
            "\n" +
            "1) Battle of Iquique (1879): Fought off the coast of Iquique, a port city in northern Chile, this battle saw the Chilean Navy engage the Peruvian Navy.\n" +
            " The most renowned episode of this battle was the heroic sacrifice of the Chilean warship \"Esmeralda\" \n" +
            "and its captain, Arturo Prat, who became a national hero.\n" +
            "\n" +
            "2) Battle of Angamos (1879): Occurring near the port of Angamos, this battle involved the Chilean Navy's pursuit and eventual capture \n" +
            " of the Peruvian ironclad \"Huáscar.\" The victory significantly weakened the Peruvian Navy's capabilities and marked a turning point in the war.\n" +
            "\n" +
            "3) Naval blockade and coastal bombardments: Throughout the war, the Chilean Navy imposed a naval blockade on the Peruvian and Bolivian coasts, \n" +
            "limiting their access to supplies and crippling their economies. Chilean naval forces also conducted coastal bombardments,\n" +
            " targeting key ports and fortifications to weaken their adversaries.\n" +
            "\n" +
            "4) Naval operations and landings: The Chilean Navy played a crucial role in facilitating amphibious landings and supporting ground operations. \n" +
            "They transported troops, provided fire support, and secured maritime supply lines for the Chilean forces as they advanced into enemy territory.\n" +
            "\n" +
            "These maritime battle campaigns were pivotal in shaping the outcome of the War of the Pacific,\n" +
            " resulting in Chile gaining significant territorial gains and asserting its dominance in the region.\n"+
            "\n" +
            "Press F to quit...";
    ChatGPTclient chatGPTclient = new ChatGPTclient("Insert-ApiKey");

    String[] questionsM = new String[3];
    String[] questionsAP = new String[3];
    String[] questionsH = new String[3];
    String[] questionsAN = new String[3];

    Location grau;
    Location artu;
    Location anib;
    Location hila;


    @Override
    public void start(Stage primaryStage) {
        //Declare String questions
        questionsM[0]="What were some of the notable naval strategies and tactics employed by " +
                "Miguel Grau during the War of the Pacific, particularly in his command of the ironclad Huáscar?";
        questionsM[1]="How did Miguel Grau's emphasis on humane treatment of prisoners and civilians set him apart as" +
                " a leader during the conflict, and how did this aspect of his character influence perceptions of him?";
        questionsM[2]="What was your contribution to the war?";
        grau = new Location(20.00,20.00,"Miguel Grau", "Mig", questionsM);
        questionsAP[0]="Arturo Prat's leadership and actions during the Battle of Iquique impact the " +
                "course of the War of the Pacific?";
        questionsAP[1]="Arturo Prat's early life and career that influenced his decision " +
                "to join the Chilean Navy and play a significant role in the conflict?";
        questionsAP[2]="What was your contribution to the war?";
        artu = new Location(20.00,20.00,"Arturo Prat", "Art", questionsAP);
        questionsH[0]="How did his presidency impact Bolivia's position in the war and its subsequent relationship with Chile and Peru?";
        questionsH[1]="Why did the pacific war started and what role did you had?";
        questionsH[2]="What was your contribution to the war?";
        hila = new Location(20.00,20.00,"Hilarión Daza", "Hil", questionsH);
        questionsAN[0]="President Aníbal Pinto's leadership and decision-making impact Chile's military and diplomatic " +
                " strategies during the early years of the War of the Pacific?";
        questionsAN[1]="In what ways did Aníbal Pinto's presidency and his role in the War of the Pacific influence" +
                "subsequent political developments and foreign relations in Chile?";
        questionsAN[2]="What was your contribution to the war?";
        anib = new Location(20.00,20.00,"Aníbal Pinto", "Ani", questionsAN);



        // Create the AnchorPane and add an object to it
        anchorPaneMain = new AnchorPane();
        anchorPaneMain.setPrefSize(5000, 5000);
        anchorPaneMain.getChildren().addAll(playerOne, chile1, chile2, chile3, limaPe, healthBar, grau, artu, anib, hila, markMovement , instructions, arica, santiagoCh);

        //Setup location position
        grau.Move(70.00, 200.00);
        anib.Move(200.00, 1500.00);
        artu.Move(150.00, 2000.00);
        hila.Move(1500.00, 700.00);

        //Setup Location Positions
        limaPe.setTranslateX(1375);
        limaPe.setTranslateY(1675);
        arica.setTranslateX(1850);
        arica.setTranslateY(2200);
        santiagoCh.setTranslateX(1850);
        santiagoCh.setTranslateY(3150);

        //Put Color To enemy
        chile1.setFill(Color.RED);
        chile2.setFill(Color.RED);
        chile3.setFill(Color.RED);

        //Move EnemyBoats to the inicial position
        chile1.MoveBoat(1000.00,1000.00);
        chile2.MoveBoat(1200.00,1000.00);
        chile3.MoveBoat(1000.00, 3000.00);

        //Chat variables
        chat.setFont(Font.font("Arial",14));

        //Setup Main Boat Position and MarkMovement
        healthBar.setFont(Font.font("Arial",20));
        instructions.setFont(Font.font("Arial",20));
        instructions.setTranslateX(10);
        instructions.setTranslateY(20);
        AnchorPane.setTopAnchor(playerOne, 275.0);
        AnchorPane.setLeftAnchor(playerOne, 375.0);
        markMovement.getPoints().addAll(20.0, 15.0,
                0.0, 10.0,
                0.0, 20.0);
        markMovement.setFill(Color.TRANSPARENT);


        //Setup Background of the game
        Image backgroundImage = new Image(getClass().getResource("/mapaAmericaSur.jpg").toExternalForm());

        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        );
        anchorPaneMain.setBackground(new Background(background));

        // Create the camera
        camera = new PerspectiveCamera(false);
        //camera.setTranslateZ(CAMERA_INITIAL_Z);
        camera.setTranslateZ(-100);
        // Set the field of view
        camera.setFieldOfView(45);

        // Create the scene
        Group root = new Group();
        root.getChildren().addAll(anchorPaneMain);
        scene = new Scene(root, 5000, 5000, true);
        scene.setCamera(camera);
        primaryStage.setScene(scene);
        primaryStage.setWidth(800);
        primaryStage.setHeight(480);
        primaryStage.show();
        playerOne.requestFocus(); // Make the object receive key events
        initialize();
    }
    //Create a mathod that runs 60 times per second giving the speed to the game
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16.66666), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (canMove){
                RotateBoat();
            }
            camera.setTranslateX(playerOne.getTranslateX());
            camera.setTranslateY(playerOne.getTranslateY());
            healthBar.setTranslateX(camera.getTranslateX());
            healthBar.setTranslateY(camera.getTranslateY()+460);
            healthBar.setText(playerOne.GetHealth().toString());
            KeyController();
            if (!chatUp){
                ShootUpdate();
                EnemyAttack(chile1);
                EnemyAttack(chile2);
                EnemyAttack(chile3);
                CheckShoot(chile1);
                CheckShoot(chile2);
                CheckShoot(chile3);
                putInstructions(grau);
                putInstructions(anib);
                putInstructions(artu);
                putInstructions(hila);
            }
        }
    }));

    public void initialize()
    {
        //Initialize the timeline
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setRate(1); //1 is normal speed. 2 is double, etc. -1 is reverse.
        timeline.play();
    }

    private void RotateBoat(){
        //Get the Input from the mouse and determine the angle and the quadrant
        anchorPaneMain.setOnMouseMoved(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me){
                markMovement.setFill(Color.RED);
                mBx = playerOne.getTranslateX()+15+360;
                mBy = playerOne.getTranslateY()+12.5+270;
                xPosM = me.getX()-playerOne.getTranslateX()-400;
                yPosM = me.getY()-playerOne.getTranslateY()-230;
                int r = 75;
                if (xPosM < 0){
                    if (yPosM > 0){
                        //3Quadrant 180-theta
                        theta =  Math.PI-Math.atan(-1*yPosM/xPosM);
                    }else {
                        //2Quadrant 180+theta
                        theta =  Math.PI+Math.atan(yPosM/xPosM);
                    }
                }else {
                    if (yPosM > 0){
                        //4Quadrant 360-theta
                        theta =  Math.PI*2-Math.atan(-1*yPosM/xPosM);

                    }else {
                        //1Quadrant theta
                        theta = Math.atan(yPosM/xPosM);
                    }
                }
                thetaD = Math.toDegrees(theta);
                markMovement.setTranslateX(mBx + r*Math.cos(theta));
                markMovement.setTranslateY(mBy + r*Math.sin(theta));
                markMovement.rotateProperty().set(thetaD);
            }
        });
        //Same method as above but now only when the mouse is dragged
        anchorPaneMain.setOnMouseDragged(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent me) {
                markMovement.setFill(Color.RED);
                xPosM = me.getX()-playerOne.getTranslateX()-400;
                yPosM = me.getY()-playerOne.getTranslateY()-240;
                if (xPosM < 0){
                    if (yPosM > 0){
                        //3Quadrant 180-theta
                        theta =  Math.PI-Math.atan(-1*yPosM/xPosM);
                    }else {
                        //2Quadrant 180+theta
                        theta =  Math.PI+Math.atan(yPosM/xPosM);
                    }
                }else {
                    if (yPosM > 0){
                        //4Quadrant 360-theta
                        theta =  Math.PI*2-Math.atan(-1*yPosM/xPosM);

                    }else {
                        //1Quadrant theta
                        theta = Math.atan(yPosM/xPosM);
                    }
                }
                thetaD = Math.toDegrees(theta);
                markMovement.setTranslateX(mBx + r*Math.cos(theta));
                markMovement.setTranslateY(mBy + r*Math.sin(theta));
                markMovement.rotateProperty().set(thetaD);
            }
        });
        //Make parameters when the mouse is clicked
        anchorPaneMain.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me){
                mouseIsJustClick = true;
            }
        });
        anchorPaneMain.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me){
                mouseIsJustClick = false;
                speed = 0;
            }
        });
        if (mouseIsJustClick){moveBoat();}

    }
    //Move boat using this method
    private void moveBoat(){
        if (oneTime){
            instructions.setText("");
            oneTime = false;
        }
        mBx = playerOne.getTranslateX()+15+360;
        mBy = playerOne.getTranslateY()+12.5+270;
        markMovement.setTranslateX(mBx + r*Math.cos(theta));
        markMovement.setTranslateY(mBy + r*Math.sin(theta));
        markMovement.rotateProperty().set(thetaD);
        if (speed < 5){speed = speed+0.0625;}
        else if(speed>5){speed = 5;}
        double xVelocity = speed * Math.cos(theta);
        double yVelocity = speed * Math.sin(theta);
        thetaE = theta;
        playerOne.setTranslateY(playerOne.getTranslateY()+ yVelocity);
        playerOne.setTranslateX(playerOne.getTranslateX()+ xVelocity);
        playerOne.rotateProperty().set(thetaD+90);
        CheckBoundries();
    }

    //Check the boundries of the map
    private void CheckBoundries(){
        if (playerOne.getTranslateX()<-400){
            playerOne.setTranslateX(playerOne.getTranslateX()+5);
        }else if(playerOne.getTranslateX()>4600){
            playerOne.setTranslateX(playerOne.getTranslateX()-5);
        }
        if (playerOne.getTranslateY()<-300){
            playerOne.setTranslateY(playerOne.getTranslateY()+5);
        }else if(playerOne.getTranslateY()>4500){
            playerOne.setTranslateY(playerOne.getTranslateY()-5);
        }
    }

    //Shoot Methods to create the bullet and setup variables
    private void Shoot(KeyCode keyCode){
            if (keyCode == KeyCode.Z && canShoot){
                bullet = new Circle(5, Color.BLACK);
                bullet.setTranslateX(playerOne.getTranslateX()+390);
                bullet.setTranslateY(playerOne.getTranslateY()+290);
                thetaEstatic = thetaE;
                anchorPaneMain.getChildren().add(bullet);
                canShoot = false;
                LeftShoot = true;
                hitEnemyOneTime = false;
            }else if(keyCode == KeyCode.X && canShoot){
                bullet = new Circle(5, Color.BLACK);
                bullet.setTranslateX(playerOne.getTranslateX()+390);
                bullet.setTranslateY(playerOne.getTranslateY()+290);
                thetaEstatic = thetaE;
                anchorPaneMain.getChildren().add(bullet);
                canShoot = false;
                LeftShoot = false;
                hitEnemyOneTime = false;
            }
    }
    //Make un update method for bullet position and when you can shoot again
    private void ShootUpdate(){
        if (!canShoot){
            if (speedB <= 20 && speedB > 0){speedB = speedB-0.40;}
            else if(speedB <= 0){
                anchorPaneMain.getChildren().remove(bullet);
                speedB = 20;
                canShoot = true;
                ;}
            double yVelocityB;
            double xVelocityB;
            if (LeftShoot){
                xVelocityB = speedB * Math.cos(thetaEstatic-(Math.PI/2));
                yVelocityB = speedB * Math.sin(thetaEstatic-(Math.PI/2));
            }else {
                xVelocityB = speedB * Math.cos(thetaEstatic+(Math.PI/2));
                yVelocityB = speedB * Math.sin(thetaEstatic+(Math.PI/2));
            }
            bullet.setTranslateX(bullet.getTranslateX()+ xVelocityB);
            bullet.setTranslateY(bullet.getTranslateY()+ yVelocityB);
        }
    }
    //Check if the bullet collided with an specific target
    private void CheckShoot(EnemyBoat enemY){
        if (!canShoot){
            if(bullet.getBoundsInParent().intersects(enemY.getBoundsInParent()) && !enemY.isDead() && !hitEnemyOneTime){
                enemY.reduceHealth(25.0);
                anchorPaneMain.getChildren().remove(bullet);
                hitEnemyOneTime = true;
                if (enemY.isDead()){
                    anchorPaneMain.getChildren().remove(enemY);
                }
            }
        }
    }

    //Enemy Attack having a mix of all logic above generating the bullet,
    //updating position, checking colliders, getting player angle
    private void EnemyAttack(EnemyBoat enemyBoat){
        enemyBoat.setMiddlePointBoatX(playerOne.getTranslateX()+385-enemyBoat.getTranslateX());
        enemyBoat.setMiddlePointBoatY(playerOne.getTranslateY()+300-enemyBoat.getTranslateY());
        if (enemyBoat.getMiddlePointBoatX() < 0){
            if (enemyBoat.getMiddlePointBoatY()  > 0){
                //3Quadrant 180-theta
                enemyBoat.setMovementTheta( Math.PI-Math.atan(-1*enemyBoat.getMiddlePointBoatY() /enemyBoat.getMiddlePointBoatX()));
            }else {
                //2Quadrant 180+theta
                enemyBoat.setMovementTheta( Math.PI+Math.atan(enemyBoat.getMiddlePointBoatY() /enemyBoat.getMiddlePointBoatX()));
            }
        }else {
            if (enemyBoat.getMiddlePointBoatY()  > 0){
                //4Quadrant 360-theta
                enemyBoat.setMovementTheta( Math.PI*2-Math.atan(-1*enemyBoat.getMiddlePointBoatY() /enemyBoat.getMiddlePointBoatX()));

            }else {
                //1Quadrant theta
                enemyBoat.setMovementTheta(Math.atan(enemyBoat.getMiddlePointBoatY() /enemyBoat.getMiddlePointBoatX()));
            }
        }
        if (enemyBoat.getMoveArea().getBoundsInParent().intersects(playerOne.getBoundsInParent()) && !enemyBoat.getShootArea().getBoundsInParent().intersects(playerOne.getBoundsInParent())){
            if (enemyBoat.getSpeed() < 3.5){enemyBoat.setSpeed(enemyBoat.getSpeed() +0.0625);}
            else if(enemyBoat.getSpeed() >3.5){enemyBoat.setSpeed(3.5);}
            enemyBoat.setxVelocity(enemyBoat.getSpeed() * Math.cos(enemyBoat.getMovementTheta()));
            enemyBoat.setyVelocity(enemyBoat.getSpeed() * Math.sin(enemyBoat.getMovementTheta()));
            enemyBoat.MoveBoat(enemyBoat.getxVelocity(), enemyBoat.getyVelocity());
            enemyBoat.rotateProperty().set(Math.toDegrees(enemyBoat.getMovementTheta()+90));
        }
        if (enemyBoat.getShootArea().getBoundsInParent().intersects(playerOne.getBoundsInParent()) && enemyBoat.getIsShoot() && !enemyBoat.isDead()){
            enemyBoat.CreateBullet(5, Color.BLACK);
            enemyBoat.TranslateBullet(enemyBoat.getTranslateX()+10,enemyBoat.getTranslateY()+10);
            anchorPaneMain.getChildren().add(enemyBoat.getBullet());
            enemyBoat.setBulletTheta(enemyBoat.getMovementTheta());
            enemyBoat.rotateProperty().set((Math.toDegrees(enemyBoat.getBulletTheta()))+90);
            enemyBoat.setIsShoot(false);
            enemyBoat.setOnHitPlayer(false);
        }
        if (!enemyBoat.getIsShoot()){
            if (enemyBoat.getSpeedBullet() <= 20 && enemyBoat.getSpeedBullet() > 0){enemyBoat.setSpeedBullet(enemyBoat.getSpeedBullet()-0.1);}
            else if(enemyBoat.getSpeedBullet() <= 0){
                anchorPaneMain.getChildren().remove(enemyBoat.getBullet());
                enemyBoat.setSpeedBullet(10);
                enemyBoat.setIsShoot(true);
                }
            enemyBoat.TranslateBullet(enemyBoat.getBullet().getTranslateX()+ (enemyBoat.getSpeedBullet() * Math.cos(enemyBoat.getBulletTheta())), enemyBoat.getBullet().getTranslateY()+ (enemyBoat.getSpeedBullet() * Math.sin(enemyBoat.getBulletTheta())));
            if (enemyBoat.getBullet().getBoundsInParent().intersects(playerOne.getBoundsInParent()) && !enemyBoat.getOnHitPlayer()){
                playerOne.ReduceHealth(25.0);
                enemyBoat.setOnHitPlayer(true);
            }
            if (playerOne.IsDead()){
                canMove = false;
                Text DeathText = new Text("You are dead");
                DeathText.setFont(Font.font(50));
                DeathText.setTranslateX(camera.getTranslateX());
                DeathText.setTranslateY(camera.getTranslateY());
                anchorPaneMain.getChildren().add(DeathText);
            }
        }
    }

    //Checking collision for locations and setting up Chat Mode
    private  void CheckBox(Rectangle box, String message, KeyCode keyCo, boolean chatGPT){
        if (playerOne.getBoundsInParent().intersects(box.getBoundsInParent()) && !chatUp){
            instructions.setTranslateX(playerOne.getTranslateX()+10);
            instructions.setTranslateY(playerOne.getTranslateY()+20);
            instructions.setText("Press \"f\" to interact");
        }else if(chatUp){
            instructions.setText("");
        }
        if(playerOne.getBoundsInParent().intersects(box.getBoundsInParent()) && keyCo == KeyCode.F && !chatUp){
            if (chatGPT){message=chatGPTclient.Request(message);}
            chat.setText(message);
            anchorPaneMain.getChildren().addAll(chatbox,chat);
            chat.setTranslateX(playerOne.getTranslateX()-70);
            chat.setTranslateY(playerOne.getTranslateY()+50);
            chatbox.setTranslateX(chat.getTranslateX()-15);
            chatbox.setTranslateY(chat.getTranslateY()-15);
            chatUp = true;
            canMove = false;
        }else if (keyCo == KeyCode.F && chatUp && playerOne.getBoundsInParent().intersects(box.getBoundsInParent())){
            anchorPaneMain.getChildren().removeAll(chatbox,chat);
            chatUp = false;
            canMove = true;
        }
    }

    private  void Vox(Location loc, KeyCode keyCo){
        if(playerOne.getBoundsInParent().intersects(loc.getBoundsInParent()) && keyCo == KeyCode.F && !chatUp){
            chat.setText("Hi, I am " + loc.getName() + ", what do you want to ask:  Press 1,2 or 3 for questions and Enter when decided");
            anchorPaneMain.getChildren().addAll(chatbox,chat, loc.imageView);
            chat.setTranslateX(playerOne.getTranslateX()-70);
            chat.setTranslateY(playerOne.getTranslateY());
            chatbox.setTranslateX(chat.getTranslateX()-15);
            chatbox.setTranslateY(chat.getTranslateY()-25);
            chatUp = true;
            canMove = false;
        }else if (keyCo == KeyCode.F && chatUp && playerOne.getBoundsInParent().intersects(loc.getBoundsInParent())){
            anchorPaneMain.getChildren().removeAll(chatbox,chat, loc.imageView);
            chatUp = false;
            canMove = true;
        }
        int num = 0;
        if (playerOne.getBoundsInParent().intersects(loc.getBoundsInParent())){
            if (keyCo == KeyCode.DIGIT1){
                chat.setText(splitString(loc.getQuestions(0)));
                num = 0;
            }else if (keyCo == KeyCode.DIGIT2){
                chat.setText(splitString(loc.getQuestions(1)));
                num = 1;
            }
            else if (keyCo == KeyCode.DIGIT3){
                chat.setText(splitString(loc.getQuestions(2)));
                num = 2;
            }
            if (!chat.getText().equals("Hi, I am " + loc.getName() + ", what do you want to ask:  Press 1,2 or 3 for questions and Enter when decided") && keyCo == keyCo.ENTER){
                anchorPaneMain.getChildren().remove(loc.imageView);
                chat.setText(splitString(chatGPTclient.Request("You are " + loc.getName() + "You will answer questions about your character and la guerra del pacifico" + loc.getQuestions(num))));
            }
        }
    }
    private void putInstructions(Location loc){
        if (playerOne.getBoundsInParent().intersects(loc.areaCheck().getBoundsInParent()) && !chatUp){
            if (playerOne.getBoundsInParent().intersects(loc.getBoundsInParent())){
                instructions.setTranslateX(playerOne.getTranslateX()+10);
                instructions.setTranslateY(playerOne.getTranslateY()+20);
                instructions.setText("Press \"f\" to interact");
            }else if(!playerOne.getBoundsInParent().intersects(loc.getBoundsInParent())){
                instructions.setText("");
            }
        }else if (chatUp){
            instructions.setText("");
        }
    }


    private void KeyController(){
        //Method that calls ang gives inputs to all keyboard based methods
        scene.setOnKeyPressed(events -> {
            KeyCode keyCode = events.getCode();
            Shoot(keyCode);
            //if(keyCode == KeyCode.I){}
            Vox(grau, keyCode);
            Vox(anib, keyCode);
            Vox(artu, keyCode);
            Vox(hila, keyCode);
            CheckBox(limaPe, limaMes, keyCode, false);
            CheckBox(santiagoCh, santiagoMes, keyCode, false);
            CheckBox(arica, aricaMes, keyCode, false);
        });
    }

    //Method to separate strings to make it visible on screen
    public String splitString(String string) {
        if (string.length() > 140) {
            if (string.substring(140).length() > 140){
                return string.substring(0, 140) + "\n" + splitString(string.substring(140));
            }else {
                return string.substring(0, 140) + "\n" + string.substring(140);
            }
        } else {
            return string;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}