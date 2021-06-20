package view;

public enum View {
    MAIN("/fxml/MainMenu.fxml"),
    INIT("/fxml/InitPopulation.fxml"),
    GENETIC("/fxml/GeneticAlgorithm.fxml"),
    PSO("/fxml/PSOAlgorithm.fxml"),
    HILL_CLIMBING("/fxml/HillClimbing.fxml");

    private String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}