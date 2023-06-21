package com.kodilla.Service_Cars_Front.forms;

import com.kodilla.Service_Cars_Front.domain.CarDto;
import com.kodilla.Service_Cars_Front.service.CarService;
import com.kodilla.Service_Cars_Front.view.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CarForm extends FormLayout {

    public String[] bodyTypeValues = {"HATCHBACK","STATION_WAGON","CABRIO","SEDAN","VAN"};
    public String[] carBrandValues = {"CITROEN","PEUGEOT","RENAULT"};

    private ComboBox<String> brand = new ComboBox<>("Brand");
    private TextField model = new TextField("Model");
    private TextField manufactureYear = new TextField("Manufacture Year");
    private TextField vinNumber = new TextField("Vin number");
    private NumberField engineSize = new NumberField("Engine size");
    private TextField plateNumber = new TextField("Plate number");
    private ComboBox<String> bodyType = new ComboBox<>("Body type");
    private TextField customerId = new TextField("Customer Id");

    private Button save = new Button("Save");
    private Button update = new Button("Update");
    private CarService service = CarService.getInstance();
    private Binder<CarDto> binder = new Binder<>(CarDto.class);

    private MainView mainView;

    public CarForm (MainView mainView) {

        bodyType.setItems(bodyTypeValues);
        bodyType.setAllowCustomValue(false);
        brand.setItems(carBrandValues);
        brand.setAllowCustomValue(false);
        this.mainView = mainView;
        save.addClickListener(event -> save());
        update.addClickListener(event -> update());
        binder.bindInstanceFields(this);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        update.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(brand,model,manufactureYear,vinNumber,engineSize,plateNumber,bodyType,customerId,save, update);
    }

    private void save() {
        update.setVisible(false);
        CarDto carDto = binder.getBean();
        service.save(carDto);
        mainView.refresh();
        setCar(null);
    }

    private void update() {
        save.setVisible(false);
        CarDto carDto = binder.getBean();
        service.update(carDto);
        mainView.refresh();
        setCar(null);
    }


    public void setCar(CarDto carDto) {
        binder.setBean(carDto);

        if (carDto==null) {
            setVisible(false);
        } else {
            setVisible(true);
            brand.focus();
        }
    }
}
