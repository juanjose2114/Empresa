public class Empleado {

        private String nombre;
        private String cedula;
        private String cargo;
        private int edad;
        public double salario;

    public Empleado(String nombre, String cedula, String cargo, int edad) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.cargo = cargo;
        this.edad = edad;
        this.salario = Empresa.CalcularSalario(cargo, edad);

//        System.out.println(toString());
    }
        public String getNombre() {
        return nombre;
    }
        public void setNombre(String nombre) {
        this.nombre = nombre;
    }
        public String getCedula() {
        return cedula;
    }
        public void setCedula(String cedula) {
        this.cedula = cedula;
    }
        public String getCargo() {
        return cargo;
    }
        public void setCargo(String cargo) {
        this.cargo = cargo;
        this.salario = Empresa.CalcularSalario(cargo, edad);
    }
        public int getEdad() {
        return edad;
    }
        public void setEdad(int edad) {
        this.edad = edad;
        this.salario = Empresa.CalcularSalario(cargo, edad);
    }
        public double getSalario() {
        return salario;
    }
        public void setSalario(String cargo, int edad) {
        this.salario = Empresa.CalcularSalario(cargo, edad);

    }

        public String toString() {
        return "nombre: " + nombre + "\n" + "cedula: " + cedula + "\n" + "cargo: " + cargo + "\n" + "anos: " + edad + "\n" +"salario: " + salario + ".";
    }

}
