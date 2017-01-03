package introsde.assignment.soap.model;

import introsde.assignment.soap.dao.LifeCoachDao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


@Entity  // indicates that this class is an entity to persist in DB
@Table(name="Goal") // to whole table must be persisted 
@NamedQuery(name="Goal.findAll", query="SELECT g FROM Goal g")

@XmlType(propOrder = { "type", "value", "idGoal"})
public class Goal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id // defines this attributed as the one that identifies the entity
    @GeneratedValue(generator="sqlite_goal")
    @TableGenerator(name="sqlite_goal", table="sqlite_sequence",
        pkColumnName="name", valueColumnName="seq",
        pkColumnValue="Goal")
    @Column(name="idGoal")
    private int idGoal;

    @Column(name="type")
    private String type;

    @Column(name="value")
    private int value;


    
    
    // getters
    public int getIdGoal(){
        return idGoal;
    }
   public String getType() {
        return type;
   }

     public int getValue() {
        return value;
   }
    
    // setters
    public void setIdGoal(int idGoal){
        this.idGoal = idGoal;
    }

    public void setType(String type) {
        this.type = type;
   }

     public void setValue(int value) {
        this.value = value;
   }
   
    


    public static List<Goal> getAll() {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        List<Goal> list = em.createNamedQuery("Goal.findAll", Goal.class)
            .getResultList();
        LifeCoachDao.instance.closeConnections(em);
        return list;
    }

    public static Goal saveGoal(Goal goal) {
     
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(goal);
        tx.commit();
        LifeCoachDao.instance.closeConnections(em);
        return goal;
    } 

    public static Goal updateGoal(Goal g) {
        EntityManager em = LifeCoachDao.instance.createEntityManager(); 
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        g=em.merge(g);
        tx.commit();
        LifeCoachDao.instance.closeConnections(em);
        return g;
    }

    public static void removeGoal(Goal g) {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        g=em.merge(g);
        em.remove(g);
        tx.commit();
        LifeCoachDao.instance.closeConnections(em);
    }
    
}