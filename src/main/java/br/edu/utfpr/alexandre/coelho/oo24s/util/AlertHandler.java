package br.edu.utfpr.alexandre.coelho.oo24s.util;

import javafx.scene.control.Alert;

public class AlertHandler {

    private static final Alert ALERT = new Alert(Alert.AlertType.ERROR);

    public AlertHandler() {
        ALERT.setTitle("Erro");
    }

    public static void openFormException(Exception ex) {
        System.out.println(ex.getMessage());
        ALERT.setTitle("Erro");
        ALERT.setHeaderText("Ocorreu um erro ao abrir a janela de cadastro!");
        ALERT.setContentText("Por favor, tente realizar a operação novamente!");
        ALERT.showAndWait();
    }

    public static void removeRecordException(Exception ex) {//String header, String content, 
        System.out.println(ex.getMessage());
        ALERT.setTitle("Erro");
        ALERT.setHeaderText("Ocorreu um erro ao remover o registro!");
        ALERT.setContentText("Por favor, tente realizar a operação novamente!");
        ALERT.showAndWait();
    }

    public static void chooseRecordException() {
        ALERT.setTitle("Erro");
        ALERT.setHeaderText("Nenhum registro selecionado");
        ALERT.setContentText("Por favor, selecione um registro na tabela!");
        ALERT.showAndWait();
    }

    public static void loadFormException(String context, Exception ex) {
        System.out.println(ex.getMessage());
        ALERT.setTitle(" .: Hotelaria Joestar :. ");
        ALERT.setHeaderText("Atenção, ocorreu um erro!");
        ALERT.setContentText("Falha ao abrir a tela de " + context);
        ALERT.showAndWait();
    }

    public static void loginException(Exception ex) {
        System.out.println(ex.getMessage());
        ALERT.setTitle("Erro");
        ALERT.setHeaderText("Usuário e/ou senha inválidos!");
        ALERT.setContentText("Por favor, tente novamente!");
        ALERT.showAndWait();
    }
}
