package Model.PhysicalHealth;

import java.util.ArrayList;

/**
 *
 * @author xfu13dcu
 */
public class PhysicalHealth {
    
    private ArrayList<WeightProgress> physicalHealthLog; 

    public PhysicalHealth(ArrayList<WeightProgress> physicalHealthLog) {
        this.physicalHealthLog = physicalHealthLog;
    }

    public ArrayList<WeightProgress> getPhysicalHealthLog() {
        return physicalHealthLog;
    }

    public void setPhysicalHealthLog(ArrayList<WeightProgress> physicalHealthLog) {
        this.physicalHealthLog = physicalHealthLog;
    }
    
    
    
}
