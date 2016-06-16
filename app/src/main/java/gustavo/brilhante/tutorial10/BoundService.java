package gustavo.brilhante.tutorial10;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Gustavo on 14/06/2016.
 */
public class BoundService extends Service {
    private final IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        BoundService getService(){
            return BoundService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public int multiply(int number01, int number02){
        return number01*number02;
    }

    public int sum(int number01, int number02){
        return number01+number02;
    }

    public int less(int number01, int number02){
        return number01-number02;
    }
    public int divide(int number01, int number02){
        return number01/number02;
    }

}
