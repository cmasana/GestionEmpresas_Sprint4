package auxiliar;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Permite crear un archivo Log con todos los registros de creación, modificación y eliminación de empleados
 * y propuestas
 */
public class Log {

    private BufferedWriter buffered;
    private final String path; // Ruta del archivo

    public Log(String path, boolean append) throws IOException {
        this.path = path;
        this.open(append);
    }

    // Abrimos buffer
    private void open(boolean append) throws IOException {
        // Con TRUE no se sobreescribe el archivo, con FALSE si
        this.buffered = new BufferedWriter(new FileWriter(this.path, append));
    }

    // Añadimos línea al buffer
    public void addLine(String line, boolean append) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formatoFecha = sdf.format(new Date());

        this.open(append);
        this.buffered.write("["+ formatoFecha +"] " + line + "\n");

        this.close();
    }

    // Obtenemos líneas del log
    public void getLines() throws IOException {
        ArrayList<String> linesFile = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(this.path));

        String line;

        while ((line = br.readLine()) != null) {
            linesFile.add(line);
        }

        br.close();

        Iterator<String> it = linesFile.iterator();

        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }

    // Cerramos buffer
    private void close() throws IOException {
        this.buffered.close();
    }

}
