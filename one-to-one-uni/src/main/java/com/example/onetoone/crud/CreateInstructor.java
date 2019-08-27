package com.example.onetoone.crud;

import com.example.onetoone.model.Instructor;
import com.example.onetoone.model.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructor {

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

            //create objects
            Instructor instructor1 = new Instructor("bilge" , "kurtoglu" , "bilge@mail.com");
            InstructorDetail instructorDetail1 = new InstructorDetail("youtube1" ,"hobby1");

            Instructor instructor2 = new Instructor("merve" , "kurtoglu" , "merve@mail.com");
            InstructorDetail instructorDetail2 = new InstructorDetail("youtube2" ,"hobby2");

            //associate objects
            instructor1.setInstructorDetail(instructorDetail1);
            instructor2.setInstructorDetail(instructorDetail2);

            //start a transaction
            session.beginTransaction();

            //save instructor
            session.save(instructor1);
            session.save(instructor2);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done.");
        }
        finally {
            factory.close();
        }




    }
}
