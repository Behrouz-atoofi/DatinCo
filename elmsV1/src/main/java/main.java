import model.Category_elementDTO;
import model.EmployeeDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class main {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("elms");
        EntityManager entityManager = factory.createEntityManager();

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("javad");
        employeeDTO.setFamily("yasari");
        employeeDTO.setUsername("123");
        employeeDTO.setPassword("123");
        employeeDTO.setEmail("hasani@gmail.com");
        employeeDTO.setManager(employeeDTO);

        Category_elementDTO role = new Category_elementDTO();
        role.setElement_id(3);
        employeeDTO.setRole(role);
        System.out.println(role.getCategory());

        entityManager.getTransaction().begin();
        entityManager.persist(employeeDTO);
        entityManager.getTransaction().commit();
    }
}
