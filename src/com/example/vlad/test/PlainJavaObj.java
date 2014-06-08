package com.example.vlad.test;

@javax.persistence.Entity
public class PlainJavaObj
{
   @javax.persistence.Id
   @javax.persistence.GeneratedValue( generator = "increment" )
   @org.hibernate.annotations.GenericGenerator( name = "increment", strategy = "increment" )
   private Long           _persistenceID;

   private String         _title;

   @javax.persistence.Temporal( javax.persistence.TemporalType.TIMESTAMP )
   @javax.persistence.Column( name = "OBJECT_DATE" )
   private java.util.Date _date;

   // Hibernate needs a no-argument constructor
   private PlainJavaObj()
   {
   }

   // for application use, to create new persistent objects
   public PlainJavaObj( String         title,
                              java.util.Date date )
   {
      _title = title;
      _date = date;
   }

   public java.util.Date getDate()
   {
      return _date;
   }

   public String getTitle()
   {
      return _title;
   }
}
