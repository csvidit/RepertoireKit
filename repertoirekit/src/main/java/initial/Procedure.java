package initial;

import java.util.ArrayList;
import java.util.Iterator;

public class Procedure {
    
    private ArrayList<String> steps;

    public Procedure()
    {
        this.steps=null;
    }

    public Procedure(ArrayList<String> steps)
    {
        this.steps=steps;
    }

    public void addStep(String newStep)
    {
        String anotherStep;
        if(newStep.substring(0, newStep.indexOf(" ")).equalsIgnoreCase("bake"))
        {
            String anotherStepWords[]=newStep.split(" ");
            int hours=Integer.parseInt(anotherStepWords[2])/60;
            int minutes=Integer.parseInt(anotherStepWords[2])%60;
            if(hours==0)
            {
                anotherStep="Bake at "+anotherStepWords[1]+" \u00B0C for "+minutes+" minutes.";
            }
            else
            {
                anotherStep="Bake at "+anotherStepWords[1]+" \u00B0C for "+hours+" and "+minutes+" minutes.";
            }
        }
        else
        {
            anotherStep=newStep;
        }
        steps.add(anotherStep);
    }

    public void addStepAt(String newStep, int index)
    {
        String anotherStep;
        if(newStep.substring(0, newStep.indexOf(" ")).equalsIgnoreCase("bake"))
        {
            String anotherStepWords[]=newStep.split(" ");
            int hours=Integer.parseInt(anotherStepWords[2])/60;
            int minutes=Integer.parseInt(anotherStepWords[2])%60;
            if(hours==0)
            {
                anotherStep="Bake at "+anotherStepWords[1]+" \u00B0C for "+minutes+" minutes.";
            }
            else
            {
                anotherStep="Bake at "+anotherStepWords[1]+" \u00B0C for "+hours+" and "+minutes+" minutes.";
            }
        }
        else
        {
            anotherStep=newStep;
        }
        steps.add(index, anotherStep);
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
