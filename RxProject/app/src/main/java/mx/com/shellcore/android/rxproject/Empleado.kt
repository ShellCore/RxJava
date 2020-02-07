package mx.com.shellcore.android.rxproject

data class Empleado(
    var id: Int = 0,
    var nombre: String = "",
    var puesto: String = "",
    var antiguedad: String = "",
    var salario: Double = 0.0,
    var plusSalario: Double = 0.0
) {

    companion object {
        fun getListaEmpleados() : List<Empleado> {
            val empleados = ArrayList<Empleado>()
            empleados.add(Empleado(1, "Carl Walter", "Mi Corporation", "25/04/2014", 2.66, 2.32))
            empleados.add(Empleado(2, "Stephanie Miranda", "Dictum Corp.", "01/03/2016", 1.74, 7.41))
            empleados.add(Empleado(3, "Kevyn R. Durham", "Lacinia Mattis Consulting", "18/03/2017", 1.77, 8.63))
            empleados.add(Empleado(4, "Ciara D. Wallace", "Tincidunt Orci Corporation", "10/10/2016", 8.68, 1.88))
            empleados.add(Empleado(5, "Brent Kelly", "Quisque Incorporated", "09/03/2007", 4.88, 3.40))
            empleados.add(Empleado(6, "Adena Spencer", "Ultricies LLC", "18/01/2017", 8.70, 6.15))
            empleados.add(Empleado(7, "Shelley F. Holcomb", "Ac Urna Incorporated", "29/01/2007", 3.10, 7.20))
            empleados.add(Empleado(8, "Vernon Walker", "Nec Diam Duis Consulting", "20/11/2003", 8.56, 1.15))
            empleados.add(Empleado(9, "Todd Vincent", "Donec Nibh Quisque Institute", "15/08/2002", 6.63, 3.47))
            empleados.add(Empleado(10, "Cameron Hubbard", "Non Quam Pellentesque Foundation", "06/05/2002", 9.82, 0.59))
            empleados.add(Empleado(11, "Howard V. Wagner", "Egestas Nunc Sed LLC", "24/11/2018", 7.37, 0.88))
            empleados.add(Empleado(12, "Thaddeus M. Patterson", "Morbi Non Sapien Consulting", "30/06/2017", 3.89, 1.53))
            empleados.add(Empleado(13, "Darius Morse", "In Incorporated", "29/08/2008", 9.95, 1.10))
            empleados.add(Empleado(14, "Sade Mercer", "Curae; Incorporated", "24/04/2008", 8.57, 6.14))
            empleados.add(Empleado(15, "Clarke K. Hancock", "Non Lorem Limited", "10/02/2009", 2.79, 5.69))
            empleados.add(Empleado(16, "Kuame Klein", "Sit Foundation", "28/10/2006", 3.65, 5.46))
            empleados.add(Empleado(17, "Ginger Ayers", "Nunc Sed Institute", "11/06/2019", 0.49, 9.72))
            empleados.add(Empleado(18, "Zoe Dominguez", "Mi Aliquam Gravida Associates", "10/05/2002", 9.27, 0.93))
            empleados.add(Empleado(19, "Nolan V. Jarvis", "Turpis Company", "26/04/2015", 9.31, 1.21))
            empleados.add(Empleado(20, "Reese Hayden", "Tempor Bibendum Donec Corporation", "13/07/2011", 1.64, 4.88))
            return empleados
        }
    }
}