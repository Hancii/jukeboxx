package ba.unsa.etf.rpr.exception;

public class JukeBoxException extends Exception{

    public JukeBoxException(String msg, Exception reason) {super(msg, reason);}

    public JukeBoxException(String msg){
        super(msg);
    }

}
