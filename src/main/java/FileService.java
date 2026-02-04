import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileService {
    public static Book parseBook(String line){
        String[] parts = line.split(";");
        return new Book(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], Integer.parseInt(parts[4]));
    }

    public static Client parseClient(String line){
        String[] parts = line.split(";");
        return new Client(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
    }

    public static Loan parseLoan(String line){
        String[] parts = line.split(";");
        return new Loan(parts[0], parts[1], Integer.parseInt(parts[2]));
    }

    public static void loadBooks(Path libraryFile, List<Book> books){
        try (BufferedReader br = Files.newBufferedReader(libraryFile, Charset.defaultCharset())){
            String line;
            while ((line = br.readLine()) != null){
                books.add(parseBook(line));
            }
        } catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }

    public static void loadClients(Path clientsFile, List<Client> clients){
        try (BufferedReader br = Files.newBufferedReader(clientsFile, Charset.defaultCharset())){
            String line;
            while ((line = br.readLine()) != null){
                clients.add(parseClient(line));
            }
        } catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }

    public static void loadLoans(Path loansFile, List<Loan> loans){
        try (BufferedReader br = Files.newBufferedReader(loansFile, Charset.defaultCharset())){
            String line;
            while ((line = br.readLine()) != null){
                loans.add(parseLoan(line));
            }
        } catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }
}
