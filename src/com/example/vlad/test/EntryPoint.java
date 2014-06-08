package com.example.vlad.test;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class EntryPoint
{
	private SessionFactory sessionFactory;
   private PlainJavaObj           plainJavaObj1;
   private PlainJavaObj           plainJavaObj2;
   private PlainJavaObj           plainJavaObj3;

   final static public void main( String arguments[] )
   {
	  EntryPoint example;
      example = new EntryPoint();
      
      
      example.createPersistentObjects();
      example.readAndDisplayPersistentObjects();
      example.manipulatePersistentObjects();
      example.readAndDisplayPersistentObjects();
      example.cleanup();
   }

   private EntryPoint()
   {
      Configuration configuration;

      Logger.getLogger( "org.hibernate" ).setLevel( Level.SEVERE );  // Supress Hibernate's excessive output

      configuration = new Configuration();
      configuration.setProperty( "hibernate.dialect",                 "org.hibernate.dialect.MySQLDialect" );                                          // Customize this for your particular RDBMS
      configuration.setProperty( "hibernate.connection.driver_class", "com.mysql.jdbc.Driver" );                                                            // Customize this for your particular RDBMS
      configuration.setProperty( "hibernate.connection.url",          "jdbc:mysql://localhost:3306/database" );  // Customize this for your particular RDBMS
      configuration.setProperty( "hibernate.connection.username",     "root" );                                                                       // Customize this for your particular RDBMS
      configuration.setProperty( "hibernate.connection.password",     "" );                                 // Customize this for your particular RDBMS installation
      configuration.setProperty( "hibernate.connection.pool_size",    "1" );                                // Customize this for your particular RDBMS installation
      configuration.setProperty( "hibernate.cache.provider_class",    "cache.internal.NoCacheProvider" );  // This is not ready for prime-time
      configuration.setProperty( "hibernate.show_sql",                "false" );  // Tell hibernate to not echo the SQL
      configuration.setProperty( "hibernate.hbm2ddl.auto",            "create" );
      configuration.addAnnotatedClass( PlainJavaObj.class );

      sessionFactory = configuration.buildSessionFactory();
   }

   final private void createPersistentObjects()
   {
      Session     session;
      boolean     committed;
      Transaction transaction;

      session = sessionFactory.openSession();

      try
      {
         committed = false;
         transaction = session.beginTransaction();

         try
         {
            plainJavaObj1 = new PlainJavaObj( "First persistent object", new Date() );
            session.save( plainJavaObj1 );

            plainJavaObj2 = new PlainJavaObj( "A second persistent object", new Date() );
            session.save( plainJavaObj2 );

            transaction.commit();
            committed = true;
         }
         finally
         {
            if ( !committed )
            {
               transaction.rollback();
            }
         }
      }
      finally
      {
         session.close();
      }
   }

   final private void manipulatePersistentObjects()
   {
      Session     session;
      Transaction transaction;

      session = sessionFactory.openSession();

      try
      {
         transaction = session.beginTransaction();

         plainJavaObj3 = new PlainJavaObj( "A third persistent object", new Date() );
         session.save( plainJavaObj3 );

         session.delete( plainJavaObj2 );

         transaction.commit();
      }
      finally
      {
         session.close();
      }
   }

   final private void readAndDisplayPersistentObjects()
   {
      Session session;
      List<PlainJavaObj> result;

      session = sessionFactory.openSession();

      try
      {
         session.beginTransaction();

         result = (List<PlainJavaObj>)( session.createQuery("from PlainJavaObj").list() );

         for ( PlainJavaObj persistentObject: result )
         {
            System.out.println( "PlainJavaObj (" + persistentObject.getDate() + ") : " + persistentObject.getTitle() );
         }

         session.getTransaction().commit();
      }
      finally
      {
         session.close();
      }
   }

   final private void cleanup()
   {
      if ( sessionFactory != null )
      {
         sessionFactory.close();
      }
   }
}
