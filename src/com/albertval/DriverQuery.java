package com.albertval;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Albert on 14/04/2016.
 */
public class DriverQuery {
    public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            boolean exit = false;
            while(!exit) {
                System.out.println("Escull cerca:");

                String ln;
                String words[];
                ln = br.readLine();
                words = ln.split(" ");
                String opc = words[0];
                try {
                    switch(opc){
                        case "1":
                            System.out.println("Has seleccionat el 1 :D");
                            break;
                        default:
                            System.out.println("Has seleccionat random num");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
    }
}
