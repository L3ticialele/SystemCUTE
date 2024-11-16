import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryPathExample {
    public static void main(String[] args) {
        // Obtendo o caminho absoluto do diretório atual
        Path currentDir = Paths.get(System.getProperty("user.dir"));

        // Concatenando o diretório desejado ao diretório atual
        String subdirectory = "meu diretorio";
        Path directoryPath = currentDir.resolve(subdirectory);

        // Exibindo o caminho resultante
        System.out.println("Caminho completo do diretório: " + directoryPath.toString());
    }
}
