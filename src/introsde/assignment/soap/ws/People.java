package introsde.assignment.soap.ws;
import introsde.assignment.soap.model.*;


import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface People {
    

    //  Get all the people
    @WebMethod(operationName="getPeopleList")
    @WebResult(name="people") 
    public List<Person> getPeople();

    //  Read person with id = 1
    @WebMethod(operationName="readPerson")
    @WebResult(name="person") 
    public Person readPerson(@WebParam(name="personId") int id);

    //  Update person with id = 1 
    @WebMethod(operationName="updatePerson")
    @WebResult(name="personId") 
    public int updatePerson(@WebParam(name="person") Person person);

     //  Create a new person
    @WebMethod(operationName="createPerson")
    @WebResult(name="personId") 
    public int addPerson(@WebParam(name="person") Person person);


     // Delete a person
    @WebMethod(operationName="deletePerson")
    @WebResult(name="deletedPersonId") 
    public int deletePerson(@WebParam(name="personId") int id);

    // Read person history
    @WebMethod(operationName="readPersonHistory")
    @WebResult(name="healthMeasure") 
    public List<HealthMeasureHistory> readPersonHistory(@WebParam(name="personId") int id,  @WebParam(name="measureType") String measure);

    // Read all measure types
    @WebMethod(operationName="readMeasureTypes")
    @WebResult(name="measureTypes") 
    public List<MeasureDefinition> readMeasureTypes();

    // Read value of measure type history mid=1 
    @WebMethod(operationName="readMeasureHistoryId")
    @WebResult(name="measures") 
    public HealthMeasureHistory readMeasureHistoryId(@WebParam(name="historyMeasureId") int id);

    // Update measure and send the old one to the measure history list 
    @WebMethod(operationName="updatePersonHealthMeasureHistory")
    @WebResult(name="healthMeasureHistoryObject") 
    public HealthMeasureHistory createNewMeasure(@WebParam(name="idPerson") int id, 
        @WebParam(name="measureType") String measureType, @WebParam(name="newHealthMeasureHistory") HealthMeasureHistory newHealthMeasureHistory);
    


    //  Update goal
    @WebMethod(operationName="updateGoal")
    @WebResult(name="goalUpdate") 
    public Goal updateGoal(@WebParam(name="goal") Goal goal);

    //  Get all the goals
    @WebMethod(operationName="getGoalsList")
    @WebResult(name="goals") 
    public List<Goal> getGoals();


}