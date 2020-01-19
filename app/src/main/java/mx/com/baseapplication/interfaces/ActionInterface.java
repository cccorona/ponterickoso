package mx.com.baseapplication.interfaces;


import mx.com.baseapplication.model.GenericalError;
import mx.com.baseapplication.utils.Enums;

public interface ActionInterface {

    void OnErrorAction(GenericalError error);
    void OnActionSelected(Enums.ACCIONES action, Object... params);

}
