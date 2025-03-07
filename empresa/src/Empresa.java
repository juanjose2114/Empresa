import javax.swing.*;
import java.util.Objects;

public class Empresa {

    public String nombre;
    public String nit;
    private static Empleado[] empleados;
    private static Empleado[] empleadosMyMn;
    private static Empleado[] empleadosMnMy;
    private Empleado[] empleadosAux;
    private Empleado[] empleadosAux2;
    private Empleado[] empleadosPorCargo;

    public static void main(String[] args) {

        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre de la Empresa");
        String NIT = JOptionPane.showInputDialog(null, "Ingrese el NIT de la Empresa");
        Empresa empresa = new Empresa(nombre, NIT);
        String cedula;
        boolean retorno = false;
        int eleccion2;

        int caso = 0;
        while ( caso != 6 ){
            int entrada = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Menú:\n" +
                            "1. Agregar trabajador\n" +
                            "2. Eliminar Empleado\n" +
                            "3. Modificar Empleado\n" +
                            "4. revizarEmpleados\n" +
                            "5. Probar Programa\n" +
                            "6. Salir\n" +
                            "Ingrese una opción:"
            ));
            switch (entrada){
                case 1:
                    empresa.agregarEmpleado();
                    break;
                case 2:
                    cedula = JOptionPane.showInputDialog(null, "ingrese la cedula del empleado a eliminar");
                    if (empresa.eliminarEmpleado(cedula)){
                        System.out.print("empleado " + cedula + " eliminado");
                    }else{
                        System.out.print("empleado " + cedula + " no existe");
                    }
                    break;
                case 3:
                    cedula = JOptionPane.showInputDialog(null,"Ingrese la cedula del empleado a modificar");
                    retorno = empresa.modificarEmpleado(cedula);
                    if (retorno) {
                        System.out.print("Empleado modificado correctamente");
                    }else{
                        System.out.print("Error al modificar el empleado");
                    }
                    break;
                case 4://revizarEmpleados
                    eleccion2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Que lista desea consultar\n1. buscar un Empleado por cedula\n2.Lista Menor a Mayor\n3.Lista de Mayor a Menor\n4.Buscar por Cargo \n5.Lista default"));
                    switch (eleccion2){
                        case 1://por cedula
                            cedula = JOptionPane.showInputDialog(null,"Ingrese la cedula del empleado a consultar:");
                            empresa.revizarEmpleados(null, cedula);
                            break;
                        case 2://De Menor a Mayor
                            empleadosMnMy = empresa.organizarEmpleadosMnMy();
                            empresa.revizarEmpleados(empleadosMnMy, null);
                            break;
                        case 3://De Mayor a Menor
                            empleadosMyMn = empresa.organizarEmpleadosMyMn();
                            empresa.revizarEmpleados(empleadosMyMn, null);
                            break;
                        case 5://List default
                            empresa.revizarEmpleados(empleados, null);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,"Obción no valida");
                            break;
                    }
                    break;
                case 5:
                    empresa.probarPrograma();
                    break;
                case 6:
                    caso = 6;
                    JOptionPane.showMessageDialog(null, "Saliento del programa");
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Obción no valida");
                    break;
            }
        }
    }

    public Empresa(String nombre, String nit)  {
        this.nombre = nombre;
        this.nit = nit;
        System.out.print(toString());
        empleados = new Empleado[49];
        System.out.println("la empresa solo puede contener "+ empleados.length + " empleados");
        empleadosAux = new Empleado[empleados.length];
        empleadosAux2 = new Empleado[empleados.length];
        empleadosMyMn = new Empleado[empleados.length];
        empleadosMnMy = new Empleado[empleados.length];
        empleadosPorCargo = new Empleado[empleados.length];
    }

    public void agregarEmpleado(){
        String nombre = JOptionPane.showInputDialog(null,"Ingrese el nombre del empleado");
        String cedula = JOptionPane.showInputDialog(null,"Ingrese la cedula del empleado");
        String cargo = JOptionPane.showInputDialog(null,"Ingrese el cargo del empleado");
        int edad = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese los años en la empresa"));

        if (verificarCedula(cedula)) {
            System.out.print("ya hay un empleado de cedula: " + cedula);
        }else{
            int posicion = posicionLibre();
            if(posicion !=-1 ) {
                empleados[posicion]=new Empleado(cedula,nombre,cargo,edad);
            }else{
                System.out.print("no hay mas espacio en la empresa");
            }
        }    }

    public boolean eliminarEmpleado(String cedula){
        int i = posicionEmpleado(cedula);
        if (i != -1) {
            empleados[i]=null;
            return true;
        } else {
            return false;
        }
    }

    public boolean modificarEmpleado(String cedula){
        String nombre = JOptionPane.showInputDialog(null,"Ingrese el nombre del empleado");
        String cargo = JOptionPane.showInputDialog(null,"Ingrese el cargo del empleado");
        int anos = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el nombre del empleado"));
        int modificado = posicionEmpleado(cedula);
        if(modificado != -1){
            empleados[modificado].setNombre(nombre);
            empleados[modificado].setCargo(cargo);
            empleados[modificado].setEdad(anos);
            empleados[modificado].salario = CalcularSalario(cargo, anos);
            return true;
        }else{
            return false;
        }
    }

    public void revizarEmpleados(Empleado[] empleadosAux2, String cedula){
        if(Objects.equals(cedula, "") || cedula == null) {
            for (int i = 0; i < empleadosAux2.length; i++) {
                if (empleadosAux2[i] != null) {
                    System.out.println(i);
                    System.out.print("\n" + empleadosAux2[i].toString() + "\n");
                }
            }
            JOptionPane.showMessageDialog(null, "Imprimiendo empleados en la terminal");
        }else{
            int i = posicionEmpleado(cedula);
            if (i != -1) {
                System.out.println("\n" + empleados[i].toString() + "\n");

            }else{
                System.out.println("el empleado de cedula " + cedula + " no existe");
            }
            JOptionPane.showMessageDialog(null, "Imprimiendo empleado " + cedula + " en la terminal");
        }
    }

    public void probarPrograma() {
        System.out.println("agregamos 4 empleados");
        String c;
        for (int i = 0; i < 4; i++) {
            c = i + "";
            Empleado empleado = new Empleado("empleado1", c, "empleado", i);
            empleados[i] = empleado;
            System.out.println("\n" + empleado.toString() + "\n");

        }

        System.out.println("organizamos la lista de empleados de Mayor a Menor\n");
        empleadosMyMn = organizarEmpleadosMyMn();
        System.out.println("Imprimimos la lista de empleados de Mayor a Menor\n");
        revizarEmpleados(empleadosMyMn, null);
        System.out.println("organizamos la lista de empleados de Menor a Mayor\n");
        empleadosMnMy = organizarEmpleadosMnMy();
        System.out.println("Imprimimos la lista de empleados de Menor a Mayor\n");
        revizarEmpleados(empleadosMnMy, null);

        System.out.println("eliminamos 3 empleados\n");
        for (int i = 0; i < 4; i++) {
            c = i + "";
            eliminarEmpleado(c);
        }
        System.out.println("Mostramos la lista de empleados\n");
        revizarEmpleados(empleados, null);
    }

    public static double CalcularSalario(String cargo, int edad){
        double salario;

        switch(cargo.toLowerCase()) {
            case "jefe":
                salario = 18000.00;
                break;
            case "senior":
                salario = 2500.00;
                break;
            case "basico":
                salario = 850.00;
                break;
            case "des":
                salario = 0.00;
                break;
            default:
                salario = 3000.00;
                break;
        }

        if (edad > 10) {
            salario *= 1.15;
        }else if(edad > 5){
            salario *= 1.1;
        }else if(edad > 3) {
            salario *= 1.05;
        }

        return salario;
    }


    //verifica la existencia de una cedula en la lista empleados
    public boolean verificarCedula(String cedula){
        for (int i = 0; i < empleados.length; i++) {
            if (empleados[i]!=null) {
                if (cedula.equals(empleados[i].getCedula())) {
                    return true;
                }
            }
        }
        return false;
    }

    //busca una posicion vacia en empleados
    public int posicionLibre(){
        for (int i = 0; i < 49; i++) {
            if (empleados[i]==null){
                return i;
            }
        }
        return -1;
    }

    //busca la posicion de un empleado
    public int posicionEmpleado(String cedula){
        for (int i = 0; i < empleados.length; i++) {
            if (empleados[i]!=null) {
                if (cedula.equals(empleados[i].getCedula())) {
                    return i;
                }
            }
        }
        return -1;
    }



    //devueve una lista de empleados organizados de mayor a menor
    public Empleado[] organizarEmpleadosMyMn(){

        empleadosAux = new Empleado[empleados.length];
        Empleado aux;

        //se le da un valor a los espacios vacios para operarlos facilmente
        for(int i = 0; i < empleadosAux.length; i++){
            if(empleadosAux[i]==null){
                empleadosAux[i] = new Empleado(null, null, "des", -1);
            }
        }

        //ciclo que organiza la lista Aux
        for(int i = 0; i < empleadosAux.length-1; i++){
            for(int j = 0; j < empleadosAux.length-1-i; j++){
                if(empleadosAux[j].getSalario()<empleadosAux[j+1].getSalario()){
                    aux = empleadosAux[j];
                    empleadosAux[j] = empleadosAux[j+1];
                    empleadosAux[j+1] = aux;
                }
            }
        }

        //los empleados creados en este metodo vuelven a ser nulos
        for(int i = 0; i < empleadosAux.length; i++){
            if(empleadosAux[i].getEdad()==-1){
                empleadosAux[i] = null;
            }
        }
        //System.arraycopy(empleadosAux, 0, empleadosMyMn, 0, empleadosAux.length);
        return empleadosAux;
    }

    //devueve una lista de empleados organizados de mayor a menor
    public Empleado[] organizarEmpleadosMnMy(){
        empleadosAux = organizarEmpleadosMyMn();
        empleadosMyMn = new Empleado[empleadosAux.length];
        int j = empleadosAux.length-1;
        for(int i = 0; i < empleadosAux.length; i++){
            empleadosMnMy[j] = empleadosAux[i];
            j -= 1;
        }
        return empleadosMnMy;
    }

    //toString de empresa
    public String toString() {
        return nombre + ", " + nit + ".";
    }



}
