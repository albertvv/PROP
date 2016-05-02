package prop.alvarHernandez;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class ControladorGrafoPersist {

    private String paper;
    private String author;
    private String conf;
    private String term;
    private String paper_label;
    private String author_label;
    private String conf_label;
    private String paper_author;
    private String paper_conf;
    private String paper_term;

    public ControladorGrafoPersist() {
        //Direccions dels fitxers continguts a DBLP
        this.paper = "C:\\Users\\Àlvar\\IdeaProjects\\LLegir\\src\\prop\\alvarHernandez\\DBLP_four_area\\paper.txt";
        this.author = "C:\\Users\\Àlvar\\IdeaProjects\\LLegir\\src\\prop\\alvarHernandez\\DBLP_four_area\\author.txt";
        this.conf = "C:\\Users\\Àlvar\\IdeaProjects\\LLegir\\src\\prop\\alvarHernandez\\DBLP_four_area\\conf.txt";
        this.term = "C:\\Users\\Àlvar\\IdeaProjects\\LLegir\\src\\prop\\alvarHernandez\\DBLP_four_area\\term.txt";
        this.paper_label = "C:\\Users\\Àlvar\\IdeaProjects\\LLegir\\src\\prop\\alvarHernandez\\DBLP_four_area\\paper_label.txt";
        this.author_label = "C:\\Users\\Àlvar\\IdeaProjects\\LLegir\\src\\prop\\alvarHernandez\\DBLP_four_area\\author_label.txt";
        this.conf_label = "C:\\Users\\Àlvar\\IdeaProjects\\LLegir\\src\\prop\\alvarHernandez\\DBLP_four_area\\conf_label.txt";
        this.paper_author = "C:\\Users\\Àlvar\\IdeaProjects\\LLegir\\src\\prop\\alvarHernandez\\DBLP_four_area\\paper_author.txt";
        this.paper_conf = "C:\\Users\\Àlvar\\IdeaProjects\\LLegir\\src\\prop\\alvarHernandez\\DBLP_four_area\\paper_conf.txt";
        this.paper_term = "C:\\Users\\Àlvar\\IdeaProjects\\LLegir\\src\\prop\\alvarHernandez\\DBLP_four_area\\paper_term.txt";

    }

    private String getEtiqueta(Integer i) {
        switch (i) {
            case 0:
                return "Database";
            case 1:
                return "Data Mining";
            case 2:
                return "AI";
            case 3:
                return  "Information Retrieval";
            default:
                return "Invalid Label";
        }
    }
    private Integer getNumEtiqueta(String etiq) {
        switch (etiq) {
            case "Database":
                return 0;
            case "Data Mining":
                return 1;
            case "AI":
                return 2;
            case "Information Retrieval":
                return 3;
            default:
                return -1;
        }
    }
    private void cargarEtiquetas(String archivo, HashMap<Integer, String> labels) throws IOException {
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            int i = 0;
            String id = new String();
            Character lab;
            while(Character.isDigit(cadena.charAt(i))) {
                id += cadena.charAt(i);
                ++i;
            }
            while(!Character.isDigit(cadena.charAt(i))) {
                ++i;
            }
            lab = cadena.charAt(i);
            String label = new String();
            label += lab;
            labels.put(Integer.parseInt(id), getEtiqueta(Integer.parseInt(label)));
        }
        b.close();
    }

    public void saveEtiquetas(String archivo, Vector<Vector<String>> M) throws IOException {
        File fichero = new File(archivo);
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(fichero));
        for(int i = 0; i < M.size(); ++i) {
            if(M.get(i).size() == 3) {
                int n = getNumEtiqueta(M.get(i).get(2));
                bw.write(M.get(i).get(0) + "    " + Integer.toString(n) + "    " + M.get(i).get(1));
            }
        }
        bw.close();
    }

    public Vector<Vector<String>> getPapers() throws IOException {
        Vector<Vector<String>> mP = new Vector<Vector<String>>();
        HashMap<Integer, String> labels = new HashMap<Integer, String>();
        cargarEtiquetas(paper_label, labels);
        String cadena;
        FileReader f = new FileReader(paper);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            int i = 0;
            String id = new String();
            String nom = new String();
            while(Character.isDigit(cadena.charAt(i))) {
                id += cadena.charAt(i);
                ++i;
            }
            while(!Character.isAlphabetic((int)cadena.charAt(i)) && !Character.isDigit(cadena.charAt(i))) {
                ++i;
            }
            while(i < cadena.length()) {
                nom += cadena.charAt(i);
                ++i;
            }
            Vector<String> p = new Vector<String>();
            p.add(id);
            p.add(nom);
            if(labels.get(Integer.parseInt(id))!= null) p.add(labels.get(Integer.parseInt(id)));
            mP.add(p);
        }
        b.close();
        return mP;
    }
    public Vector<Vector<String>> getAutores() throws IOException {
        Vector<Vector<String>> mA = new Vector<Vector<String>>();
        HashMap<Integer, String> labels = new HashMap<Integer, String>();
        cargarEtiquetas(author_label, labels);
        String cadena;
        FileReader f = new FileReader(author);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            int i = 0;
            String id = new String();
            String nom = new String();
            while(Character.isDigit(cadena.charAt(i))) {
                id += cadena.charAt(i);
                ++i;
            }
            while(!Character.isAlphabetic((int)cadena.charAt(i)) && !Character.isDigit(cadena.charAt(i))) {
                ++i;
            }
            while(i < cadena.length()) {
                nom += cadena.charAt(i);
                ++i;
            }
            Vector<String> a = new Vector<String>();
            a.add(id);
            a.add(nom);
            if(labels.get(Integer.parseInt(id))!= null) a.add(labels.get(Integer.parseInt(id)));
            mA.add(a);
        }
        b.close();
        return mA;
    }
    public Vector<Vector<String>> getConferencias() throws IOException {
        Vector<Vector<String>> mC = new Vector<Vector<String>>();
        HashMap<Integer, String> labels = new HashMap<Integer, String>();
        cargarEtiquetas(conf_label, labels);
        String cadena;
        FileReader f = new FileReader(conf);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            int i = 0;
            String id = new String();
            String nom = new String();
            while(Character.isDigit(cadena.charAt(i))) {
                id += cadena.charAt(i);
                ++i;
            }
            while(!Character.isAlphabetic((int)cadena.charAt(i)) && !Character.isDigit(cadena.charAt(i))) {
                ++i;
            }
            while(i < cadena.length()) {
                nom += cadena.charAt(i);
                ++i;
            }
            Vector<String> c = new Vector<String>();
            c.add(id);
            c.add(nom);
            if(labels.get(Integer.parseInt(id))!= null) c.add(labels.get(Integer.parseInt(id)));
            mC.add(c);
        }
        b.close();
        return mC;
    }
    public Vector<Vector<String>> getTerminos() throws IOException {
        Vector<Vector<String>> mT = new Vector<Vector<String>>();
        String cadena;
        FileReader f = new FileReader(term);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            int i = 0;
            String id = new String();
            String nom = new String();
            while(Character.isDigit(cadena.charAt(i))) {
                id += cadena.charAt(i);
                ++i;
            }
            while(!Character.isAlphabetic((int)cadena.charAt(i)) && !Character.isDigit(cadena.charAt(i))) {
                ++i;
            }
            while(i < cadena.length()) {
                nom += cadena.charAt(i);
                ++i;
            }
            Vector<String> t = new Vector<String>();
            t.add(id);
            t.add(nom);
            mT.add(t);
        }
        b.close();
        return mT;
    }
    public Vector<Vector<String>> getPA() throws IOException {
        Vector<Vector<String>> PA = new Vector<Vector<String>>();
        String cadena;
        FileReader f = new FileReader(paper_author);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            int i = 0;
            String Pid = new String();
            String Aid = new String();
            while(Character.isDigit(cadena.charAt(i))) {
                Pid += cadena.charAt(i);
                ++i;
            }
            while(!Character.isDigit(cadena.charAt(i))) {
                ++i;
            }
            while(i < cadena.length() && Character.isDigit(cadena.charAt(i))) {
                Aid += cadena.charAt(i);
                ++i;
            }
            Vector<String> pa = new Vector<String>();
            pa.add(Pid);
            pa.add(Aid);
            PA.add(pa);
        }
        b.close();
        return PA;
    }
    public Vector<Vector<String>> getPC() throws IOException {
        Vector<Vector<String>> PC = new Vector<Vector<String>>();
        String cadena;
        FileReader f = new FileReader(paper_conf);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            int i = 0;
            String Pid = new String();
            String Cid = new String();
            while(Character.isDigit(cadena.charAt(i))) {
                Pid += cadena.charAt(i);
                ++i;
            }
            while(!Character.isDigit(cadena.charAt(i))) {
                ++i;
            }
            while(i < cadena.length() && Character.isDigit(cadena.charAt(i))) {
                Cid += cadena.charAt(i);
                ++i;
            }
            Vector<String> pc = new Vector<String>();
            pc.add(Pid);
            pc.add(Cid);
            PC.add(pc);
        }
        b.close();
        return PC;
    }
    public Vector<Vector<String>> getPT() throws IOException {
        Vector<Vector<String>> PT = new Vector<Vector<String>>();
        String cadena;
        FileReader f = new FileReader(paper_term);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            int i = 0;
            String Pid = new String();
            String Tid = new String();
            while(Character.isDigit(cadena.charAt(i))) {
                Pid += cadena.charAt(i);
                ++i;
            }
            while(!Character.isDigit(cadena.charAt(i))) {
                ++i;
            }
            while(i < cadena.length() && Character.isDigit(cadena.charAt(i))) {
                Tid += cadena.charAt(i);
                ++i;
            }
            Vector<String> pt = new Vector<String>();
            pt.add(Pid);
            pt.add(Tid);
            PT.add(pt);
        }
        b.close();
        return PT;
    }
    public boolean savePapers(Vector<Vector<String>> Papers) throws IOException {
        if (Papers.size() == 0) return false;
        else {
            saveEtiquetas(paper_label, Papers);
            File fichero = new File(paper);
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(fichero));
            for(int i = 0; i < Papers.size(); ++i) {
                bw.write(Papers.get(i).get(0) + "    " + Papers.get(i).get(1));
            }
            bw.close();
            return true;
        }
    }
    public boolean saveAutores(Vector<Vector<String>> Autores) throws IOException {
        if (Autores.size() == 0) return false;
        else {
            saveEtiquetas(author_label, Autores);
            File fichero = new File(author);
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(fichero));
            for(int i = 0; i < Autores.size(); ++i) {
                bw.write(Autores.get(i).get(0) + "    " + Autores.get(i).get(1));
            }
            bw.close();
            return true;
        }
    }
    public boolean saveConferencias(Vector<Vector<String>> Conferencias) throws IOException {
        if (Conferencias.size() == 0) return false;
        else {
            saveEtiquetas(conf_label, Conferencias);
            File fichero = new File(conf);
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(fichero));
            for(int i = 0; i < Conferencias.size(); ++i) {
                bw.write(Conferencias.get(i).get(0) + "    " + Conferencias.get(i).get(1));
            }
            bw.close();
            return true;
        }
    }
    public boolean saveTerminos(Vector<Vector<String>> Terminos) throws IOException {
        if (Terminos.size() == 0) return false;
        else {
            File fichero = new File(term);
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(fichero));
            for(int i = 0; i < Terminos.size(); ++i) {
                bw.write(Terminos.get(i).get(0) + "    " + Terminos.get(i).get(1));
            }
            bw.close();
            return true;
        }
    }
    public boolean savePA(Vector<Vector<String>> PA) throws IOException {
        if (PA.size() == 0) return false;
        else {
            File fichero = new File(paper_author);
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(fichero));
            for(int i = 0; i < PA.size(); ++i) {
                bw.write(PA.get(i).get(0) + "    " + PA.get(i).get(1));
            }
            bw.close();
            return true;
        }
    }
    public boolean savePC(Vector<Vector<String>> PC) throws IOException {
        if (PC.size() == 0) return false;
        else {
            File fichero = new File(paper_conf);
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(fichero));
            for(int i = 0; i < PC.size(); ++i) {
                bw.write(PC.get(i).get(0) + "    " + PC.get(i).get(1));
            }
            bw.close();
            return true;
        }
    }
    public boolean savePT(Vector<Vector<String>> PT) throws IOException {
        if (PT.size() == 0) return false;
        else {
            File fichero = new File(paper_term);
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(fichero));
            for(int i = 0; i < PT.size(); ++i) {
                bw.write(PT.get(i).get(0) + "    " + PT.get(i).get(1));
            }
            bw.close();
            return true;
        }
    }
}
