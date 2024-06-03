import java.util.Locale;
import java.util.Scanner;

public class ConversorMoneda {
    private String opcion;
    private String opcion2;
    private double cantidad;


    public String getOpcion() {
        return opcion;
    }
    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public String getOpcion2() {
        return opcion2;
    }
    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
    }

    public double getCantidad() {
        return cantidad;
    }
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }


    public void conversor(){
        Scanner p = new Scanner(System.in);
        ApiRequest api = new ApiRequest();
        api.apiconversion();

        while (true){

            System.out.println("""
                    Estas son las diferentes monedas que manejamos: 
                    1 - Dolar Estadounidense codigo(USD)
                    2 - Euro codigo(EUR) 
                    3 - Peso colombiano codigo(COP)
                    4 - Libra Sterlina codigo(GBP)
                    5 - Pesos Mexicanos codigo(MXN)
                    6 - Soles Peruanos codigo(PEN)
                    7 - Cordoba Nicaraguense codigo(NIO)
                    8 - Quetzal Guatemala codigo(GTQ)
                    9 - Colon Costa Rica codigo(CRC)
                    """);
            System.out.println("¿Cual es la moneda que desea hacer la conversion? ingrese el codigo");
            opcion = p.nextLine().toUpperCase(Locale.ROOT);

            if(opcion.equals("salir")){
                System.out.println("Gracias por usar el conversor de monedas");
                break;
            }
            else {
                System.out.println("Cantidad a Convertir: ");
                cantidad = p.nextDouble();
                p.nextLine();
                System.out.println("A que moneda quiere convertirlo Ingrese el codigo:");
                opcion2 = p.nextLine().toUpperCase();

                Double tasaOrigen = api.getTasas().get(opcion);
                Double tasaDestino = api.getTasas().get(opcion2);

                if (tasaOrigen != null && tasaDestino != null) {
                    // Convertir de monedaOrigen a USD
                    double cantidadEnUSD = cantidad / tasaOrigen;
                    // Convertir de USD a monedaDestino
                    double cantidadFinal = cantidadEnUSD * tasaDestino;

                    System.out.printf("Cantidad = %.2f %s\n", cantidadFinal, opcion2);
                }else{
                    System.out.println("Código de moneda no válido o la tasa de cambio no está disponible.");
                }
                System.out.println("Quieres seguir con la aplicacion si/no" );
                opcion = p.nextLine().toLowerCase();
                if (opcion.equals("no")){
                    System.out.println("Gracias, por usar nuestro conversor ");
                    break;
                }

            }
        }
    }
}
