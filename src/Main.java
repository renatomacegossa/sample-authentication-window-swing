import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Inicio");


        AuthenticationWindow authenticationWindow = new AuthenticationWindow();

        authenticationWindow.show();

        System.out.println(String.join(", ", authenticationWindow.data()));

        authenticationWindow.show();

        System.out.println(String.join(", ", authenticationWindow.data()));

        System.out.println("Fim");
    }
}