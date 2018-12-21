package kfu.apriorialgorithm;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Application {
    private static DecimalFormat df = new DecimalFormat("#.#");

    public static void main(String[] args) throws IllegalAccessException {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        TabPane tabPane = new TabPane();
        List<Computer> computers = getComputers();
        HashMap<String, HashMap<String, Double>> aprioriItems = getAprioriItems(computers);

        primaryStage.setTitle("Apriori Algorithm");
        showAprioriItems(aprioriItems, computers);
        for (Map.Entry<String, HashMap<String, Double>> t : aprioriItems.entrySet()) {
            Tab tab = new Tab();
            PieChart pieChart = new PieChart();

            for (Map.Entry<String, Double> p : t.getValue().entrySet()){
                PieChart.Data slice = new PieChart.Data(
                        p.getKey() + " [" + df.format(p.getValue() * 100 / computers.size()) + "%]",
                        p.getValue()
                );
                pieChart.getData().add(slice);
            }

            pieChart.setLegendSide(Side.BOTTOM
            );
            tab.setText(t.getKey());
            tab.setContent(pieChart);
            tabPane.getTabs().add(tab);
        }

        root.getChildren().addAll(tabPane);
        primaryStage.setScene(new Scene(root, 840, 800));
        primaryStage.show();
    }

    public static List<Computer> getComputers() {
        List<Computer> result;
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("META-INF/spring/application-context.xml");
        ctx.refresh();
        result = ctx.getBean("jdbcComputerDao", JdbcComputerDao.class).getAllComputers();
        return result;
    }

    private static HashMap<String, HashMap<String, Double>> getAprioriItems(List<Computer> computers) throws IllegalAccessException {
        HashMap<String, HashMap<String, Double>> result = new HashMap<>();
        HashMap<String, Double> value;
        for (Field field : Computer.class.getDeclaredFields()) {
            field.setAccessible(true);
            if (!field.getName().equals("ball") && !field.getName().equals("id") && !field.getName().equals("ipAddress") && !field.getName().equals("computerName")) {
                value = new HashMap<>();
                for (Computer c2 : computers) {
                    for (Computer c1 : computers) {
                        if (!c1.myequals(c2) && field.get(c1).equals(field.get(c2)) && value.containsKey(String.valueOf(field.get(c1)))) {
                            value.put(field.get(c1).toString(), value.get(String.valueOf(field.get(c1))) + 1);
                            break;
                        } else {
                            if (!field.get(c1).equals("nill") && !value.containsKey(String.valueOf(field.get(c1))))
                                value.put(field.get(c1).toString(), 0.0);
                            if (!field.get(c2).equals("nill") && !value.containsKey(String.valueOf(field.get(c2))))
                                value.put(field.get(c2).toString(), 0.0);
                        }
                    }
                }
                for (Map.Entry<String, Double> p : value.entrySet()) {
                    if (p.getValue() == 0) {
                        value.put(p.getKey(), 1.0);
                    }
                }
                result.put(field.getName(), value);
            }
        }

        return result;
    }

    private static void showAprioriItems(HashMap<String, HashMap<String, Double>> aprioriItems, List<Computer> computers){
        for (Map.Entry<String, HashMap<String, Double>> t : aprioriItems.entrySet()) {
            System.out.println(t.getKey() + " : ");
            for (Map.Entry<String, Double> p : t.getValue().entrySet())
                System.out.println("    " + p.getKey() + " -> " + p.getValue() + " || " +df.format(p.getValue() * 100 / computers.size()) + "%");
        }
    }
}









