import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static final Scanner myScanner = new Scanner(System.in);
    private static boolean isApplicationRunning = true;
    private static final CryptoApi cryptoApi = new CryptoApi();

    public static void main(String[] args) {
        try {
            while (isApplicationRunning) {
                startMenu();
            }

        } catch (UserRequestsExitException e) {
            System.out.println("Goodbye!");
        }
    }

    public static void startMenu() {
        System.out.println("\n---------------------------------");
        System.out.println("Enter coin price you would like to see.\n");

        for (String coinType : CryptoApi.ALL_COINS_TYPE) {
            System.out.println("\t" + coinType);
        }

        System.out.println("\n(Enter exit to quit)\n");
        System.out.print("> ");

        String input = myScanner.nextLine().toLowerCase(Locale.ROOT);

        if (input.equals("exit")) {
            isApplicationRunning = false;
            throw new UserRequestsExitException();
        }

        if (isCoinTypeValid(input)) {
            String price = cryptoApi.GetCurrentCoinPrice(input);

            System.out.println("The current price of " + input + " is " + price);
        } else {
            System.out.println("That coin type is not supported. Please try again");
            startMenu();
        }
    };

    public static boolean isCoinTypeValid(String input) {
        return CryptoApi.ALL_COINS_TYPE.contains(input);
    };
}