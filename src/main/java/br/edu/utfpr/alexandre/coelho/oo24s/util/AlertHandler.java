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
        setAlert("Nenhum registro selecionado", "Por favor, selecione um registro na tabela!");
    }
    
    public static void cantDeleteException() {
        setAlert("Seleção Indisponível", "Não é possível excluir o usuário que está logado!");
    }
    
    public static void loginException(Exception ex) {
        setAlert("Usuário e/ou senha inválidos!", "Por favor, tente novamente!", ex);
    }

    public static void showReportException(Exception ex) {
        setAlert("Falha ao exibir relatório!", "Falha ao exibir relatório!", ex);   
    }

    public static void validationFormException() {
        setAlert("Erro ao adicionar novo registro", "Por favor, preencha todos os campos obrigatórios!");
    }
    
    public static void noChoiceSelected() {
        setAlert("Nenhum registro selecionado", "Por favor, selecione um registro no caixa de Combo!");
    }
    
    public static void dateException() {
        setAlert("Datas inválidas", "Por favor, a data de saída deve ser posterior à data de entrada!");
    }
    
    public static void loadFormException(String context, Exception ex) {
        ex.printStackTrace();
        ALERT.setTitle(" .: Hotelaria Joestar :. ");
        ALERT.setHeaderText("Atenção, ocorreu um erro!");
        ALERT.setContentText("Falha ao abrir a tela de " + context);
        ALERT.showAndWait();
    }

    private static void setAlert(String header, String context, Exception ex){
        ex.printStackTrace();
        ALERT.setTitle("Erro");
        ALERT.setHeaderText(header);
        ALERT.setContentText(context);
        ALERT.showAndWait();
    }
    
    private static void setAlert(String header, String context) {
        ALERT.setTitle("Erro");
        ALERT.setHeaderText(header);
        ALERT.setContentText(context);
        ALERT.showAndWait();
    }
}
