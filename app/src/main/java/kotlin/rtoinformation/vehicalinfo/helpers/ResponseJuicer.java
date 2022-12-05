package kotlin.rtoinformation.vehicalinfo.helpers;

import com.google.gson.Gson;
import kotlin.rtoinformation.vehicalinfo.datamodels.ExternalVehicleDetailsResponse;
import kotlin.rtoinformation.vehicalinfo.utils.GlobalReferenceEngine;
import kotlin.rtoinformation.vehicalinfo.utils.Utils;

public class ResponseJuicer {
    public static ExternalVehicleDetailsResponse responseJuice(String str) {
        if (Utils.isNullOrEmpty(str)) {
            return null;
        }
        try {
            String decrypt = EncryptionHandler.decrypt(str, GlobalReferenceEngine.dataAccessKey);
            if (Utils.isNullOrEmpty(decrypt)) {
                return null;
            }
            return (ExternalVehicleDetailsResponse) new Gson().fromJson(decrypt, ExternalVehicleDetailsResponse.class);
        } catch (Exception e) {
            return null;
        }
    }
}
