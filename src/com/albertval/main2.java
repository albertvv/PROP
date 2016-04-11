public class main {
    public static void main(String [ ] args){
        //usuari_estandard user = new usuari_estandard("Mustafar", "Moha");
        //boolean b = user.definir_relacio("coautoria", "APA", "Es una prova");
        //user.guardar_usuari();
        usuari_privilegiat up = new usuari_privilegiat("Manolo", "Huevo");
        cjtUsuarios.cjtUsuaris.afegir_usuari("Manolo", up);
        up = (usuari_privilegiat)cjtUsuarios.cjtUsuaris.consultar_usuari("Manolo");
        up.crear_usuari("Mustafar", "Salam");
        up.donar_privilegis("Mustafar");
        usuari_privilegiat up2 = (usuari_privilegiat)cjtUsuarios.cjtUsuaris.consultar_usuari("Mustafar");
        up2.borrar_usuari("Manolo");
        System.out.println(cjtUsuarios.cjtUsuaris.consultar_usuari("Manolo")==null);
        //usuari_estandard user = (usuari_estandard)cjtUsuarios.cjtUsuaris.consultar_usuari("Mustafar");
        //b = up.modificar_usuari("Mustafar","Moha", "Salam", "Mustaf", "masculi", null);
        //user.borrar_usuari();
        //user =  (usuari_estandard)cjtUsuarios.cjtUsuaris.consultar_usuari("Mustafar");
        //System.out.println(user == null);
        //System.out.println(user.getUsername()+user.getNombre()+user.getPassword()+user.getSexo());
        //System.out.println(cjtUsuarios.cjtUsuaris.borrar_usuari("Mustafar"));
        //System.out.println(cjtUsuarios.cjtUsuaris.borrar_usuari("Mustafar"));
        //user =  (usuari_estandard)cjtUsuarios.cjtUsuaris.consultar_usuari("Mustafar");
        //System.out.println(user==null);
        //System.out.println(user.getUsername());
        //TipoRelacion relacio = user.consultar_relacio("coautoria");
        //System.out.println(b+relacio.getNombre()+relacio.getPath()+relacio.getDescripcio());
        //user.modificar_relacio("coautoria","coproduccio","APCPA",null);
        //relacio = user.consultar_relacio("coproduccio");
        //b = user.definir_relacio("coautoria", "APCPA", "Es una altre prova");
        //relacio = user.consultar_relacio("coautoria");
        //System.out.println(user.esborrar_relacio("coautoria"));
        //System.out.println(user.esborrar_relacio("coautoria"));
        //relacio = user.consultar_relacio("coautoria");
        //System.out.println(relacio==null);
        //System.out.println(b+relacio.getNombre()+relacio.getPath()+relacio.getDescripcio());
    }
}
