module com.oop.fmradiostation {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.oop.fmradiostation to javafx.fxml;
    exports com.oop.fmradiostation;
    opens com.oop.fmradiostation.login to javafx.fxml;
    exports com.oop.fmradiostation.login;
    opens com.oop.fmradiostation.Sabbir.production_manager to javafx.fxml;
    exports com.oop.fmradiostation.Sabbir.production_manager;
    opens com.oop.fmradiostation.Sabbir.radio_jockey to javafx.fxml;
    exports com.oop.fmradiostation.Sabbir.radio_jockey;
    opens com.oop.fmradiostation.Das.studio_coordinator to javafx.fxml;
    exports com.oop.fmradiostation.Das.studio_coordinator;
    opens com.oop.fmradiostation.Das.sound_engineer to javafx.fxml;
    exports com.oop.fmradiostation.Das.sound_engineer;
    opens com.oop.fmradiostation.Elora.finance_officer to javafx.fxml;
    exports com.oop.fmradiostation.Elora.finance_officer;
    opens com.oop.fmradiostation.Elora.listener to javafx.fxml;
    exports com.oop.fmradiostation.Elora.listener;
    opens com.oop.fmradiostation.Paul.station_manager to javafx.fxml;
    exports com.oop.fmradiostation.Paul.station_manager;
    opens com.oop.fmradiostation.Paul.marketing_executive to javafx.fxml;
    exports com.oop.fmradiostation.Paul.marketing_executive;


}