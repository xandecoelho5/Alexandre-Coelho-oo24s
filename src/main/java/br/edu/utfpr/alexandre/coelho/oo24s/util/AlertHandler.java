package br.edu.utfpr.alexandre.coelho.oo24s.util;

import javafx.scene.control.Alert;

public class AlertHandler {

    private static final Alert ALERT = new Alert(Alert.AlertType.ERROR);

    public static void openFormException(Exception ex) {
        setAlert("Ocorreu um erro ao abrir a janela de cadastro!", "Por favor, tente realizar a operação novamente!", ex);
    }

    public static void removeRecordException(Exception ex) {
        setAlert("Ocorreu um erro ao remover o registro!", "Por favor, tente realizar a operação novamente!", ex);
    }

    public static void chooseRecordException() {
        ALERT.setTitle("Erro");
        ALERT.setHeaderText("Nenhum registro selecionado");
        ALERT.setContentText("Por favor, selecione um registro na tabela!");
        ALERT.showAndWait();
    }
    
    public static void cantDeleteException() {
        ALERT.setTitle("Erro");
        ALERT.setHeaderText("Seleção Indisponível");
        ALERT.setContentText("Não é possível excluir o usuário que está logado!");
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
        setAlert("Usuário e/ou senha inválidos!", "Por favor, tente novamente!", ex);
    }

    public static void showReportException(Exception ex) {
        setAlert("Falha ao exibir relatório!", "Falha ao exibir relatório!", ex);   
    }
   
    private static void setAlert(String header, String context, Exception ex){
        System.out.println(ex.getMessage());
        ALERT.setTitle("Erro");
        ALERT.setHeaderText(header);
        ALERT.setContentText(context);
        ALERT.showAndWait();
    }
}
