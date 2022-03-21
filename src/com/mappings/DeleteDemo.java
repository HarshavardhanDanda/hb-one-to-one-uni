package com.mappings;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLOutput;

public class DeleteDemo {
    public static void main(String[] args){

        //create session factory
        SessionFactory factory= new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        //create session
        Session session= factory.getCurrentSession();
        try{

            session.beginTransaction();
            int theId=1;
            Instructor tempInstructor=session.get(Instructor.class, theId);
            System.out.println("Found Instructor: "+ tempInstructor);

            if(tempInstructor!=null){
                System.out.println("Deleting "+tempInstructor);
                session.delete(tempInstructor);//also delete details object cuz of cascade
            }
            //commit transaction
            session.getTransaction().commit();

            System.out.println("completed!!");
        }finally{
            factory.close();
        }
    }
}