/*



Group root = new Group();

        Map<String, double[]> map = getComputers();
        ObservableList<Map.Entry<String, double[]>> items = FXCollections.observableArrayList(map.entrySet());
        TableView<Map.Entry<String, double[]>> table = new TableView<Map.Entry<String, double[]>>(items);

        TableColumn<Map.Entry<String, double[]>, String> column;
        column = new TableColumn<Map.Entry<String, double[]>, String>("Names");
        column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, double[]>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, double[]>, String> param) {
                return new SimpleStringProperty(param.getValue().getKey());
            }
        });

        table.getColumns().add(column);

        for(int i = 0; i<n; i++){
            column = new TableColumn<Map.Entry<String, double[]>, String>(comps[i].getComputerName());
            column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, double[]>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, double[]>, String> param) {
                    double result = param.getValue().getValue()[p++];
                    if(p == n - 1) p = 0;
                    return new SimpleStringProperty(String.valueOf(Math.round(result*100)) + "%");
                }
            });
            table.getColumns().add(column);
        }


        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList();
        for (int i = 0; i < n; i++)
            pieChartData.add(new PieChart.Data(comps[i].getComputerName(), comps[i].getBall()));
        PieChart chart = new PieChart(pieChartData);

        ObservableList<PieChart.Data> pieChartData2 =
                FXCollections.observableArrayList();

        HashMap<String, Double> hashMap = new HashMap<String, Double>();
        for(Field field: Computer.class.getDeclaredFields()){
            field.setAccessible(true);
            for(Computer comp1: comps){
                for(Computer comp2: comps){
                        if(field.get(comp1).toString().equals(field.get(comp2).toString())) {
                            hashMap.put(field.getName() + " : " + field.get(comp1).toString(), hashMap.get(field.getName() + " : " + field.get(comp1)) == null ? 0 : hashMap.get(field.getName() + " : " + field.get(comp1)) + 1);
                            hashMap.put(field.getName() + " : " + field.get(comp2).toString(), hashMap.get(field.getName() + " : " + field.get(comp2)) == null ? 0 : hashMap.get(field.getName() + " : " + field.get(comp2)) + 1);
                        }

                }
            }
        }
        for(Map.Entry<String, Double> t: hashMap.entrySet()){
            System.out.println(t.getKey() + "  " + t.getValue());
            if(!t.getKey().contains("ball"))
            pieChartData2.add(new PieChart.Data(t.getKey() + " | " + t.getValue(), t.getValue()));
        }
        PieChart chart2 = new PieChart(pieChartData2);
        chart2.setMinSize(800, 800);
        Stage enotherstage = new Stage();
        Group enothergroupe = new Group();
        enothergroupe.getChildren().add(chart2);
        enotherstage.setScene(new Scene(enothergroupe, 800, 800));
        enotherstage.show();


        root.getChildren().addAll(table);


        Stage stage = new Stage();
        Group root2 = new Group();
        root2.getChildren().add(chart);
        stage.setScene(new Scene(root2, 500, 400));
        stage.show();


public static Computer[] generateComps(int n){
        Lorem lorem = LoremIpsum.getInstance();
        Random random = new Random();
        Computer[] result = new Computer[n];
        for (int i = 0; i < n; i++) {
            result[i] = new Computer(
                    lorem.getZipCode(),
                    random.nextBoolean() == true? "Windows" : "Linux",
                    random.nextInt(2) + "." + random.nextInt(2),
                    random.nextInt(4)*2 + ".0",
                    random.nextInt(4)*500 + ".0"
            );
        }
        for(Computer computer: result)
            computer.setBall(computer.getBall() / Computer.class.getDeclaredFields().length);
        return result;
    }

    List<Computer> computers = getComputers();
        for(Map.Entry<String, double[]> entry: map.entrySet()){
        System.out.print(entry.getKey() + " : " );
        for (int i = 0; i < entry.getValue().length; i++) {
        System.out.print(entry.getValue()[i] + " ");
        }
        System.out.println();
        }



        for(Computer c1 : computers){
            for(Computer c2: computers){
                System.out.println("[ " + c1.getId() + " , " + c2.getId() + " ] -> " + c1.equals(c2));
            }
        }

        for (Map.Entry<String, Double> t : aprioriItems.entrySet()) {
            System.out.println(t.getKey() + " -> " + t.getValue());
        }

[cpu] Intel core i  generation -> 1.0
[diskSpace] 2000 -> 1.0
[bitOS] win86 -> 1.0
[bitOS] win64 -> 1.0
[osVersion] Windows 10 -> 1.0
[videoCard] NVideo Geforce 1024 -> 1.0
[bitOS] win32 -> 1.0
[videoCard] NVideo Geforce 2048 -> 1.0
[osName] Windows -> 1.0
[videoCard] NVideo 512 -> 1.0
[bitOS] nill -> 1.0
[freeDiskSpace] 530 -> 1.0
[cpuId] 124.001 -> 1.0
[screenQuality] 1200 X 800 -> 1.0
[osVersion] Ubuntu 16 -> 1.0
[osVersion] Ubuntu 15 -> 1.0
[videoCard] NVideo Standard 1024 -> 1.0
[osName] Ubuntu -> 1.0
[osVersion] Windows 7 -> 1.0
[fillDiskSpace] 470 -> 1.0
[osVersion] Windows 8 -> 1.0
[rm] 8 -> 1.0
[diskSpace] 1000 -> 1.0
*/