package org.usfirst.frc.team686.robot2017.auto.actions;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team686.robot2017.lib.util.DataLogger;

/**
 * Composite action, running all sub-actions at the same time All actions are
 * started then updated until all actions report being done.
 * 
 * @param A
 *            List of Action objects
 */
public class ParallelAction implements Action 
{

	private final ArrayList<Action> mActions;
    
    public ParallelAction(List<Action> actions) 
    {
        mActions = new ArrayList<>(actions.size());
        for (Action action : actions) 
        {
            mActions.add(action);
        }
    }

    @Override
    public boolean isFinished() 
    {
        boolean all_finished = true;
        for (Action action : mActions)
        {
            if (!action.isFinished()) 
            {
                all_finished = false;
            }
        }
        return all_finished;
    }

    @Override
    public void update() 
    {
        for (Action action : mActions)
        {
            action.update();
        }
    }

    @Override
    public void done() 
    {
        for (Action action : mActions) 
        {
            action.done();
        }
    }

    @Override
    public void start() 
    {
        for (Action action : mActions) 
        {
            action.start();
        }
    }
    
	private final DataLogger logger = new DataLogger()
    {
        @Override
        public void log()
        {
            for (Action action : mActions) 
            {
                action.getLogger().log();
            }
	    }
    };
	
    public DataLogger getLogger() { return logger; }
    
}
