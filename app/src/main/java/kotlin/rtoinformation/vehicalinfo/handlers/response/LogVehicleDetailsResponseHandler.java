package kotlin.rtoinformation.vehicalinfo.handlers.response;

//import com.google.firebase.crashlytics.FirebaseCrashlytics;


import kotlin.rtoinformation.vehicalinfo.handlers.TaskHandler;

import org.json.JSONObject;

public class LogVehicleDetailsResponseHandler implements TaskHandler.JsonResponseHandler {
    private TaskHandler.ResponseHandler<JSONObject> responseHandler;

    public LogVehicleDetailsResponseHandler(TaskHandler.ResponseHandler<JSONObject> responseHandler2) {
        this.responseHandler = responseHandler2;
    }

    public void onError(String str) {
        this.responseHandler.onError(str);
    }

    public void onResponse(JSONObject jSONObject) {
        try {
            TaskHandler.ResponseHandler<JSONObject> responseHandler2 = this.responseHandler;
            if (responseHandler2 != null) {
                responseHandler2.onResponse(jSONObject);
            }
        } catch (Exception e) {
//            FirebaseCrashlytics.getInstance().recordException(e);
        }
    }
}
