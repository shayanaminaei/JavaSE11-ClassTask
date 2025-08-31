package mftplus.model.repository;

import mftplus.model.tools.ConnectionProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseManager {
    public static void createDatabase() throws FileNotFoundException, SQLException {

        Scanner scanner = new Scanner(new File("src/main/resources/database_creator.sql"));

        String sqlCommand = "";
        while (scanner.hasNextLine()) {
                sqlCommand += scanner.nextLine();
            }
        String[] sqlCommands = sqlCommand.split(";");

        for (String command : sqlCommands) {
            System.out.println(command);
            try {
                ConnectionProvider.getProvider().getOracleConnection().prepareStatement(command).execute();
                System.out.println("Created");
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }

            System.out.println("-------------------------------------------------------");
        }
    }
}
