package mainclasses.io;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


public class Error {

    private BufferedWriter buffered;
    private final String path; // Ruta del archivo

    public Error(String path, boolean append) throws IOException {
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

    // Captura un error y lo escribe dentro del archivo correspondiente
    public void capturarError(Error myError, String alerta) {
        try {
            myError.addLine(alerta, true); // TRUE para que no sobreescriba

        } catch (IOException e) {
            InputOutput.printAlert("Error: Problema en la operación de escritura del archivo");
        }

    }

}
