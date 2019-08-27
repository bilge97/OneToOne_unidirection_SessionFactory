package com.example.onetoone.crud;

import com.example.onetoone.model.Instructor;
import com.example.onetoone.model.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructor {

    public static void main(String[] args){

        //create Session Factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        //create Session
        Session session = factory.getCurrentSession();

        try{


            //start a transaction
            session.beginTransaction();

            // get instructor by primary key
            Long theId = 2L;
            Instructor tempInstrutor = session.get(Instructor.class , theId);

            //delete instructor
            //will also delete associated "details" object because of Cascadetype.ALL
            if(tempInstrutor!= null)
            {
                session.delete(tempInstrutor);
            }

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done.");
        }
        finally {
            factory.close();
        }




    }
}
