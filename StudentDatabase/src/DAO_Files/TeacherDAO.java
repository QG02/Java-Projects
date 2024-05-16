package DAO_Files;

import Source_Files.Teacher;
import java.sql.*;

public class TeacherDAO {

    private static final String TeacherQuery = "SELECT * FROM teacher WHERE teacher_id = ?";

    private final DAOFactory daoFactory;

    public TeacherDAO(DAOFactory daoFactory) {

        this.daoFactory = daoFactory;

    }

    public Teacher find(int id){

        Teacher teacher = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {

            conn = daoFactory.getConnection();

            if (conn != null) {

                ps = conn.prepareStatement(TeacherQuery);
                ps.setInt(1, id);
                rs = ps.executeQuery();

                if (rs.next()) {

                    int teacherID = rs.getInt("teacher_id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String email = rs.getString("email");
                    String specialization = rs.getString("specialization");

                    teacher = new Teacher(teacherID, firstName, lastName, email, specialization);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return teacher;
    }
}
