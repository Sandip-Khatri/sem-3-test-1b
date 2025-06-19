package demomavinfx;





import java.time.LocalDate;

public class loginapp extends Application {

    // Track failed attempts and locked status
    private int failedAttempts = 0;
    private final int MAX_ATTEMPTS = 5;
    private boolean isLocked = false;

    @Override
    public void start(Stage primaryStage) {
        // Title
        Label titleLabel = new Label("Login Page");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Input fields
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");

        // Button
        Button loginButton = new Button("Login");

        // Labels for name, student ID, and date
        Label nameLabel = new Label("Name: Sandip Khatri");
        Label studentIdLabel = new Label("Student ID: 123456789");
        Label dateLabel = new Label("Date: " + LocalDate.now());

        // Login logic with account locking
        loginButton.setOnAction(e -> {
            if (isLocked) {
                showAlert(Alert.AlertType.ERROR, "Account Locked", "Sorry, Your Account is Locked!!!");
                return;
            }

            String username = usernameField.getText();
            String password = passwordField.getText();

            if (username.equals("sandip") && password.equals("sandip123")) {
                showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + username + "!");
                failedAttempts = 0;  // reset attempts after success
            } else {
                failedAttempts++;
                if (failedAttempts >= MAX_ATTEMPTS) {
                    isLocked = true;
                    showAlert(Alert.AlertType.ERROR, "Account Locked", "Sorry, Your Account is Locked!!!");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.\nAttempt " + failedAttempts + " of " + MAX_ATTEMPTS);
                }
            }
        });

        // Layout
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(
            titleLabel,
            usernameField,
            passwordField,
            loginButton,
            nameLabel,
            studentIdLabel,
            dateLabel
        );

        // Scene and Stage
        Scene scene = new Scene(vbox, 400, 350);
        primaryStage.setTitle("JavaFX Login Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper method for alerts
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}