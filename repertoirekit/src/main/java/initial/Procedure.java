package initial;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Procedure {
    
    private ArrayList<String> steps;
    private static final String stepConsts[]={"Bake at ", " \u00B0C for ", " hours and "," minutes."};

    public Procedure()
    {
        this.steps= new ArrayList<>();
    }

    public Procedure(List<String> steps)
    {
        this.steps= (ArrayList<String>) steps;
    }

    public String createStep(String newStep)
    {
        String anotherStep;
        if(newStep.substring(0, newStep.indexOf(" ")).equalsIgnoreCase("bake"))
        {
            String anotherStepWords[]=newStep.split(" ");
            int hours=Integer.parseInt(anotherStepWords[2])/60;
            int minutes=Integer.parseInt(anotherStepWords[2])%60;
            if(hours==0)
            {
                anotherStep=stepConsts[0]+anotherStepWords[1]+stepConsts[1]+minutes+stepConsts[3];
            }
            else
            {
                anotherStep=stepConsts[0]+anotherStepWords[1]+stepConsts[1]+hours+stepConsts[2]+minutes+stepConsts[3];
            }
        }
        else
        {
            anotherStep=newStep;
        }
        return anotherStep;
    }

    public void addStep(String newStep)
    {
        
        steps.add(createStep(newStep));
    }

    public void addStepAt(String newStep, int index)
    {
        steps.add(index, createStep(newStep));
    }

    public void addStepFromFile(String newStep)
    {
        steps.add(newStep.substring(newStep.indexOf(" ")+1));
    }

    public void deleteStep(int index)
    {
        steps.remove(index);
    }

    @Override
    public String toString()
    {
        String toString="";
        Iterator<String> stepsIter = steps.iterator();
        while(stepsIter.hasNext())
        {
            String temp=stepsIter.next();
            int index=steps.indexOf(temp);

            toString+=index+". "+temp+"\n";
        }
        return toString;
    }

}
