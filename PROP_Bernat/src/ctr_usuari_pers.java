package user_persistencia;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ctr_usuari_pers {
    URL url = getClass().getResource("usuaris.txt");
    File file = new File(url.getPath());
    public ArrayList<ArrayList<String>> carregar_usuaris() throws FileNotFoundException{
        Scanner sc = new Scanner(file);
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        ArrayList<String> array_aux;
        String aux = " ";
        boolean end = false;
        int i = 0;
        while(sc.hasNext()){
            array_aux =new ArrayList<String>();
            while(!end){
                if((i >= 6) && ((i-6)%3 == 2)){
                    aux = sc.nextLine();
                    aux = aux.trim();
                }
                else aux = sc.next();
                if(aux.equals("end")){
                    end = true;
                }
                else{
                    if(aux.equals("null")) aux = null;
                    array_aux.add(aux);
                }
                ++i;
            }
            result.add(array_aux);
            end = false;
            i = 0;
        }
        sc.close();
        return result;
    }
    public void guardar_usuaris(ArrayList<ArrayList<String>> users){
        BufferedWriter writer = null;
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            writer = new BufferedWriter(fw);
            ArrayList<String> aux;
            String s;
            for(int i = 0;i < users.size(); ++i) {
                aux = users.get(i);
                for (int j = 0; j < aux.size(); ++j) {
                    s = aux.get(j);
                    if(s == null) s = "null";
                    s+=" ";
                    writer.write(s);
                    if(((j - 6)%3) == 2) writer.newLine();
                }
                writer.write("end");
                writer.newLine();
            }
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
