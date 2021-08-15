package BinArchive;

import EnterData.EnterData;
import Entity.Client;
import Entity.Machinery;
import Manager.MachineryManager;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Bin {

    private static final String FILE_LOCATION = "maquinaria.txt";


    /*public static void writeMachineryInDisc(){
        try {
            //hacer una copia de seguridad en otra carpeta cada vez que guardo el archivo
            int newMachineNumber = getNumberOfMachines()+1; //numero de maquinas registradas + 1 por la posicion de la nueva
            RandomAccessFile file = new RandomAccessFile(FILE_LOCATION,"rw");

            seekEndOfFile(file);
            boolean saveMoreObjets =true;

            while(saveMoreObjets){
                System.out.println("Maquina numero " + newMachineNumber);
                Machinery machinery = MachineryManager.enterData();
                saveMachineInArchive(machinery,file); //se escribe objeto en archivo
                System.out.println("Quiere guardar mas maquinaria en el archivo? S/N");
                String answer= EnterData.nextLine();
                if(answer.equalsIgnoreCase("N")){
                    saveMoreObjets=false;
                }
                newMachineNumber++;
            }

            //se cierra archivo
            file.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }*/

    public static void addNewMachinery(Machinery machinery){
        try {
            //hacer una copia de seguridad en otra carpeta cada vez que guardo el archivo
            int newMachineNumber = getNumberOfMachines()+1; //numero de maquinas registradas + 1 por la posicion de la nueva

            try {
                File file1 = new File(FILE_LOCATION, "r");

                if (!file1.exists()) {
                    file1.createNewFile();
                }
            }catch(Exception e){

            }
            RandomAccessFile file = new RandomAccessFile(FILE_LOCATION,"rw");


            seekEndOfFile(file);

            saveMachineInArchive(machinery,file); //se escribe objeto en archivo

            //se cierra archivo
            file.close();
        } catch (IOException ex) {

        }
    }

    private static void seekEndOfFile(RandomAccessFile file) throws IOException{
        long fileLenght = file.length();
        file.seek(fileLenght);
    }

    private static void saveMachineInArchive(Machinery machinery, RandomAccessFile file){
        Gson gson=new Gson();
        try {
            String line = gson.toJson(machinery);
            file.writeBytes(line + "\n");
        }catch (IOException e){
            System.out.println("Exception: " + e);
        }catch (Exception e){
            System.out.println("Exception: " + e);
        }

    }

    public static int getNumberOfMachines(){
        int counter=0;
        try {
            RandomAccessFile file = new RandomAccessFile(FILE_LOCATION, "r");
            String line = file.readLine();
            while(line!=null) {
                counter++;
                line = file.readLine();
            }

            file.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return counter;
    }


    public static List<Machinery> readObjetsAndAddToList(){
        List<Machinery> listOfMachinery = new ArrayList<>();
        try{
            RandomAccessFile file = new RandomAccessFile(FILE_LOCATION,"r");
            file.seek(0);

            String line = file.readLine();
            while(line!=null) {
                listOfMachinery.add(getMachinery(line));
                line = file.readLine();
            }
            file.close();
        }catch(IOException e){
            System.out.println(e);
        }catch(Exception e){
            System.out.println(e);
        }
        return listOfMachinery;
    }

    private static Machinery getMachinery(String line){
        Gson gson=new Gson();
        return gson.fromJson(line,Machinery.class);
    }

    public static void overwriteArchive(List<Machinery> list){
        Gson gson=new Gson();
        try{
            eraseArchive();
            RandomAccessFile file = new RandomAccessFile(FILE_LOCATION,"rw");
            file.seek(0);
            String line;
            for(int i=0;i<list.size();i++){
                line = gson.toJson(list.get(i));
                file.writeBytes(line + "\n");
            }
        file.close();
        }catch(IOException e){
            System.out.println(e);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    private static void eraseArchive(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_LOCATION));
            bw.write("");
            bw.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
