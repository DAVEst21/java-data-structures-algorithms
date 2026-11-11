package registros.carreras;

import colecciones.edlineal.arreglos.Arreglo;
import colecciones.ednolineal.Arreglo3D;
import utils.entradasalida.cadenas.Salida;

public class GestorCarreras {
    protected Arreglo3D kilometrosRecorridos; //Guarda solo los kilometros
    protected Arreglo corredores; //Me guarda la info de los corredores
    protected Arreglo eventos; //Me guarda la infor de las carreras
    protected Arreglo anios; //Me guarda la info de los años
    
    //Creo un constructor que me indique cuántos hay de cada cosa
    public GestorCarreras(int numCorredores, int numEventos,
            int numAnios){
        corredores=new Arreglo(numCorredores);
        eventos=new Arreglo(numEventos);
        anios=new Arreglo(numAnios);
        kilometrosRecorridos=new Arreglo3D(numEventos, 
                                    numCorredores, numAnios, 0.0);
    }

    //Método para agregar corredores
    public boolean agregarCorredor(int noCorredorAsignado, 
                                   String nombre, int edad,
                                   char sexo){
        //Para crear un corredor, me pasan sus datos y luego hago
        // un new, despupés lo meto al arreglo de corredores
        Corredor nuevoCorredor=new Corredor(noCorredorAsignado, 
                                            nombre, edad, sexo);
        //antes de ponerlo en le arreglo corredores,
        //hay que ver que no se duplique ese número de corredor
        int busquedaCorredor=(int)corredores.buscar(nuevoCorredor);

        if(busquedaCorredor>=0){ //es decir, existe, no lo agregamos
            return false;
        }
        //cualquier cosa abajo se hace si no existe

        int retorno=corredores.poner(nuevoCorredor);
        if(retorno>=0 ){
            return true;
        }else{ //me regresó -1, error
            return false;
        }
    }

    //Método para agregar eventos
    public boolean agregarEvento(String nombre, String lugar,
                                int anioFundacion){

        //Recibo los datos del evento
        //Checo que no exista ese evento
        int busquedaEvento=(int)eventos.buscar(nombre);

        //Sino existe lo agregamos al arreglo de eventos
        if(busquedaEvento>=0){ //ya existe ese vento
            return false; //no puedo agregar un nuevo evento
        }
        //Solo se crea el objeto si se va agregar
        //Creo un evento nuevo con esos datos
        Evento nuevoEvento=new Evento(nombre, lugar, anioFundacion);

        //Agregarlo al arreglo de eventos
        int retorno=eventos.poner(nuevoEvento);
        if(retorno>=0){ //si pudo agregarlo
            return true;
        }else{ //hubo error
            return false;
        }
    }

    //Método para agregar añios
    public boolean agregarAnio(int anio){
        //Recibo el dato del año a agregar,
        //Primero checo que ese año no exista
        int busquedaAnio=(int)anios.buscar(anio);
        if(busquedaAnio<0){ //no existe, -1
            //Entonces lo agregamos
            //En caso que no exista, lo puedo agregar
            int retorno= anios.poner(anio);
            if (retorno>=0){ //si pudo
                return true;
            }else{ //hay error, -1
                return false;
            }
        }else{ //ya existe
            return false;
        }
    }

    //Busca los índices de una celda y me los regresa
    //en un arreglo, 0 (Corredor), 1 (Evento), 2 (Anio)
    private Arreglo buscarIndicesKms(int noCorredor, String nombreE,
                                     int anio){
        int indiceEvento=(int)eventos.buscar(nombreE);
        int indiceCorredor=(int)corredores.buscar(noCorredor);
        int indiceAnio=(int)anios.buscar(anio);

        if(indiceEvento>=0 && indiceCorredor>=0 && indiceAnio>=0){
            //son válidos
            //Creo un arreglo con esos índices y lo retorno
            Arreglo indices=new Arreglo(3);
            indices.poner(indiceCorredor); //0
            indices.poner(indiceEvento); //1
            indices.poner(indiceAnio); //2
            return indices;
        }else{ //uno no se encontró
            return null;
        }

    }

