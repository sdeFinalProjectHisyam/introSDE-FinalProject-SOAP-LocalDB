package introsde.assignment.soap.ws;
import introsde.assignment.soap.model.*;


import java.util.List;

import javax.jws.WebService;

//Service Implementation

@WebService(endpointInterface = "introsde.assignment.soap.ws.People",
    serviceName="PeopleService")
public class PeopleImpl implements People {

    //  Get all the people
    @Override
     public List<Person> getPeople() {
         return Person.getAll();
     }

    //  Read person with id = 1
    @Override
    public Person readPerson(int id) {
        System.out.println("---> Reading Person by id = "+id);
        Person p = Person.getPersonById(id);
        if (p!=null) {
            System.out.println("---> Found Person by id = "+id+" => "+p.getName());
        } else {
            System.out.println("---> Didn't find any Person with  id = "+id);
        }
        return p;
    }

     //  Update person with id = 1 
    @Override
    public int updatePerson(Person person) {
        Person.updatePerson(person);
        return person.getIdPerson();
     }


     //  Create a new person
    @Override
    public int addPerson(Person person) {
        Person newPerson = Person.savePerson(person);
        return newPerson.getIdPerson();
     }

    
     //  Delete a person
    @Override
    public int deletePerson(int id) {
        Person p = Person.getPersonById(id);
        if (p!=null) {
            Person.removePerson(p);
            return 0;
        } else {
            return -1;
        }
    }

    // Read person history
    @Override
    public List<HealthMeasureHistory> readPersonHistory(int id, String measure) {
        return HealthMeasureHistory.getMeasureTypeById(id, measure);
    }


    // Read all measure types
    @Override
    public List<MeasureDefinition> readMeasureTypes() {
        return MeasureDefinition.getAll();
    }


    // Read value of measure type history mid=1 
    @Override
    public HealthMeasureHistory readMeasureHistoryId(int id) {
        return HealthMeasureHistory.getHealthMeasureHistoryById(id);
    }

    // Update measure and send the old one to the measure history list 
    @Override
    public HealthMeasureHistory createNewMeasure(int id, String measureType, HealthMeasureHistory newHealthMeasureHistory){
        Person person = Person.getPersonById(id);
        List<LifeStatus> lifeStatusList = person.getLifeStatus();
        LifeStatus lifeStatus = null;
            for (int i = 0; i<lifeStatusList.size(); i++) {
                LifeStatus lifeStatusTemp = lifeStatusList.get(i); 
                String measureName = lifeStatusTemp.getMeasure();  
                if (measureName.equals(measureType)) {
                    lifeStatus = lifeStatusTemp;
                }

            }

        String oldMeasureValue = lifeStatus.getValue();
        LifeStatus newLifeStatus = lifeStatus;
        newLifeStatus.setValue(newHealthMeasureHistory.getValue());
        lifeStatus.updateLifeStatus(newLifeStatus);           
        newHealthMeasureHistory.setValue(oldMeasureValue);
        newHealthMeasureHistory.setPerson(person);
        newHealthMeasureHistory.setMeasureName(measureType);
        return HealthMeasureHistory.saveHealthMeasureHistory(newHealthMeasureHistory);
    } 

    

     //  Update goal 
    @Override
    public Goal  updateGoal(Goal g) {
        Goal newGoal = Goal.updateGoal(g);
        return g;
    }

    //  Get all the goals
    @Override
     public List<Goal> getGoals() {
         return Goal.getAll();
     }


}