package Manager;

import Constants.STATUS;

public abstract class StatusManager{
    //1:en casa central, 2:en viaje, 3: en comercio; 4 pasado a ventas o dado de baja
    private static final String CASA_CENTRAL = STATUS.CASA_CENTRAL;
    private static final String EN_VIAJE = STATUS.EN_VIAJE;
    private static final String EN_COMERCIO = STATUS.EN_COMERCIO;
    private static final String PASADO_A_VENTAS = STATUS.PASADO_A_VENTAS;
    private static final String DADO_DE_BAJA = STATUS.DADO_DE_BAJA;

    public static String getStatus(int status){
        String response = null;
        switch (status){
            case 1:
                response = CASA_CENTRAL;
                break;
            case 2:
                response = EN_VIAJE;
                break;
            case 3:
                response=EN_COMERCIO;
                break;
            case 4:
                response=PASADO_A_VENTAS;
                break;
            case 5:
                response = DADO_DE_BAJA;
                break;
        }
        return response;
    }
}
