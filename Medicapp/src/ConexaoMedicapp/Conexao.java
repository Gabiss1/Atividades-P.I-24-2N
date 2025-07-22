package ConexaoMedicapp;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Conexao {
    private static String URL = "jdbc:postgresql://localhost:5432/Biblioteca";
    private static final String usuario = "postgres";
    private static final String senha = "root";

    public static Connection conectar() {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(URL, usuario, senha);
            System.out.println("Conectado com sucesso!");
        } catch (SQLException error) {
            System.err.println("Erro ao conectar com o banco de dados: " + error.getMessage());

        } return conexao;
    }

    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexao fechada com sucesso!");
            } catch (SQLException error) {
                System.err.println("Erro ao fechar conexao: " + error.getMessage());
            }
        }
    }

    public static void main(String[] args){
        String nameDatabase = "teste"; // Nome do banco de dados a ser criado
        Connection testeConexao = Conexao.conectar();

        try (Connection connection = DriverManager.getConnection(URL, usuario, senha)) {
            Statement statement = connection.createStatement();
            String sql = "CREATE DATABASE " + nameDatabase;
            statement.executeUpdate(sql);
            URL = "jdbc:postgresql://localhost:5432/"+nameDatabase;
            System.out.println("Banco de dados criado com sucesso: " + nameDatabase);
            if (testeConexao != null) {
                Conexao.fecharConexao(testeConexao);
            }

        } catch (SQLException e) {
            System.out.println("Database já existente ");
            URL = "jdbc:postgresql://localhost:5432/"+nameDatabase;
            if (testeConexao != null) {
                Conexao.fecharConexao(testeConexao);
            }
        }


        //System.out.println("\n--- Conectado com sucesso! ---");
        //setUsers("Gabriel", "gabi@gmail.com", "1827-4765", "984930078", "15/07/2006");
        //setUsers("Lucca", "lucca@gmail.com", "7263-0219", "984933798", "20/04/2003");


        //System.out.println("\n--- Conectado com sucesso! ---");
        //getUsers();
        createTables();
    }

    public static void createTables(){
        String tableUser = "CREATE TABLE IF NOT EXISTS Primary_UserTest (" +
                "    User_ID BIGSERIAL PRIMARY KEY NOT NULL," +
                "    User_Name VARCHAR(255) NOT NULL," +
                "    User_Email VARCHAR(255) NOT NULL," +
                "    User_Address VARCHAR(255) NOT NULL," +
                "    User_Contact VARCHAR(255) NOT NULL," +
                "    User_Birth_Date DATE NOT NULL" +
                ")";
        String tableMedication = "CREATE TABLE IF NOT EXISTS MedicationTest(" +
                "    Medication_ID BIGSERIAL PRIMARY KEY NOT NULL," +
                "    Medication_Name VARCHAR(255) NOT NULL," +
                "    Medication_Category VARCHAR(255) NOT NULL," +
                "    Dosage VARCHAR(255) NOT NULL," +
                "    Time_Of_Usage VARCHAR(255) NOT NULL," +
                "    Medication_Pharmaceutical_Form VARCHAR(20) NOT NULL" +
                ")";
        String tableInstitute = "CREATE TABLE IF NOT EXISTS Legal_Entity_Institute (\n" +
                "    CNPJ VARCHAR(18) UNIQUE NOT NULL,\n" +
                "    fk_User_ID INT PRIMARY KEY NOT NULL,\n" +
                "    Institute_ID BIGSERIAL NOT NULL\n" +
                ");";
        String tableCustomer = "CREATE TABLE Customer (\n" +
                "    Customer_ID BIGSERIAL PRIMARY KEY NOT NULL,\n" +
                "    fk_User_ID BIGSERIAL NOT NULL,\n" +
                "    fk_Institute_ID BIGSERIAL NOT NULL\n" +
                ");";
        String tableNaturalPerson = "CREATE TABLE IF NOT EXISTS Natural_Person (\n" +
                "    CPF VARCHAR(14) UNIQUE NOT NULL,\n" +
                "    fk_User_ID BIGSERIAL PRIMARY KEY NOT NULL\n" +
                ");";
        String tableAppointmentBookNPerson = "CREATE TABLE IF NOT EXISTS Appointment_Book_Natural_Person (\n" +
                "    Appointment_ID BIGSERIAL PRIMARY KEY NOT NULL,\n" +
                "    Appointment_End_Date DATE NOT NULL,\n" +
                "    Appointment_Start_Date DATE NOT NULL,\n" +
                "    fk_Natural_Person_ID BIGSERIAL NOT NULL,\n" +
                "    fk_Medication_ID BIGSERIAL NOT NULL\n" +
                ");";
        String tableAppointmentBookInstitute = "CREATE TABLE IF NOT EXISTS Appointment_Book_Institute (\n" +
                "    Appointment_ID BIGSERIAL PRIMARY KEY NOT NULL,\n" +
                "    Appointment_End_Date DATE NOT NULL,\n" +
                "    Appointment_Start_Date DATE NOT NULL,\n" +
                "    fk_Medication_ID BIGSERIAL NOT NULL,\n" +
                "    fk_Institute_ID BIGSERIAL NOT NULL\n" +
                ");";
        String tableReminderInstitute = "CREATE TABLE Reminder_Institute (\n" +
                "    Reminder_ID BIGSERIAL PRIMARY KEY NOT NULL,\n" +
                "    Reminder_Time DATE NOT NULL,\n" +
                "    Reminder_Observations VARCHAR(255) NOT NULL,\n" +
                "    Reminder_Status BOOLEAN NOT NULL,\n" +
                "    fk_Appointment_Book_Institute_ID BIGSERIAL NOT NULL,\n" +
                "    fk_Customer_ID BIGSERIAL NOT NULL\n" +
                ");";
        String tableReminderNPerson = "CREATE TABLE Reminder_Natural_Person (\n" +
                "    Reminder_ID BIGSERIAL PRIMARY KEY NOT NULL,\n" +
                "    Reminder_Time DATE NOT NULL,\n" +
                "    Reminder_Observation VARCHAR(255) NOT NULL,\n" +
                "    Reminder_Status BOOLEAN NOT NULL,\n" +
                "    fk_Appointment_Book_Natural_Person_ID BIGSERIAL NOT NULL,\n" +
                "    fk_Natural_Person_ID BIGSERIAL NOT NULL\n" +
                ");";

        String tables[] = {tableUser, tableInstitute, tableMedication,
                tableAppointmentBookInstitute, tableAppointmentBookNPerson, tableNaturalPerson,
                tableReminderNPerson, tableReminderInstitute, tableCustomer};
        PreparedStatement stmt = null;

        try (Connection conexao = DriverManager.getConnection(URL, usuario, senha);
             Statement statement = conexao.createStatement()) {

            for(String tableCode: tables){
                statement.execute(tableCode);
            }
            System.out.println("Tabela criada com sucesso!");
            addForeignKeys();

        } catch (SQLException e) {
            System.err.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }

    public static void addForeignKeys(){
        String foreingKeyInstPUser = "ALTER TABLE Legal_Entity_Institute ADD CONSTRAINT FK_Legal_Entity_Institute_2\n" +
                "    FOREIGN KEY (fk_User_ID)\n" +
                "    REFERENCES Primary_User (User_ID)\n" +
                "    ON DELETE CASCADE;";
        String foreingKeyNPersonPUser = "ALTER TABLE Natural_Person ADD CONSTRAINT FK_Natural_Person_2\n" +
                "    FOREIGN KEY (fk_User_ID)\n" +
                "    REFERENCES Primary_User (User_ID)\n" +
                "    ON DELETE CASCADE;";
        String foreingKeyCustomerNPerson = "ALTER TABLE Customer ADD CONSTRAINT FK_Customer_2\n" +
                "    FOREIGN KEY (fk_User_ID)\n" +
                "    REFERENCES Natural_Person (fk_User_ID);";
        String foreingKeyCustomerInstitute = "ALTER TABLE Customer ADD CONSTRAINT FK_Customer_3\n" +
                "    FOREIGN KEY (fk_Institute_ID)\n" +
                "    REFERENCES Legal_Entity_Institute (fk_User_ID);";
        String foreingKeyAppoinBookNPersonID = "ALTER TABLE Appointment_Book_Natural_Person ADD CONSTRAINT FK_Appointment_Book_Natural_Person_2\n" +
                "    FOREIGN KEY (fk_Natural_Person_ID)\n" +
                "    REFERENCES Natural_Person (fk_User_ID);";
        String foreingKeyAppoinBookNPersonMedicID = "ALTER TABLE Appointment_Book_Natural_Person ADD CONSTRAINT FK_Appointment_Book_Natural_Person_3\n" +
                "    FOREIGN KEY (fk_Medication_ID)\n" +
                "    REFERENCES Medication (Medication_ID);";
        String foreingKeyAppoinBookInstMedicID = "ALTER TABLE Appointment_Book_Institute ADD CONSTRAINT FK_Appointment_Book_Institute_2\n" +
                "    FOREIGN KEY (fk_Medication_ID)\n" +
                "    REFERENCES Medication (Medication_ID);";
        String foreingKeyAppoinBookInstID = "ALTER TABLE Appointment_Book_Institute ADD CONSTRAINT FK_Appointment_Book_Institute_3\n" +
                "    FOREIGN KEY (fk_Institute_ID)\n" +
                "    REFERENCES Legal_Entity_Institute (fk_User_ID);";
        String foreingKeyReminderInstAppoinID = "ALTER TABLE Reminder_Institute ADD CONSTRAINT FK_Reminder_Institute_2\n" +
                "    FOREIGN KEY (fk_Appointment_Book_Institute_ID)\n" +
                "    REFERENCES Appointment_Book_Institute (Appointment_ID);";
        String foreingKeyReminderInstCustomerID = "ALTER TABLE Reminder_Institute ADD CONSTRAINT FK_Reminder_Institute_3\n" +
                "    FOREIGN KEY (fk_Customer_ID)\n" +
                "    REFERENCES Customer (Customer_ID);";
        String foreingKeyReminderNPersonAppoinID = "ALTER TABLE Reminder_Natural_Person ADD CONSTRAINT FK_Reminder_Natural_Person_2\n" +
                "    FOREIGN KEY (fk_Appointment_Book_Natural_Person_ID)\n" +
                "    REFERENCES Appointment_Book_Natural_Person (Appointment_ID);";
        String foreingKeyReminderNPersonCustomerID = "ALTER TABLE Reminder_Natural_Person ADD CONSTRAINT FK_Reminder_Natural_Person_3\n" +
                "    FOREIGN KEY (fk_Natural_Person_ID)\n" +
                "    REFERENCES Natural_Person (fk_User_ID);";

        String fks[] = {foreingKeyReminderNPersonCustomerID, foreingKeyReminderNPersonAppoinID, foreingKeyAppoinBookInstID,
                foreingKeyAppoinBookInstMedicID, foreingKeyAppoinBookNPersonID, foreingKeyAppoinBookNPersonMedicID,
                foreingKeyInstPUser, foreingKeyNPersonPUser, foreingKeyReminderInstAppoinID, foreingKeyReminderInstCustomerID,
                foreingKeyCustomerInstitute, foreingKeyCustomerNPerson};
        PreparedStatement stmt = null;

        try (Connection conexao = DriverManager.getConnection(URL, usuario, senha);
             Statement statement = conexao.createStatement()) {

            for(String fkCode: fks){
                statement.execute(fkCode);
            }
            System.out.println("Foreing Keys Adicionadas com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar Foreign Keys: " + e.getMessage());
        }
    }

    public static void setUsers(String name, String email, String address, String contact, String birth_date){
        String sql = "INSERT INTO Primary_User (User_Name, User_Email, User_Address, User_Contact, User_Birth_Date) VALUES (?, ?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = Conexao.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setString(3, address);
                stmt.setString(4, contact);
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                Date date = (Date) formato.parse(birth_date);
                stmt.setDate(5, date);

                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Aluno "+ name + " foi adicionado com sucesso!");
                }
            }
        } catch (SQLException | ParseException error) {
            System.err.println("Erro ao inserir o aluno: " + error.getMessage());
        } finally {
            try{
                if (stmt != null) stmt.close();
                if(conexao != null) fecharConexao(conexao);
            } catch (SQLException error) {
                System.err.println("Erro ao fechar conexao: " + error.getMessage());
            }
        }
    }

    public static void getUsers(){
        String sql = "SELECT * FROM Prymary_User ORDER BY User_ID";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conexao = Conexao.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                rs = stmt.executeQuery();
                System.out.println("\n--- Alunos cadastrados no BD ---");
                Boolean encontrouAluno = false;
                while (rs.next()) {
                    encontrouAluno = true;
                    int id = rs.getInt("User_ID");
                    String name = rs.getString("User_Name");
                    String email = rs.getString("User_Email");
                    String address = rs.getString("User_Address");
                    String contact = rs.getString("User_Contact");
                    Date birth_date = rs.getDate("User_Birth_Date");
                    System.out.println("ID: "+id + ", Name: " + name + ", Email: " + email + ", Address: " + address+
                            ", Contact: " + contact + ", Birth Date: " + birth_date);
                }
                if (!encontrouAluno) {
                    System.out.println("Nenhum aluno encontrado");
                }
                System.out.println("--------------------------------");
            }
        } catch(SQLException error){
            System.out.println("Erro ao conectar com o banco de dados: " + error.getMessage());
        } finally {
            try{
                if (rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conexao != null) fecharConexao(conexao);
            } catch(SQLException error){
                System.err.println("Erro ao fechar recursos após pesquisa: " + error.getMessage());
            }
        }
    }
}
