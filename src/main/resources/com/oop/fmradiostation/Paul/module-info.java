module group_27.fm_radio_station {
    requires javafx.controls;
    requires javafx.fxml;


    opens group_27.fm_radio_station to javafx.fxml;
    exports group_27.fm_radio_station;
}