    //Método que agrega kilómetros
    public boolean agregarKilometros(double km, int noCorredor,
                                     String nombreEvento, int anio){
        //Me dan los datos de dónde se va a guardar
        //el kilometraje recorrido,
        //Necesito encontrar los índices de esos datos
        Arreglo indicesKms=buscarIndicesKms(noCorredor, 
                                        nombreEvento, anio);
        if(indicesKms!=null){
            //Para con esos índices poder en el cubo, guardarlos kms
            int indiceEvento=(int)indicesKms.obtener(1);
            int indiceCorredor=(int)indicesKms.obtener(0);
            int indiceAnio=(int)indicesKms.obtener(2);
            return kilometrosRecorridos.cambiarCelda(indiceEvento, 
                                                indiceCorredor,
                                                indiceAnio, km);
        }else{ //el asreglo de índices es nulo, no ppodemos hacer nada
            return false;
        }
    }

    public void mostrarDatos(){
        Salida.salidaPorDefecto("Eventos: \n");
        eventos.imprimirDes();
        Salida.salidaPorDefecto("\n");
        Salida.salidaPorDefecto("Corredores: \n");
        corredores.imprimirDes();
        Salida.salidaPorDefecto("\n");
        Salida.salidaPorDefecto("Anios: \n");
        anios.imprimirDes();
        Salida.salidaPorDefecto("\n");
        Salida.salidaPorDefecto("Kms: \n");
        kilometrosRecorridos.imprimirXColumnas();
        Salida.salidaPorDefecto("\n");
    }
    
    /*
    Un usuario quiere obtener información de kilómetros
    recorridos usando consultas como ésta:
    Obtener la cantidad de kilómetros recorridos por Juan
    en el año 2010, 2011 y 2014 en la carrera de
    "Bicentenario"
    
    -Obtener la cantidad de kilómetros recorridos de Pedro
    en el año 1999 en las carreras de "Bicentenario" y
    "Carrera Max".
    */
    
    public Double kilometrosXCorredor(int noCorredor, Arreglo carrerasParticipantes, Arreglo aniosParticipantes) {
        //Se tiene el cáluclo de un solo corredor
        //Se tiene que calcular de varias carreras
        Double kmCorredor = 0.0;
        for (int cadaCarrera = 0; cadaCarrera<carrerasParticipantes.cantidad(); cadaCarrera++) {
            //Tengo que ir sumando todos los kilómetros de cada
            //carrera de un corredor en ciertos años
            String carreraTemporal = (String) carrerasParticipantes.obtener(cadaCarrera);
            Double kmCarreraEvento = kilometrosXCorredorXCarrera (noCorredor, carreraTemporal, aniosParticipantes);
            
            //Acumulamos los kms del corredor de todas las carreras
            if (kmCarreraEvento == null) {
                return null;
            }
            else {
                kmCorredor = kmCorredor + kmCarreraEvento;
            }
        }
        return kmCorredor;
    }
    
    public Double kilometrosXCorredorXCarrera(int noCorredor, String carreraParticipante, Arreglo aniosParticipantes) {
        //Obtener los kms de un corredor de una sola carrera, pero de todos los anios
        Double kmAnio = 0.0;
        for (int cadaAnio = 0; cadaAnio < aniosParticipantes.cantidad(); cadaAnio++) {
            //Obtengo una celda del cubo con kilómetros de fila, columna, profundidad
            //necesito los 3 datos indirectos de noCorredor, nombre del evento y el anio
            int anioTemporal = (int) aniosParticipantes.obtener(cadaAnio);
            //Obtener los indices (los datos directos del cubo) de los 3 datos indirectos
            Arreglo indicesDirectos = buscarIndicesKms (noCorredor, carreraParticipante, anioTemporal);
            Double kmsCelda = 
                    (Double) kilometrosRecorridos.obtenerCelda((int) indicesDirectos.obtener(0), (int) indicesDirectos.obtener(1), (int) indicesDirectos.obtener(2));
            if (kmsCelda == null) {
                return null;
            }
            else {
                kmAnio = kmAnio + kmsCelda;
            }
        }
        return kmAnio;
    }
    
}
